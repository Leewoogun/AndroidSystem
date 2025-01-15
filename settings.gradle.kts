pluginManagement {
    includeBuild("build-logic")
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
    }
}

rootProject.name = "androidSystem"
include(":app")

include(
    ":core:data",
    ":core:designSystem",
    ":core:navigation",
    ":core:network",
)

include(":feature:main")
include(":feature:home")
include(":feature:favorite")
include(":feature:calendar")
include(":core:data-api")
include(":core:model")
include(":core:domain")
include(":core:ui")
include(":core:util")
include(":core:database")
