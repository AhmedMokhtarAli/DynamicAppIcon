package com.mokh.dynamicappicon.data

import com.mokh.dynamicappicon.BuildConfig
import com.mokh.dynamicappicon.data.RemoteIconsConstant.ICON_VALUE_EID_ADHA
import com.mokh.dynamicappicon.data.RemoteIconsConstant.ICON_VALUE_RAMADAN

enum class RemoteConfigKeys(val value: String, val aliasName: String) {
    RAMADAN(ICON_VALUE_RAMADAN, BuildConfig.main_activity_alias_ramadan),
    EID_ADHA(ICON_VALUE_EID_ADHA, BuildConfig.main_activity_alias_eid_adha);

    companion object {
        fun fromValue(value: String): RemoteConfigKeys? {
            return values().firstOrNull { it.value == value }
        }
    }
}

object RemoteIconsConstant {
    const val ICON_KEY = "icon"
    const val ICON_VALUE_RAMADAN = "ramadan"
    const val ICON_VALUE_EID_ADHA = "eid_adha"
}
