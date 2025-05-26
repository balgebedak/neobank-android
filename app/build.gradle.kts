import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

// Helper function to load properties safely
fun Project.loadProps(fileName: String): Properties {
    val propsFile = rootProject.file("app/$fileName")
    return Properties().apply {
        if (propsFile.exists()) {
            propsFile.inputStream().use { load(it) }
        } else {
            println("Warning: Property file '$fileName' not found. Looked in: ${propsFile.absolutePath}")
        }
    }
}

android {
    namespace = "com.example.neobank"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.neobank"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Default fallback values
        buildConfigField("String", "API_BASE_URL", "\"https://api.default.neobank.com/\"")
        buildConfigField("String", "APP_NAME_SUFFIX", "\" (Default)\"")
        buildConfigField("String", "NEOBANK_API_KEY", "\"DEFAULT_FALLBACK_NEOBANK_KEY\"")
        buildConfigField("String", "SOME_OTHER_SDK_KEY", "\"DEFAULT_FALLBACK_OTHER_KEY\"")
    }

    flavorDimensions += "environment"

    productFlavors {
        create("dev") {
            dimension = "environment"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-dev"

            buildConfigField("String", "API_BASE_URL", "\"https://api.dev.neobank.com/\"")
            buildConfigField("String", "APP_NAME_SUFFIX", "\" (Dev)\"")

            val devProps = project.loadProps("dev.properties")
            val devApiKey = devProps.getProperty("NEOBANK_API_KEY", "MISSING_DEV_NEOBANK_KEY").replace("\"", "")
            val devSdkKey = devProps.getProperty("SOME_OTHER_SDK_KEY", "MISSING_DEV_OTHER_KEY").replace("\"", "")
            println("🔐 dev.properties loaded:")
            println("NEOBANK_API_KEY = $devApiKey")
            println("SOME_OTHER_SDK_KEY = $devSdkKey")
            buildConfigField("String", "NEOBANK_API_KEY", "\"$devApiKey\"")
            buildConfigField("String", "SOME_OTHER_SDK_KEY", "\"$devSdkKey\"")
        }

        create("staging") {
            dimension = "environment"
            applicationIdSuffix = ".staging"
            versionNameSuffix = "-staging"

            buildConfigField("String", "API_BASE_URL", "\"https://api.staging.neobank.com/\"")
            buildConfigField("String", "APP_NAME_SUFFIX", "\" (Staging)\"")

            val stagingProps = project.loadProps("staging.properties")
            val stagingApiKey = stagingProps.getProperty("NEOBANK_API_KEY", "MISSING_STAGING_NEOBANK_KEY").replace("\"", "")
            val stagingSdkKey = stagingProps.getProperty("SOME_OTHER_SDK_KEY", "MISSING_STAGING_OTHER_KEY").replace("\"", "")
            buildConfigField("String", "NEOBANK_API_KEY", "\"$stagingApiKey\"")
            buildConfigField("String", "SOME_OTHER_SDK_KEY", "\"$stagingSdkKey\"")
        }

        create("prod") {
            dimension = "environment"

            buildConfigField("String", "API_BASE_URL", "\"https://api.prod.neobank.com/\"")
            buildConfigField("String", "APP_NAME_SUFFIX", "\"\"")

            val prodProps = project.loadProps("prod.properties")
            val prodApiKey = prodProps.getProperty("NEOBANK_API_KEY", "MISSING_PROD_NEOBANK_KEY").replace("\"", "")
            val prodSdkKey = prodProps.getProperty("SOME_OTHER_SDK_KEY", "MISSING_PROD_OTHER_KEY").replace("\"", "")
            buildConfigField("String", "NEOBANK_API_KEY", "\"$prodApiKey\"")
            buildConfigField("String", "SOME_OTHER_SDK_KEY", "\"$prodSdkKey\"")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // Preferences DataStore
    implementation(libs.androidx.datastore.preferences)

}
