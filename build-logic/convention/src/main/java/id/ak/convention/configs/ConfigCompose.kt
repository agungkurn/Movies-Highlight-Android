package id.ak.convention.configs

import com.android.build.api.dsl.LibraryExtension
import id.ak.convention.ext.androidTestImplementation
import id.ak.convention.ext.debugImplementation
import id.ak.convention.ext.implementation
import id.ak.convention.ext.libs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configJetpackCompose(libraryExtension: LibraryExtension) {
    libraryExtension.apply {
        buildFeatures {
            compose = true
        }

        dependencies {
            val bom = libs.androidx.compose.bom.get()

            implementation(libs.androidx.core.ktx.get())
            implementation(libs.androidx.activity.compose.get())
            implementation(platform(bom))
            implementation(libs.bundles.androidx.compose.get())
            implementation(libs.bundles.coil.get())
            debugImplementation(libs.androidx.ui.tooling.debug.get())
            androidTestImplementation(platform(bom))
        }
    }
}