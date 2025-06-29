package com.example.dynamicappicon.model

enum class RemoteConfigKeys(val key: String) {
    RAMADAN("ramadan"),
    EID_ADHA("eid_adha");
    companion object {
        fun fromValue(value: String): RemoteConfigKeys? =
            values().find { it.key == value }
    }
}
