package com.polymerteam.polymermachine.api.storage

interface IMachineStorage {
    fun apply()

    fun readNbt()
    fun writeNbt()

    fun readByte()
    fun writeByte()
}