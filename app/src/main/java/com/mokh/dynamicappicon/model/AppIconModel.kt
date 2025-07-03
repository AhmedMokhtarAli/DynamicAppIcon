package com.mokh.dynamicappicon.model

import androidx.compose.ui.graphics.Color
import com.mokh.dynamicappicon.BuildConfig
import com.mokh.dynamicappicon.R
import com.mokh.dynamicappicon.ui.theme.*

data class AppIconModel(
    val aliasName: String,
    val titleResId: Int,
    val color: Color,
    var isRemote: Boolean = false,
    var iconResId: Int? = null
) {

    companion object {
        fun all(): List<AppIconModel> = localIcons() + remoteIcons()

        private fun localIcons(): List<AppIconModel> {
            return listOf(
                AppIconModel(BuildConfig.main_activity_alias_red, R.string.red, Red),
                AppIconModel(BuildConfig.main_activity_alias_green, R.string.green, Green),
                AppIconModel(BuildConfig.main_activity_alias_blue, R.string.blue, Blue),
                AppIconModel(BuildConfig.main_activity_alias_orange, R.string.orange, Orange),
                AppIconModel(BuildConfig.main_activity_alias_yellow, R.string.yellow, Yellow),
                AppIconModel(BuildConfig.main_activity_alias_purple, R.string.purple, Purple),
                AppIconModel(BuildConfig.main_activity_alias_teal, R.string.teal, Teal),
                AppIconModel(BuildConfig.main_activity_alias_pink, R.string.pink, Pink),
                AppIconModel(BuildConfig.main_activity_alias_indigo, R.string.indigo, Indigo),
                AppIconModel(BuildConfig.main_activity_alias_cyan, R.string.cyan, Cyan),
                AppIconModel(BuildConfig.main_activity_alias_lime, R.string.lime, Lime),
                AppIconModel(BuildConfig.main_activity_alias_deep_orange, R.string.deep_orange, DeepOrange),
                AppIconModel(BuildConfig.main_activity_alias_brown, R.string.brown, Brown),
                AppIconModel(BuildConfig.main_activity_alias_grey, R.string.grey, Grey),
                AppIconModel(BuildConfig.main_activity_alias_blue_grey, R.string.blue_grey, BlueGrey),
            )
        }

        private fun remoteIcons(): List<AppIconModel> = listOf(
            AppIconModel(
                BuildConfig.main_activity_alias_ramadan,
                R.string.ramadan,
                Color.White,
                isRemote = true,
                iconResId = R.drawable.ic_launcher_ramdan_foreground
            ),
            AppIconModel(
                BuildConfig.main_activity_alias_eid_adha,
                R.string.eid_adha,
                Color.White,
                isRemote = true,
                iconResId = R.drawable.ic_launcher_eid_adha_foreground
            )
        )
    }
}
