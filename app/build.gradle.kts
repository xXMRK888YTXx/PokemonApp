plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id (Deps.Dagger.DaggerKaptPlugin)
}

android {
    namespace = "com.xxmrk888ytxx.pokemons"
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId = "com.xxmrk888ytxx.pokemons"
        minSdk = Config.minSdk
        targetSdk = Config.compileSdk
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Deps.Compose.ComposeKotlinCompiler
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(ProjectModules.CoreCompose))
    implementation(project(ProjectModules.PokemonListScreen))

    //Navigation
    implementation(Deps.Compose.Navigation)

    kapt(Deps.Dagger.DaggerKaptCompiler)
}