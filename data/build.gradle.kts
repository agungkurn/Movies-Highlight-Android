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

fun getCiEnvironment(propertyName: String): String? {
    return System.getenv(propertyName)
}

android {
    defaultConfig {
        if (System.getenv("CIRCLECI") != null) {
            // Code specific to CircleCI environment
            // Access CircleCI environment variables or perform CircleCI-specific tasks

            buildConfigField("String", "API_KEY", getCiEnvironment("api_key").orEmpty())
            buildConfigField("String", "BASE_URL", getCiEnvironment("base_url").orEmpty())
            buildConfigField(
                "String",
                "BASE_IMAGE_URL",
                getCiEnvironment("base_image_url").orEmpty()
            )
            buildConfigField("String", "POSTER_SIZE", getCiEnvironment("poster_size").orEmpty())
            buildConfigField(
                "String",
                "CERTIFICATE_HASH",
                getCiEnvironment("certificate_hash").orEmpty()
            )
        } else {
            buildConfigField("String", "API_KEY", getLocalProperty("api_key").orEmpty())
            buildConfigField("String", "BASE_URL", getLocalProperty("base_url").orEmpty())
            buildConfigField(
                "String",
                "BASE_IMAGE_URL",
                getLocalProperty("base_image_url").orEmpty()
            )
            buildConfigField("String", "POSTER_SIZE", getLocalProperty("poster_size").orEmpty())
            buildConfigField(
                "String",
                "CERTIFICATE_HASH",
                getLocalProperty("certificate_hash").orEmpty()
            )
        }
    }
}

dependencies {
    implementation(libs.androidx.paging.common)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
    implementation(libs.sqlite.cipher)
    implementation(libs.androidx.sqlite)
}