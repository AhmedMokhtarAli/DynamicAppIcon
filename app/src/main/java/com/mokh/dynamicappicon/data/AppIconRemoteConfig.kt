package com.mokh.dynamicappicon.data

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.mokh.dynamicappicon.model.AppIconModel
import com.mokh.dynamicappicon.data.RemoteIconsConstant.ICON_KEY

class AppIconRemoteConfig() {
    private val remoteConfig = FirebaseRemoteConfig.getInstance()
    private val allIcons = AppIconModel.all()

    fun initialize(settings: FirebaseRemoteConfigSettings = defaultSettings(), onResult: (AppIconModel?) -> Unit) {
        remoteConfig.setConfigSettingsAsync(settings)
        remoteConfig.setDefaultsAsync(mapOf(ICON_KEY to allIcons.first().aliasName))

        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val iconKey = remoteConfig.getString(ICON_KEY)
                onResult(getRemoteIconByKey(iconKey))
            } else {
                onResult(null)
            }
        }
    }

    private fun getRemoteIconByKey(key: String): AppIconModel? {
        return RemoteConfigKeys.fromValue(key)?.let { configKey ->
            allIcons.firstOrNull { it.aliasName == configKey.aliasName }
        }
    }

    private fun defaultSettings() = FirebaseRemoteConfigSettings.Builder()
        .setMinimumFetchIntervalInSeconds(10)
        .build()


}