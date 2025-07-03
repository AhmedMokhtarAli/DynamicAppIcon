plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.services)
}
val mainActivity = "com.mokh.dynamicappicon.MainActivity"
val activityAliasRed = "com.mokh.dynamicappicon.MainActivityRed"
val activityAliasRamadan = "com.mokh.dynamicappicon.MainActivityRamadan"
val activityAliasEidAdha = "com.mokh.dynamicappicon.MainActivityEidAdha"
val activityAliasBlue = "com.mokh.dynamicappicon.MainActivityBlue"
val activityAliasGreen = "com.mokh.dynamicappicon.MainActivityGreen"
val activityAliasOrange = "com.mokh.dynamicappicon.MainActivityOrange"
val activityAliasYellow = "com.mokh.dynamicappicon.MainActivityYellow"
val activityAliasPurple = "com.mokh.dynamicappicon.MainActivityPurple"
val activityAliasTeal = "com.mokh.dynamicappicon.MainActivityTeal"
val activityAliasPink = "com.mokh.dynamicappicon.MainActivityPink"
val activityAliasIndigo = "com.mokh.dynamicappicon.MainActivityIndigo"
val activityAliasCyan = "com.mokh.dynamicappicon.MainActivityCyan"
val activityAliasLime = "com.mokh.dynamicappicon.MainActivityLime"
val activityAliasDeepOrange = "com.mokh.dynamicappicon.MainActivityDeepOrange"
val activityAliasBrown = "com.mokh.dynamicappicon.MainActivityBrown"
val activityAliasGrey = "com.mokh.dynamicappicon.MainActivityGrey"
val activityAliasBlueGrey = "com.mokh.dynamicappicon.MainActivityBlueGrey"

android {
    namespace = "com.mokh.dynamicappicon"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mokh.dynamicappicon"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        manifestPlaceholders.apply {
            manifestPlaceholders.apply {
                set("main_activity", mainActivity)
                set("main_activity_alias_red", activityAliasRed)
                set("main_activity_alias_ramadan", activityAliasRamadan)
                set("main_activity_alias_eid_adha", activityAliasEidAdha)
                set("main_activity_alias_blue", activityAliasBlue)
                set("main_activity_alias_green", activityAliasGreen)
                set("main_activity_alias_orange", activityAliasOrange)
                set("main_activity_alias_yellow", activityAliasYellow)
                set("main_activity_alias_purple", activityAliasPurple)
                set("main_activity_alias_teal", activityAliasTeal)
                set("main_activity_alias_pink", activityAliasPink)
                set("main_activity_alias_indigo", activityAliasIndigo)
                set("main_activity_alias_cyan", activityAliasCyan)
                set("main_activity_alias_lime", activityAliasLime)
                set("main_activity_alias_deep_orange", activityAliasDeepOrange)
                set("main_activity_alias_brown", activityAliasBrown)
                set("main_activity_alias_grey", activityAliasGrey)
                set("main_activity_alias_blue_grey", activityAliasBlueGrey)
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            isMinifyEnabled = false
            buildConfigField("String", "main_activity", "\"$mainActivity\"")
            buildConfigField("String", "main_activity_alias_red", "\"$activityAliasRed\"")
            buildConfigField("String", "main_activity_alias_ramadan", "\"$activityAliasRamadan\"")
            buildConfigField("String", "main_activity_alias_eid_adha", "\"$activityAliasEidAdha\"")
            buildConfigField("String", "main_activity_alias_blue", "\"$activityAliasBlue\"")
            buildConfigField("String", "main_activity_alias_green", "\"$activityAliasGreen\"")
            buildConfigField("String", "main_activity_alias_orange", "\"$activityAliasOrange\"")
            buildConfigField("String", "main_activity_alias_yellow", "\"$activityAliasYellow\"")
            buildConfigField("String", "main_activity_alias_purple", "\"$activityAliasPurple\"")
            buildConfigField("String", "main_activity_alias_teal", "\"$activityAliasTeal\"")
            buildConfigField("String", "main_activity_alias_pink", "\"$activityAliasPink\"")
            buildConfigField("String", "main_activity_alias_indigo", "\"$activityAliasIndigo\"")
            buildConfigField("String", "main_activity_alias_cyan", "\"$activityAliasCyan\"")
            buildConfigField("String", "main_activity_alias_lime", "\"$activityAliasLime\"")
            buildConfigField("String", "main_activity_alias_deep_orange", "\"$activityAliasDeepOrange\"")
            buildConfigField("String", "main_activity_alias_brown", "\"$activityAliasBrown\"")
            buildConfigField("String", "main_activity_alias_grey", "\"$activityAliasGrey\"")
            buildConfigField("String", "main_activity_alias_blue_grey", "\"$activityAliasBlueGrey\"")
        }

        debug {
            isDebuggable = true
            isMinifyEnabled = false
            buildConfigField("String", "main_activity", "\"$mainActivity\"")
            buildConfigField("String", "main_activity_alias_red", "\"$activityAliasRed\"")
            buildConfigField("String", "main_activity_alias_ramadan", "\"$activityAliasRamadan\"")
            buildConfigField("String", "main_activity_alias_eid_adha", "\"$activityAliasEidAdha\"")
            buildConfigField("String", "main_activity_alias_blue", "\"$activityAliasBlue\"")
            buildConfigField("String", "main_activity_alias_green", "\"$activityAliasGreen\"")
            buildConfigField("String", "main_activity_alias_orange", "\"$activityAliasOrange\"")
            buildConfigField("String", "main_activity_alias_yellow", "\"$activityAliasYellow\"")
            buildConfigField("String", "main_activity_alias_purple", "\"$activityAliasPurple\"")
            buildConfigField("String", "main_activity_alias_teal", "\"$activityAliasTeal\"")
            buildConfigField("String", "main_activity_alias_pink", "\"$activityAliasPink\"")
            buildConfigField("String", "main_activity_alias_indigo", "\"$activityAliasIndigo\"")
            buildConfigField("String", "main_activity_alias_cyan", "\"$activityAliasCyan\"")
            buildConfigField("String", "main_activity_alias_lime", "\"$activityAliasLime\"")
            buildConfigField("String", "main_activity_alias_deep_orange", "\"$activityAliasDeepOrange\"")
            buildConfigField("String", "main_activity_alias_brown", "\"$activityAliasBrown\"")
            buildConfigField("String", "main_activity_alias_grey", "\"$activityAliasGrey\"")
            buildConfigField("String", "main_activity_alias_blue_grey", "\"$activityAliasBlueGrey\"")
        }

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    // Core Android
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    // Compose BOM platform
    implementation(platform(libs.androidx.compose.bom))

    // Compose UI
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.compose.foundation)

    // Material Design
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.core)
    implementation(libs.androidx.material.icons.extended)

    // Animation
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.animation.graphics)

    // Runtime
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.runtime.livedata)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // Debug
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.config.ktx)

}