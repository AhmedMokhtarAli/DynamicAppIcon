package com.example.dynamicappicon.data

import com.example.dynamicappicon.model.AppIconModel
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

class AppIconRemoteConfig() {
    private val remoteConfig = FirebaseRemoteConfig.getInstance()
    private val allIcons = AppIconModel.all()

    fun initialize(settings: FirebaseRemoteConfigSettings = defaultSettings(), onResult: (AppIconModel?) -> Unit) {
        remoteConfig.setConfigSettingsAsync(settings)
        remoteConfig.setDefaultsAsync(mapOf("icon" to allIcons.first().aliasName))

        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val iconKey = remoteConfig.getString("icon")
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

    enum class RemoteConfigKeys(val value: String, val aliasName: String) {
        RAMADAN("ramadan", "com.mokh.dynamicappicon.MainActivityRamadan"),
        EID_ADHA("eid_adha", "com.mokh.dynamicappicon.MainActivityEidAdha");

        companion object {
            fun fromValue(value: String): RemoteConfigKeys? {
                return values().firstOrNull { it.value == value }
            }
        }
    }
}