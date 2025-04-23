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

dependencies {
}