package com.teampolymer.polymer.machine

import com.teampolymer.polymer.machine.api.PolymerMachineApi
import com.teampolymer.polymer.machine.api.capability.registerCapabilities
import com.teampolymer.polymer.machine.common.block.ModBlocks
import com.teampolymer.polymer.machine.common.item.ModItems
import com.teampolymer.polymer.machine.common.scripting.kts.KtsScriptLoader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.FORGE_BUS
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(PolymerMachineApi.MOD_ID)
object PolymerMachine {

    private val LOG: Logger = LogManager.getLogger()

    init {
        LOG.log(Level.INFO, "Hello world!")

        // Register the KDeferredRegister to the mod-specific event bus
        ModBlocks.REGISTRY.register(MOD_BUS)
        ModItems.REGISTRY.register(MOD_BUS)

        // usage of the KotlinEventBus
        MOD_BUS.addListener(PolymerMachine::onClientSetup)
        MOD_BUS.addListener(PolymerMachine::preInit)
        FORGE_BUS.addListener(PolymerMachine::onServerAboutToStart)

        KtsScriptLoader().load()
    }

    fun processIMC(event: InterModProcessEvent) {

    }

    private fun preInit(event: FMLCommonSetupEvent) {
        registerCapabilities()
    }

    /**
     * This is used for initializing client specific
     * things such as renderers and keymaps
     * Fired on the mod specific event bus.
     */
    private fun onClientSetup(event: FMLClientSetupEvent) {
        LOG.log(Level.INFO, "Initializing client...")
    }

    /**
     * Fired on the global Forge bus.
     */
    private fun onServerAboutToStart(event: FMLServerAboutToStartEvent) {
        LOG.log(Level.INFO, "Server starting...")
    }
}