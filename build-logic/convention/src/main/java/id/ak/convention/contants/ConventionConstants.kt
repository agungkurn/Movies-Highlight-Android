package id.ak.convention.contants

internal object ConventionConstants {
    val commonCoreModule = ":common:core"
    val commonUiModule = ":common:ui"
    val domainModule = ":domain"
    val resourceExcludes = listOf(
        "/META-INF/{AL2.0,LGPL2.1}",
        "/META-INF/gradle/incremental.annotation.processors"
    )
    const val BASE_NAME = "id.ak.convention"
    const val MIN_SDK_VERSION = 28
    const val MAX_SDK_VERSION = 35
    const val KSP = "ksp"
    const val FREE_COMPILER = "-opt-in=kotlin.RequiresOptIn"
}