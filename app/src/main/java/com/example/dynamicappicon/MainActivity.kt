package com.example.dynamicappicon

import IconSelectorScreen
import android.content.ComponentName
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.dynamicappicon.data.AppIconRemoteConfig
import com.example.dynamicappicon.helper.AppIconManager
import com.example.dynamicappicon.model.AppIconModel
import com.example.dynamicappicon.model.RemoteConfigKeys
import com.example.dynamicappicon.ui.theme.DynamicAppIconTheme
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings


class MainActivity : ComponentActivity() {
    private val appIconManager by lazy { AppIconManager(this) }
    private val remoteConfig by lazy { AppIconRemoteConfig() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DynamicAppIconTheme {
                val currentIcon = remember { appIconManager.getCurrentIcon() }
                var remoteIcon by remember {
                    mutableStateOf(currentIcon?.takeIf { it.isRemote })
                }

                IconSelectorScreen(
                    appIcons = AppIconModel.all().filter { it.isRemote.not() },
                    currentIcon = currentIcon,
                    onRemoteConfigEnabled = {
                        remoteConfig.initialize { icon ->
                            remoteIcon = icon
                            icon?.let { appIconManager.updateAppIcon(it) }
                        }
                    },
                    onIconSelected = { selectedIcon ->
                        appIconManager.updateAppIcon(selectedIcon)
                    },
                    remoteIcon = remoteIcon
                )
            }
        }
    }
}


