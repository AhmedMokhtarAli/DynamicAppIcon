package com.mokh.dynamicappicon.utils

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import com.mokh.dynamicappicon.model.AppIconModel

class AppIconManager(private val context: Context) {
    private val packageManager = context.packageManager
    private val allIcons = AppIconModel.all()

    fun getCurrentIcon(): AppIconModel? {
        return allIcons.firstOrNull { icon ->
            packageManager.getComponentEnabledSetting(
                ComponentName(context, icon.aliasName)
            ) == PackageManager.COMPONENT_ENABLED_STATE_ENABLED
        }
    }

    fun updateAppIcon(targetIcon: AppIconModel) {
        allIcons.forEach { icon ->
            val state = if (icon == targetIcon) {
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED
            } else {
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED
            }
            setComponentState(icon.aliasName, state)
        }
    }

    private fun setComponentState(aliasName: String, state: Int) {
        val component = ComponentName(context, aliasName)
        packageManager.setComponentEnabledSetting(
            component,
            state,
            PackageManager.DONT_KILL_APP
        )
    }
}