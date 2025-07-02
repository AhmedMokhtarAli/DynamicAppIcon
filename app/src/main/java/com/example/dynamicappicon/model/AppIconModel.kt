package com.example.dynamicappicon.model

import androidx.compose.ui.graphics.Color
import com.example.dynamicappicon.R
import com.example.dynamicappicon.ui.theme.Blue
import com.example.dynamicappicon.ui.theme.BlueGrey
import com.example.dynamicappicon.ui.theme.Brown
import com.example.dynamicappicon.ui.theme.Cyan
import com.example.dynamicappicon.ui.theme.DeepOrange
import com.example.dynamicappicon.ui.theme.Green
import com.example.dynamicappicon.ui.theme.Grey
import com.example.dynamicappicon.ui.theme.Indigo
import com.example.dynamicappicon.ui.theme.Lime
import com.example.dynamicappicon.ui.theme.Orange
import com.example.dynamicappicon.ui.theme.Pink
import com.example.dynamicappicon.ui.theme.Purple
import com.example.dynamicappicon.ui.theme.Red
import com.example.dynamicappicon.ui.theme.Teal
import com.example.dynamicappicon.ui.theme.Yellow

private const val APP_ID = "com.mokh.dynamicappicon"

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
            val localIconData = listOf(
                Triple("MainActivityRed", R.string.red, Red),
                Triple("MainActivityGreen", R.string.green, Green),
                Triple("MainActivityBlue", R.string.blue, Blue),
                Triple("MainActivityOrange", R.string.orange, Orange),
                Triple("MainActivityYellow", R.string.yellow, Yellow),
                Triple("MainActivityPurple", R.string.purple, Purple),
                Triple("MainActivityTeal", R.string.teal, Teal),
                Triple("MainActivityPink", R.string.pink, Pink),
                Triple("MainActivityIndigo", R.string.indigo, Indigo),
                Triple("MainActivityCyan", R.string.cyan, Cyan),
                Triple("MainActivityLime", R.string.lime, Lime),
                Triple("MainActivityDeepOrange", R.string.deep_orange, DeepOrange),
                Triple("MainActivityBrown", R.string.brown, Brown),
                Triple("MainActivityGrey", R.string.grey, Grey),
                Triple("MainActivityBlueGrey", R.string.blue_grey, BlueGrey),
            )

            return localIconData.map { (aliasName, titleResId, color) ->
                AppIconModel(APP_ID+"."+aliasName, titleResId, color)
            }
        }

        private fun remoteIcons(): List<AppIconModel> = listOf(
            AppIconModel(
                APP_ID+"."+"MainActivityRamadan",
                R.string.ramadan,
                Color.White,
                isRemote = true,
                iconResId = R.drawable.ic_launcher_ramdan_foreground
            ),
            AppIconModel(
                APP_ID+"."+"MainActivityEidAdha",
                R.string.eid_adha,
                Color.White,
                isRemote = true,
                iconResId = R.drawable.ic_launcher_eid_adha_foreground
            )
        )
    }
}
