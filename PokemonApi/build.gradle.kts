plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id(Deps.KotlinSerialization.plugin)
}

android {
    namespace = "com.xxmrk888ytxx.pokemonapi"
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
}

dependencies {
    implementation(project(ProjectModules.CoreAndroid))

    implementation(Deps.KotlinSerialization.serialization)
    implementation(Deps.Ktor.KtorAndroid)
    implementation(Deps.Ktor.KtorSerialization)
    implementation(Deps.Ktor.loggier)
    implementation(Deps.Ktor.KtorNegotiation)
}