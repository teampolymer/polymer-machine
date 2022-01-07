package com.polymerteam.polymermachine.common.scripting.util

import net.minecraftforge.fml.ModList
import java.io.File
import java.net.JarURLConnection
import java.net.URL
import java.net.URLClassLoader


fun modClasspath(): Sequence<File> {
    return ModList.get().modFiles.map { it.file.filePath.toFile() }.asSequence()
}

fun systemDependencies(): Sequence<File> {
    return allRelatedClassLoaders(Thread.currentThread().contextClassLoader)
        .filter { it is URLClassLoader }
        .flatMap {
            (it as URLClassLoader).urLs.asSequence().mapNotNull { url -> url.toValidClasspathFileOrNull() }
        }
}


fun forgeContextClasspath(): List<File> {
    return (modClasspath() + systemDependencies()).toList()
}


private val validClasspathFilesExtensions = setOf("jar", "zip", "java")

internal fun File.isValidClasspathFile(): Boolean =
    isDirectory || (isFile && extension in validClasspathFilesExtensions)

internal fun URL.toValidClasspathFileOrNull(): File? =
    (toContainingJarOrNull() ?: toFileOrNull())?.takeIf { it.isValidClasspathFile() }

internal fun URL.toFileOrNull() =
    try {
        File(toURI())
    } catch (e: IllegalArgumentException) {
        null
    } catch (e: java.net.URISyntaxException) {
        null
    } ?: run {
        if (protocol != "file") null
        else File(file)
    }

internal fun URL.toContainingJarOrNull(): File? =
    if (protocol == "jar") {
        (openConnection() as? JarURLConnection)?.jarFileURL?.toFileOrNull()
    } else null

// Iterating over classloaders tree in a regular, parent-first order
private fun allRelatedClassLoaders(
    clsLoader: ClassLoader,
    visited: MutableSet<ClassLoader> = HashSet()
): Sequence<ClassLoader> {
    if (!visited.add(clsLoader)) return emptySequence()

    val singleParent = clsLoader.parent
    if (singleParent != null)
        return sequenceOf(singleParent).flatMap { allRelatedClassLoaders(it, visited) } + clsLoader

    return try {
        val arrayOfClassLoaders = getParentClassLoaders(clsLoader)
        // TODO: PluginClassLoader uses filtering (mustBeLoadedByPlatform), consider using the same logic, if possible
        // (untill proper compiling from classloader instead of classpath is implemented)
        arrayOfClassLoaders.asSequence().flatMap { allRelatedClassLoaders(it, visited) } + clsLoader
    } catch (e: Throwable) {
        sequenceOf(clsLoader)
    }
}

private fun getParentClassLoaders(clsLoader: ClassLoader): Array<ClassLoader> {
    return try {
        getParentsForNewClassLoader(clsLoader)
    } catch (exception: NoSuchMethodException) {
        try {
            getParentsForOldClassLoader(clsLoader)
        } catch (exception: NoSuchFieldException) {
            // Possibly idea sources and kotlin compiler had diverged
            emptyArray()
        }
    }
}


@Throws(NoSuchFieldException::class)
private fun getParentsForOldClassLoader(clsLoader: ClassLoader): Array<ClassLoader> {
    // Correct way of getting parents in com.intellij.ide.plugins.cl.PluginClassLoader from IDEA 202 and earlier
    val field = clsLoader.javaClass.getDeclaredField("myParents") // com.intellij.ide.plugins.cl.PluginClassLoader
    field.isAccessible = true

    @Suppress("UNCHECKED_CAST")
    return field.get(clsLoader) as Array<ClassLoader>
}

@Throws(NoSuchMethodException::class)
private fun getParentsForNewClassLoader(clsLoader: ClassLoader): Array<ClassLoader> {
    // Correct way of getting parents in com.intellij.ide.plugins.cl.PluginClassLoader from IDEA 203+
    val method = clsLoader.javaClass.getDeclaredMethod("getAllParents")
    method.isAccessible = true

    @Suppress("UNCHECKED_CAST")
    return method.invoke(clsLoader) as Array<ClassLoader>
}