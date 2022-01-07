package com.polymerteam.polymermachine.api.scripting.kts

import cpw.mods.modlauncher.Launcher
import kotlin.script.experimental.annotations.KotlinScript
import kotlin.script.experimental.api.ScriptCompilationConfiguration
import kotlin.script.experimental.api.ScriptEvaluationConfiguration
import kotlin.script.experimental.api.dependencies
import kotlin.script.experimental.jvm.*

@KotlinScript(
    fileExtension = "kts",
    compilationConfiguration = MachineScriptCompilationConfiguration::class,
    evaluationConfiguration = MachineScriptEvaluationConfiguration::class,
)
abstract class MachineScript {
    fun helloWorld() {
        println("********** Hello World ************")
    }
}


object MachineScriptCompilationConfiguration : ScriptCompilationConfiguration({
    // Implicit imports for all scripts of this type
//    defaultImports(DependsOn::class, Repository::class)
    jvm {
//        dependenciesFromCurrentContext()
        val classLoader = Thread.currentThread().contextClassLoader
        dependencies.append(JvmDependencyFromClassLoader { classLoader })
    }
})

object MachineScriptEvaluationConfiguration : ScriptEvaluationConfiguration({

})