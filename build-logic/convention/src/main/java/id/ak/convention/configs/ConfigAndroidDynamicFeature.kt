package id.ak.convention.configs

import com.android.build.api.dsl.DynamicFeatureExtension
import id.ak.convention.contants.ConventionConstants
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configAndroidDynamicFeature(dynamicFeatureExtension: DynamicFeatureExtension) {
    dynamicFeatureExtension.apply {
        namespace =
            "${ConventionConstants.BASE_NAME}.${project.path.replace(":", ".").substring(1)}"
        compileSdk = ConventionConstants.MAX_SDK_VERSION

        defaultConfig {
            minSdk = ConventionConstants.MIN_SDK_VERSION
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        buildFeatures {
            buildConfig = true
        }

        buildTypes {
            release {
                isMinifyEnabled = false
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_21
            targetCompatibility = JavaVersion.VERSION_21
        }

    }

    configureKotlinCompiler()
}

private fun Project.configureKotlinCompiler() {
    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
            freeCompilerArgs.add(ConventionConstants.FREE_COMPILER)
        }
    }
}