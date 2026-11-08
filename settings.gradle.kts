pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "JapaneseLearningApp"
include(":app")
enableFeatureArgs = ["androidx.compose.compiler.plugins.proguardInlinedTypeSuffixEnabled=true"]