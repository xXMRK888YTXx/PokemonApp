pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Pokemons"
include(":app")
include(":CoreAndroid")
include(":CoreCompose")
include(":PokemonListScreen")
include(":PokemonApi")
include(":Database")
include(":PokemonDetailsScreen")
