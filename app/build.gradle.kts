import org.jetbrains.kotlin.konan.properties.Properties
import java.io.FileInputStream

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.services)
}
android {
    namespace = "com.mokh.dynamicappicon"
    compileSdk = 35

    val properties = Properties()
    val configFile = project.rootProject.file("config")
    if (configFile.canRead()) {
        properties.load(FileInputStream(configFile))
    }
    fun Properties.getCleanProperty(key: String): String {
        return getProperty(key)?.trim('"') ?: throw GradleException("Missing $key")
    }

    val mainActivity = properties.getCleanProperty("MAIN_ACTIVITY")
    val activityAliases = mapOf(
        "main_activity_alias_red" to properties.getCleanProperty("ACTIVITY_ALIAS_RED"),
        "main_activity_alias_ramadan" to properties.getCleanProperty("ACTIVITY_ALIAS_RAMADAN"),
        "main_activity_alias_eid_adha" to properties.getCleanProperty("ACTIVITY_ALIAS_EID_ADHA"),
        "main_activity_alias_blue" to properties.getCleanProperty("ACTIVITY_ALIAS_BLUE"),
        "main_activity_alias_green" to properties.getCleanProperty("ACTIVITY_ALIAS_GREEN"),
        "main_activity_alias_orange" to properties.getCleanProperty("ACTIVITY_ALIAS_ORANGE"),
        "main_activity_alias_yellow" to properties.getCleanProperty("ACTIVITY_ALIAS_YELLOW"),
        "main_activity_alias_purple" to properties.getCleanProperty("ACTIVITY_ALIAS_PURPLE"),
        "main_activity_alias_teal" to properties.getCleanProperty("ACTIVITY_ALIAS_TEAL"),
        "main_activity_alias_pink" to properties.getCleanProperty("ACTIVITY_ALIAS_PINK"),
        "main_activity_alias_indigo" to properties.getCleanProperty("ACTIVITY_ALIAS_INDIGO"),
        "main_activity_alias_cyan" to properties.getCleanProperty("ACTIVITY_ALIAS_CYAN"),
        "main_activity_alias_lime" to properties.getCleanProperty("ACTIVITY_ALIAS_LIME"),
        "main_activity_alias_deep_orange" to properties.getCleanProperty("ACTIVITY_ALIAS_DEEP_ORANGE"),
        "main_activity_alias_brown" to properties.getCleanProperty("ACTIVITY_ALIAS_BROWN"),
        "main_activity_alias_grey" to properties.getCleanProperty("ACTIVITY_ALIAS_GREY"),
        "main_activity_alias_blue_grey" to properties.getCleanProperty("ACTIVITY_ALIAS_BLUE_GREY")
    )
    defaultConfig {
        applicationId = "com.mokh.dynamicappicon"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        manifestPlaceholders.apply {
            set("main_activity",mainActivity)
            activityAliases.forEach{ key,value ->
                set(key,value)
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
            activityAliases.forEach { key, value ->
                buildConfigField("String", key, "\"$value\"")
            }
        }

        debug {
            isDebuggable = true
            isMinifyEnabled = false
            buildConfigField("String", "main_activity", "\"$mainActivity\"")
            activityAliases.forEach{ key, value ->
                buildConfigField("String", key, "\"$value\"")
            }
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