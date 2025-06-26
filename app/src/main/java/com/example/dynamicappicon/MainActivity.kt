package com.example.dynamicappicon

import IconSelectorScreen
import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.dynamicappicon.model.AppIconModel
import com.example.dynamicappicon.ui.theme.DynamicAppIconTheme


class MainActivity : ComponentActivity() {
    private val appIcons = AppIconModel.all()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DynamicAppIconTheme {
                IconSelectorScreen(
                    appIcons = appIcons,
                    currentIcon = getCurrentlyEnabledIcon(),
                    onIconSelected = { selectedIcon ->
                        switchAppIcon(this, selectedIcon)
                    }
                )
            }
        }
    }

    private fun switchAppIcon(context: Context, targetIcon: AppIconModel) {
        val packageManager = context.packageManager
        appIcons.forEach { icon ->
            val state = if (icon.aliasName == targetIcon.aliasName) {
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED
            } else {
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED
            }
            setComponentState(context, packageManager, icon.aliasName, state)
        }
    }

    private fun setComponentState(
        context: Context,
        packageManager: PackageManager,
        aliasName: String,
        newState: Int
    ) {
        val componentName = ComponentName(context, aliasName)
        packageManager.setComponentEnabledSetting(
            componentName,
            newState,
            PackageManager.DONT_KILL_APP
        )
    }
    private fun getCurrentlyEnabledIcon(): AppIconModel? {
        return appIcons.firstOrNull { icon ->
            packageManager.getComponentEnabledSetting(
                ComponentName(this, icon.aliasName)
            ) == PackageManager.COMPONENT_ENABLED_STATE_ENABLED
        }
    }
}



