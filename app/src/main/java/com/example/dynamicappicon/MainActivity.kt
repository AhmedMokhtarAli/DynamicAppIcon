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
import com.example.dynamicappicon.model.RemoteConfigKeys
import com.example.dynamicappicon.ui.theme.DynamicAppIconTheme
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings


class MainActivity : ComponentActivity() {
    private val appIcons = AppIconModel.all()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DynamicAppIconTheme {
                var remoteIcon by remember { mutableStateOf<AppIconModel?>(if(getCurrentIcon()?.isRemote==true) getCurrentIcon() else null) }
                Log.d("ssssssssssss", "onCreate: ${remoteIcon?.isRemote}")
                IconSelectorScreen(
                    appIcons = appIcons.filter { it.isRemote.not() },
                    currentIcon = getCurrentIcon(),
                    onRemoteConfigEnabled = {
                        initRemoteConfig { icon ->
                            remoteIcon = icon
                            updateAppIcon(icon)
                        }
                    },
                    onIconSelected = { selectedIcon ->
                        updateAppIcon(selectedIcon)
                    },
                    remoteIcon = remoteIcon
                )
            }
        }
    }

    private fun initRemoteConfig(onResult: (AppIconModel) -> Unit) {
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(10)
            .build()

        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(mapOf("icon" to appIcons.first().aliasName))

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val iconKey = remoteConfig.getString("icon")
                    val remoteKey = RemoteConfigKeys.fromValue(iconKey)
                    remoteKey?.let { key ->
                        val aliasName = when (key) {
                            RemoteConfigKeys.RAMADAN -> "com.mokh.dynamicappicon.MainActivityRamadan"
                            RemoteConfigKeys.EID_ADHA -> "com.mokh.dynamicappicon.MainActivityEidAdha"
                        }
                        appIcons.firstOrNull { it.aliasName == aliasName }?.let { icon ->
                            onResult(icon)
                        }
                    } ?: run {
                        Log.w("RemoteConfig", "Unknown icon key: $iconKey")
                    }
                }
            }
    }

    private fun updateAppIcon(targetIcon: AppIconModel) {
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



