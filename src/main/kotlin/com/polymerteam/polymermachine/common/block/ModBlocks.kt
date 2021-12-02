package com.polymerteam.polymermachine.common.block

import com.polymerteam.polymermachine.api.PolymerMachineApi
import com.polymerteam.polymermachine.common.utils.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

object ModBlocks {
    val REGISTRY = KDeferredRegister(ForgeRegistries.BLOCKS, PolymerMachineApi.MOD_ID)

    val EXAMPLE_BLOCK by REGISTRY.registerObject("example_block") {
        Block(Material.BAMBOO) {
            lightLevel { 15 }
            strength(3.0f)
            noCollission()
            randomTicks()
            requiresCorrectToolForDrops()
            sound(SoundType.BAMBOO)
        }
    }
}

