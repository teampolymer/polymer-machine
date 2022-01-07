import net.minecraftforge.gradle.common.util.RunConfig
import net.minecraftforge.gradle.userdev.UserDevExtension
import net.minecraftforge.gradle.userdev.DependencyManagementExtension
import java.util.Date
import java.text.SimpleDateFormat

buildscript {
    repositories {
        maven("https://maven.minecraftforge.net")
        mavenCentral()
    }
    dependencies {
        classpath(group = "net.minecraftforge.gradle", name = "ForgeGradle", version = "5.1.+") {
            isChanging = true
        }
    }
}
plugins {
    kotlin("jvm") version "1.5.21"
    kotlin("plugin.serialization") version "1.5.21"
}

apply {
    plugin("net.minecraftforge.gradle")
}



version = "1.16.5-b1-1"
group = "com.polymer-team.polymer-machine" // http://maven.apache.org/guides/mini/guide-naming-conventions.html
//archivesBaseName = "polymer-machine"
configure<BasePluginExtension> {
    archivesName.set("polymer-machine")
}
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

println(
    "Java: ${System.getProperty("java.version")} " +
            "JVM:  ${System.getProperty("java.vm.version")}(${System.getProperty("java.vendor")}) " +
            "Arch:  ${System.getProperty("os.arch")}"
)


configure<UserDevExtension> {
    mappings("official", "1.16.5")

//    accessTransformer(file("src/main/resources/META-INF/accesstransformer.cfg"))
    runs {
        val runConfig = Action<RunConfig> {
            workingDirectory = project.file("run").canonicalPath
            // Recommended logging data for a userdev environment
            // The markers can be changed as needed.
            // "SCAN": For mods scan.
            // "REGISTRIES": For firing of registry events.
            // "REGISTRYDUMP": For getting the contents of all registries.
            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")
            mods {
                create("polymermachine") {
                    source(sourceSets["main"])
                }
            }
        }

        create("client", runConfig)
        create("server", runConfig)
        create("data") {
            runConfig(this)
            // Specify the modid for data generation, where to output the resulting resource, and where to look for existing resources.
            args(
                "--mod",
                "polymermachine",
                "--all",
                "--output",
                file("src/generated/resources/"),
                "--existing",
                file("src/main/resources/")
            )
        }
    }

}

//Include resources generated by data generators.
sourceSets.main {
    resources { srcDir("src/generated/resources") }
}

repositories {
    maven {
        name = "Kotlin for Forge"
        url = uri("https://thedarkcolour.github.io/KotlinForForge/")
    }

    flatDir {
        dir("libs")
    }
}

dependencies {
    // Specify the version of Minecraft to use, If this is any group other then 'net.minecraft' it is assumed
    // that the dep is a ForgeGradle 'patcher' dependency. And it's patches will be applied.
    // The userdev artifact is a special name and will get all sorts of transformations applied to it.
    "minecraft"("net.minecraftforge:forge:1.16.5-36.2.9")
    // Use the latest version of KotlinForForge
    implementation("thedarkcolour:kotlinforforge:1.14.0")

    implementation("org.jetbrains.kotlin:kotlin-scripting-common:1.6.10")
    implementation("org.jetbrains.kotlin:kotlin-scripting-jvm:1.6.10")
    implementation("org.jetbrains.kotlin:kotlin-scripting-dependencies:1.6.10")
    implementation("org.jetbrains.kotlin:kotlin-scripting-jvm-host:1.6.10")


    val fg = project.extensions.getByType<DependencyManagementExtension>()


}


// Example for how to get properties into the manifest for reading by the runtime..
tasks.jar {
    manifest {
        val time = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(Date())
        attributes(
            mapOf(
                "Specification-Title" to "Polymer Machine",
                "Specification-Vendor" to "WarmthDawn",
                "Specification-Version" to "1", // We are version 1 of ourselves
                "Implementation-Title" to project.name,
                "Implementation-Version" to "${project.version}",
                "Implementation-Vendor" to "WarmthDawn",
                "Implementation-Timestamp" to time
            )
        )
    }

}
// Example configuration to allow publishing using the maven-publish task
// This is the preferred method to reobfuscate your jar file
tasks.jar {
    finalizedBy("reobfJar")
}
// However if you are in a multi-project build, dev time needs unobfed jar files, so you can delay the obfuscation until publishing by doing
//publish.dependsOn('reobfJar')

