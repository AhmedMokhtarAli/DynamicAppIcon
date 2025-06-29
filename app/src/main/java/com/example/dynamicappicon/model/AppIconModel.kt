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

data class AppIconModel(
    val aliasName: String,
    val titleResId: Int,
    val color: Color,
    var isRemote: Boolean = false,
    var iconResId: Int? = null
) {
    companion object {
        fun all(): List<AppIconModel> = listOf(
            AppIconModel("com.mokh.dynamicappicon.MainActivityRed", R.string.red, Red),
            AppIconModel("com.mokh.dynamicappicon.MainActivityGreen", R.string.green, Green),
            AppIconModel("com.mokh.dynamicappicon.MainActivityBlue", R.string.blue, Blue),
            AppIconModel("com.mokh.dynamicappicon.MainActivityOrange", R.string.orange, Orange),
            AppIconModel("com.mokh.dynamicappicon.MainActivityYellow", R.string.yellow, Yellow),
            AppIconModel("com.mokh.dynamicappicon.MainActivityPurple", R.string.purple, Purple),
            AppIconModel("com.mokh.dynamicappicon.MainActivityTeal", R.string.teal, Teal),
            AppIconModel("com.mokh.dynamicappicon.MainActivityPink", R.string.pink, Pink),
            AppIconModel("com.mokh.dynamicappicon.MainActivityIndigo", R.string.indigo, Indigo),
            AppIconModel("com.mokh.dynamicappicon.MainActivityCyan", R.string.cyan, Cyan),
            AppIconModel("com.mokh.dynamicappicon.MainActivityLime", R.string.lime, Lime),
            AppIconModel("com.mokh.dynamicappicon.MainActivityDeepOrange", R.string.deep_orange, DeepOrange),
            AppIconModel("com.mokh.dynamicappicon.MainActivityBrown", R.string.brown, Brown),
            AppIconModel("com.mokh.dynamicappicon.MainActivityGrey", R.string.grey, Grey),
            AppIconModel("com.mokh.dynamicappicon.MainActivityBlueGrey", R.string.blue_grey, BlueGrey),
            AppIconModel("com.mokh.dynamicappicon.MainActivityRamadan", R.string.ramadan, Color.White,isRemote = true,iconResId = R.drawable.ic_launcher_ramdan_foreground),
            AppIconModel("com.mokh.dynamicappicon.MainActivityEidAdha", R.string.eid_adha, Color.White,isRemote = true, iconResId = R.drawable.ic_launcher_eid_adha_foreground))
    }
}
