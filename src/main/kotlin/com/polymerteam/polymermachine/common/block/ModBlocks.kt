package com.polymerteam.polymermachine.common.block

import com.polymerteam.polymermachine.PolymerMachine
import com.polymerteam.polymermachine.api.PolymerMachineApi
import com.polymerteam.polymermachine.common.utils.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

object ModBlocks {
    val REGISTRY = KDeferredRegister(ForgeRegistries.BLOCKS, PolymerMachineApi.MOD_ID)

}

