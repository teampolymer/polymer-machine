package com.teampolymer.polymer.machine.common.item

import com.teampolymer.polymer.machine.PolymerMachine
import com.teampolymer.polymer.machine.api.PolymerMachineApi
import com.polymerteam.polymermachine.common.block.ModBlocks
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

object ModItems {
    val REGISTRY = KDeferredRegister(ForgeRegistries.ITEMS, com.teampolymer.polymer.machine.api.PolymerMachineApi.MOD_ID)
}