buildscript {
    ext {
        compose_version = "2024.04.01"
    }
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.23")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("prepare", Delete::class) {
    delete(rootProject.buildDir)
}