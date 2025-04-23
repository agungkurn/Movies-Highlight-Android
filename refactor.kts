#!/usr/bin/env kotlin

import java.io.File

fun prompt(question: String, default: String? = null): String {
    print("$question${if (default != null) " [$default]" else ""}: ")
    return readln().takeIf { it.isNotBlank() } ?: default.orEmpty()
}

// --- Step 1: Input ---
val projectName = prompt("📦 Enter project name").also {
    require(it.isNotBlank()) { "Project name is required." }
}

val defaultPackage = "com.example.${projectName.lowercase()}"
val packageName = prompt("🏷️  Package name", defaultPackage)
require(Regex("^[a-z]+(\\.[a-z][a-z0-9]*)+\$").matches(packageName)) {
    "❌ Invalid package name: $packageName"
}

val featureInput = prompt("🔧 Feature modules (comma-separated)", "home")
val featureModules = featureInput
    .split(",")
    .map { it.trim().lowercase().replace(Regex("[^a-z0-9]"), "") }
    .filter { it.length in 1..15 }

println("\n⏳ Refactoring project...")
println("✅ Project name   : $projectName")
println("✅ Package name   : $packageName")
println("✅ Feature modules: ${featureModules.joinToString(", ")}")

// --- Step 2: Replace package name ---
val oldPackage = "id.ak.multimoduletemplate"
val skippedFileNames = listOf("refactor.kts", "README.md")
val oldPackagePath = oldPackage.replace(".", File.separator)
val newPackagePath = packageName.replace(".", File.separator)

fun replaceInFile(file: File) {
    val text = file.readText()
    val replaced = text
        .replace(oldPackage, packageName)
        .replace("Multi-Module-Template", projectName)
    if (text != replaced) {
        file.writeText(replaced)
        println("✏️  Updated: ${file.path}")
    }
}

fun walkAndReplace(root: File) {
    root.walkTopDown()
        .filter {
            it.isFile && it.name !in skippedFileNames && it.extension in listOf(
                "kt",
                "kts",
                "xml",
                "gradle"
            )
        }
        .forEach { replaceInFile(it) }
}
walkAndReplace(File("."))

// --- Step 3: Rename package directories ---
fun renamePackageDirs(root: File) {
    val srcRoots = root.walkTopDown()
        .filter { it.name == "java" || it.name == "kotlin" }
        .filter { it.isDirectory }
        .toList()

    for (srcRoot in srcRoots) {
        val oldDir = File(srcRoot, oldPackagePath)
        if (oldDir.exists()) {
            val newDir = File(srcRoot, newPackagePath)
            println("📁 Moving: ${oldDir.path} → ${newDir.path}")
            newDir.parentFile.mkdirs()
            oldDir.copyRecursively(newDir, overwrite = true)
            oldDir.deleteRecursively()
        }
    }
}
renamePackageDirs(File("."))

// --- Step 4: Optional - Rename feature modules ---
featureModules.forEach { name ->
    val originalModule = File("feature-home")
    if (originalModule.exists()) {
        val target = File("feature-$name")
        if (target.exists()) {
            println("⚠️  Skipping: feature-$name already exists")
        } else {
            println("📦 Renaming module: feature-home → feature-$name")
            originalModule.copyRecursively(target, overwrite = true)
            originalModule.deleteRecursively()

            // Fix module name inside `settings.gradle.kts`
            val settingsFile = File("settings.gradle.kts")
            settingsFile.writeText(
                settingsFile.readText().replace(":feature-home", ":feature-$name")
            )
        }
    }
}

// --- Step 5: Refactor build-config.convention plugin ID ---
val conventionModule = File("build-config.convention")
val oldConventionId = "id.ak.convention"
val basePackage = packageName
    .split(".")
    .dropLast(1)
    .joinToString(".") // e.g., com.example
val newConventionId = "$basePackage.convention"

fun replaceConventionId(file: File) {
    val text = file.readText()
    val replaced = text.replace(oldConventionId, newConventionId)
    if (text != replaced) {
        file.writeText(replaced)
        println("🔧 Refactored convention ID in: ${file.path}")
    }
}

if (conventionModule.exists()) {
    conventionModule.walkTopDown()
        .filter { it.isFile && it.extension in listOf("kt", "kts", "toml") }
        .forEach { replaceConventionId(it) }
}

println("\n🎉 Done! Your project is ready.")
