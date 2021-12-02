package com.polymerteam.polymermachine.api.generic

import com.polymerteam.polymermachine.common.utils.newProperties
import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.material.Material
import net.minecraft.item.ItemStack
import net.minecraft.loot.LootContext
import net.minecraft.util.math.BlockPos
import net.minecraft.world.Explosion
import net.minecraft.world.World

class KotlinTestBlock : Block(newProperties(Material.STONE) {

}) {



    //TNT爆炸
    override fun wasExploded(p_180652_1_: World, p_180652_2_: BlockPos, p_180652_3_: Explosion) {
        super.wasExploded(p_180652_1_, p_180652_2_, p_180652_3_)
    }

    override fun onRemove(
        p_196243_1_: BlockState,
        p_196243_2_: World,
        p_196243_3_: BlockPos,
        p_196243_4_: BlockState,
        p_196243_5_: Boolean
    ) {
        super.onRemove(p_196243_1_, p_196243_2_, p_196243_3_, p_196243_4_, p_196243_5_)
    }

    override fun getDrops(blockstate: BlockState, ctx: LootContext.Builder): MutableList<ItemStack> {
        return super.getDrops(blockstate, ctx)
    }

}