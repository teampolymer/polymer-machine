package com.polymerteam.polymermachine.common.item

import com.polymerteam.polymermachine.PolymerMachine
import com.polymerteam.polymermachine.api.PolymerMachineApi
import com.polymerteam.polymermachine.common.block.ModBlocks
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

object ModItems {
    val REGISTRY = KDeferredRegister(ForgeRegistries.ITEMS, PolymerMachineApi.MOD_ID)
}