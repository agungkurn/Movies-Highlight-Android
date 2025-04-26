package id.ak.convention

import com.android.build.api.dsl.DynamicFeatureExtension
import id.ak.convention.configs.configAndroidDynamicFeature
import id.ak.convention.configs.configJetpackComposeDynamicFeature
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

class DynamicFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                alias(libs.plugins.android.dynamic.feature)
                alias(libs.plugins.kotlin.android)
                alias(libs.plugins.kotlin.compose)
                alias(libs.plugins.kotlin.serialization)
                alias(libs.plugins.kapt)
            }

            extensions.configure<DynamicFeatureExtension> {
                configAndroidDynamicFeature(this)
                configJetpackComposeDynamicFeature(this)
            }

            dependencies {
                implementation(project(ConventionConstants.appModule))
                implementation(project(ConventionConstants.commonCoreModule))
                implementation(project(ConventionConstants.commonUiModule))
                implementation(project(ConventionConstants.domainModule))
                implementation(libs.hilt.android.get())
                add("kapt", libs.hilt.compiler.get())
                implementation(libs.hilt.navigation.get())
                implementation(libs.androidx.navigation.get())
                implementation(libs.kotlinx.serialization.get())
                androidTestImplementation(libs.androidx.junit.get())
                androidTestImplementation(libs.androidx.espresso.core.get())
                testImplementation(libs.junit.get())
            }
        }
    }
}