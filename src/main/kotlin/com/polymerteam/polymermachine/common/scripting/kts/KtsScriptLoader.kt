package com.polymerteam.polymermachine.common.scripting.kts

import com.polymerteam.polymermachine.common.scripting.util.forgeContextClasspath
import org.apache.logging.log4j.LogManager
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.createDirectories
import kotlin.io.path.exists
import kotlin.math.log
import kotlin.script.experimental.api.ResultWithDiagnostics
import kotlin.script.experimental.api.onSuccess
import kotlin.script.experimental.api.plus
import kotlin.script.experimental.host.toScriptSource
import kotlin.script.experimental.jvm.jvm
import kotlin.script.experimental.jvm.updateClasspath
import kotlin.script.experimental.jvmhost.BasicJvmScriptingHost
import kotlin.script.experimental.jvmhost.createJvmScriptDefinitionFromTemplate

class KtsScriptLoader {
    val MULTIBLOCK_DIR: Path = Paths.get("polymer", "machine")

    private val logger = LogManager.getLogger()

    fun load() {

        if (!MULTIBLOCK_DIR.exists()) {
            MULTIBLOCK_DIR.createDirectories()
        }


        val scripts = MULTIBLOCK_DIR.toFile()
            .walk()
            .filter { it.isFile && it.name.endsWith(".kts") }
            .map { it.toScriptSource() }
        val configuration =
            createJvmScriptDefinitionFromTemplate<MachineScript>(compilation = {

//    defaultImports(DependsOn::class, Repository::class)
                jvm {
                    updateClasspath(forgeContextClasspath())
                }
            })

        val host = BasicJvmScriptingHost()

        scripts.forEach {
            logger.info("Loading script {}", it.name)
            when (val result = host.eval(it, configuration.compilationConfiguration, configuration.evaluationConfiguration)) {
                is ResultWithDiagnostics.Success -> {
                    logger.info("Loaded script {}", it.name)
                }
                is ResultWithDiagnostics.Failure -> {
                    logger.error("Failed to load script {}", it.name)
                    result.reports.forEach { r ->
                        logger.error(r)
                    }
                }
            }
        }

    }
}