package id.ak.convention

import id.ak.convention.contants.ConventionConstants
import id.ak.convention.ext.alias
import id.ak.convention.ext.id
import id.ak.convention.ext.implementation
import id.ak.convention.ext.libs
import id.ak.convention.ext.testImplementation
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.dependencies

class DomainLayerPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                id("java-library")
                alias(libs.plugins.jetbrains.kotlin.jvm)
            }

            extensions.configure<JavaPluginExtension>("java") {
                sourceCompatibility = JavaVersion.VERSION_21
                targetCompatibility = JavaVersion.VERSION_21
            }

            extensions.configure<org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension>("kotlin") {
                jvmToolchain(21)
            }

            dependencies {
                implementation(project(ConventionConstants.commonCoreModule))
                implementation(libs.javax.inject.get())
                implementation(libs.kotlinx.coroutines.core.get())
                testImplementation(libs.junit.get())
            }
        }
    }
}