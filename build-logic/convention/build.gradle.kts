import org.gradle.initialization.DependenciesAccessors
import org.gradle.kotlin.dsl.support.serviceOf
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "id.ak.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
    }
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("data") {
            id = "base.data"
            implementationClass = "id.ak.convention.DataLayerPlugin"
        }
        register("domain") {
            id = "base.domain"
            implementationClass = "id.ak.convention.DomainLayerPlugin"
        }
        register("feature") {
            id = "base.feature"
            implementationClass = "id.ak.convention.FeatureLayerPlugin"
        }
        register("common") {
            id = "base.common.core"
            implementationClass = "id.ak.convention.CommonPlugin"
        }
        register("commonUi") {
            id = "base.common.ui"
            implementationClass = "id.ak.convention.CommonUiPlugin"
        }
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    gradle.serviceOf<DependenciesAccessors>().classes.asFiles.forEach {
        compileOnly(files(it.absolutePath))
    }
}