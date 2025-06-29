package com.example.dynamicappicon

import IconSelectorScreen
import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.dynamicappicon.model.AppIconModel
import com.example.dynamicappicon.ui.theme.DynamicAppIconTheme
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import kotlin.math.log


class MainActivity : ComponentActivity() {
    private val appIcons = AppIconModel.all()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DynamicAppIconTheme {
                var remoteIcon by remember { mutableStateOf<AppIconModel?>(null) }

                IconSelectorScreen(
                    appIcons = appIcons.filter { it.isRemote.not() },
                    currentIcon = getCurrentIcon(),
                    onRemoteConfigEnabled = {initRemoteConfig{ icon, url ->
                        remoteIcon = icon
                    }},
                    onIconSelected = { selectedIcon ->
                        updateAppIcon(selectedIcon)
                    },
                    remoteIcon = remoteIcon
                )
            }
        }
    }

    private fun initRemoteConfig(onResult: (AppIconModel, String) -> Unit) {
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(10)
            .build()

        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(mapOf("icon" to appIcons.first().aliasName))

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val aliasName = remoteConfig.getString("icon")
                    appIcons.find { it.aliasName == aliasName }?.let { icon ->
//                        if (icon.aliasName == getCurrentIcon()?.aliasName) return@let
//                        updateAppIcon(icon)

                        onResult(icon, icon.aliasName)
                    }
                }
            }
    }

    private fun updateAppIcon(targetIcon: AppIconModel) {
        Log.d("sssssssssssssss", "updateAppIcon: ${targetIcon.aliasName}")
        appIcons.forEach { icon ->
            val state = if (icon == targetIcon) {
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED
            } else {
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED
            }
            setComponentState(icon.aliasName, state, packageManager)
        }
    }

    private fun setComponentState(aliasName: String, state: Int, pm: PackageManager) {
        val component = ComponentName(this, aliasName)
        pm.setComponentEnabledSetting(component, state, PackageManager.DONT_KILL_APP)
    }

    private fun getCurrentIcon(): AppIconModel? {
        return appIcons.firstOrNull { icon ->
            packageManager.getComponentEnabledSetting(ComponentName(this, icon.aliasName)) ==
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED
        }
    }
}



