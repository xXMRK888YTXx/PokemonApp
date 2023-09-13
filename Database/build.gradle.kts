plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id(Deps.KSP.ksp_plugin)

}

android {
    namespace = "com.xxmrk888ytxx.database"
    compileSdk = Config.compileSdk

    defaultConfig {
        minSdk = Config.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = Config.isR8ProGuardEnableForRelease
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            isMinifyEnabled = Config.isR8ProGuardEnableForDebug
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Config.sourceCompatibility
        targetCompatibility = Config.targetCompatibility
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
    ksp {
        arg("room.schemaLocation","$projectDir/schemas")
    }
}

dependencies {
    implementation(Deps.Room.RoomKTX)
    implementation(Deps.Room.RoomRuntime)
    ksp(Deps.Room.compiler)
}