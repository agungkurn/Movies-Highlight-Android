import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.data)
}

fun getLocalProperty(propertyName: String): String? {
    val localProperties = Properties()
    val localPropertiesFile = File("local.properties")

    if (localPropertiesFile.exists()) {
        localProperties.load(FileInputStream(localPropertiesFile))
    }

    return localProperties.getProperty(propertyName)
}

android {
    defaultConfig {
        buildConfigField("String", "API_KEY", getLocalProperty("api_key").orEmpty())
        buildConfigField("String", "BASE_URL", getLocalProperty("base_url").orEmpty())
        buildConfigField("String", "BASE_IMAGE_URL", getLocalProperty("base_image_url").orEmpty())
        buildConfigField("String", "POSTER_SIZE", getLocalProperty("poster_size").orEmpty())
    }
}

dependencies {
    implementation(libs.androidx.paging.common)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
}