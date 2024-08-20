pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }

    }
}

rootProject.name = "Magic Ball"
include(":app")
include(":features")
include(":features:loadscreen")
include(":core")
include(":features:menuscreen")
include(":features:playmenu")
include(":features:settingsscreen")
include(":features:quitscreen")
include(":features:firstgame")
include(":features:doublegame")
include(":game-commons")
