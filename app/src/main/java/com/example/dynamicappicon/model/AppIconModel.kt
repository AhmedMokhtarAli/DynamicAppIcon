package com.example.dynamicappicon.model

import androidx.compose.ui.graphics.Color
import com.example.dynamicappicon.R
import com.example.dynamicappicon.ui.theme.Blue
import com.example.dynamicappicon.ui.theme.Green
import com.example.dynamicappicon.ui.theme.Red

data class AppIconModel(
    val aliasName: String,
    val titleResId: Int,
    val color: Color
) {
    companion object {
        fun all(): List<AppIconModel> = listOf(
            AppIconModel("com.mokh.dynamicappicon.MainActivityRed", R.string.red, Red),
            AppIconModel("com.mokh.dynamicappicon.MainActivityGreen", R.string.green, Green),
            AppIconModel("com.mokh.dynamicappicon.MainActivityBlue", R.string.blue, Blue)
        )
    }
}
