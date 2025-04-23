package id.ak.convention

import com.android.build.gradle.LibraryExtension
import id.ak.convention.configs.configAndroid
import id.ak.convention.contants.ConventionConstants
import id.ak.convention.ext.alias
import id.ak.convention.ext.debugImplementation
import id.ak.convention.ext.implementation
import id.ak.convention.ext.libs
import id.ak.convention.ext.releaseImplementation
import id.ak.convention.ext.testImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import kotlin.jvm.kotlin
import kotlin.text.get

class DataLayerPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                alias(libs.plugins.android.library)
                alias(libs.plugins.kotlin.android)
                alias(libs.plugins.hilt)
                alias(libs.plugins.ksp)
                alias(libs.plugins.kotlin.serialization)
            }

            extensions.configure<LibraryExtension> {
                configAndroid(this)
                defaultConfig.targetSdk = ConventionConstants.MAX_SDK_VERSION
            }

            dependencies {
                add(ConventionConstants.KSP, libs.hilt.compiler.get())
                implementation(libs.hilt.android.get())
                implementation(project(ConventionConstants.commonCoreModule))
                implementation(project(ConventionConstants.domainModule))
                implementation(libs.dataStorePreferences.get())
                implementation(libs.bundles.retrofit.get())
                implementation(libs.kotlinx.serialization.get())
                debugImplementation(libs.chucker.debug.get())
                releaseImplementation(libs.chucker.release.get())
                testImplementation(libs.junit.get())
            }
        }
    }
}