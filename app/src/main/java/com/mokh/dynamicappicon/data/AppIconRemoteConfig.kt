package com.mokh.dynamicappicon.data

import com.mokh.dynamicappicon.model.AppIconModel
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.mokh.dynamicappicon.BuildConfig
import com.mokh.dynamicappicon.data.AppIconRemoteConfig.Constants.ICON_KEY
import com.mokh.dynamicappicon.data.AppIconRemoteConfig.Constants.ICON_VALUE_EID_ADHA
import com.mokh.dynamicappicon.data.AppIconRemoteConfig.Constants.ICON_VALUE_RAMADAN

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

    enum class RemoteConfigKeys(val value: String, val aliasName: String) {
        RAMADAN(ICON_VALUE_RAMADAN, BuildConfig.main_activity_alias_ramadan),
        EID_ADHA(ICON_VALUE_EID_ADHA, BuildConfig.main_activity_alias_eid_adha);

        companion object {
            fun fromValue(value: String): RemoteConfigKeys? {
                return values().firstOrNull { it.value == value }
            }
        }
    }

    object Constants {
        const val ICON_KEY = "icon"
        const val ICON_VALUE_RAMADAN = "ramadan"
        const val ICON_VALUE_EID_ADHA = "eid_adha"
    }
}