package com.polymerteam.polymermachine.common.scripting.kts

import kotlin.script.experimental.annotations.KotlinScript

@KotlinScript(fileExtension = "kts")
abstract class MachineScript {
    fun helloWorld() {
        println("********** Hello World ************")
    }
}