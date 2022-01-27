package com.polymerteam.polymermachine.common.item

import com.polymerteam.polymermachine.PolymerMachine
import com.polymerteam.polymermachine.api.PolymerMachineApi
import com.polymerteam.polymermachine.common.block.ModBlocks
import com.polymerteam.polymermachine.common.utils.codegen.generateCode
import com.polymerteam.polymermachine.common.utils.codegen.proto.BaseMachineBlock
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.KDeferredRegister

object ModItems {
    val REGISTRY = KDeferredRegister(ForgeRegistries.ITEMS, PolymerMachineApi.MOD_ID)

    val EXAMPLE_ITEM by REGISTRY.registerObject("test_block") {
        BlockItem(ModBlocks.EXAMPLE_BLOCK, Item.Properties().tab(ItemGroup.TAB_MISC))
    }
}