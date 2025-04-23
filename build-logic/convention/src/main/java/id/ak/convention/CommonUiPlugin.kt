package id.ak.convention

import com.android.build.gradle.LibraryExtension
import id.ak.convention.configs.configAndroid
import id.ak.convention.configs.configJetpackCompose
import id.ak.convention.contants.ConventionConstants
import id.ak.convention.ext.alias
import id.ak.convention.ext.androidTestImplementation
import id.ak.convention.ext.implementation
import id.ak.convention.ext.libs
import id.ak.convention.ext.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class CommonUiPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                alias(libs.plugins.android.library)
                alias(libs.plugins.kotlin.android)
                alias(libs.plugins.kotlin.compose)
            }

            extensions.configure<LibraryExtension> {
                configAndroid(this)
                configJetpackCompose(this)
                defaultConfig.targetSdk = ConventionConstants.MAX_SDK_VERSION
            }

            dependencies {
                implementation(libs.androidx.navigation.get())
                implementation(libs.kotlinx.serialization.get())
                androidTestImplementation(libs.androidx.junit.get())
                androidTestImplementation(libs.androidx.espresso.core.get())
                testImplementation(libs.junit.get())
            }
        }
    }
}