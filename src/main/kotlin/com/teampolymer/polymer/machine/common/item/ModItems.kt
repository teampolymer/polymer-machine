package com.teampolymer.polymer.machine.common.item

import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

object ModItems {
    val REGISTRY = KDeferredRegister(ForgeRegistries.ITEMS, com.teampolymer.polymer.machine.api.PolymerMachineApi.MOD_ID)
}