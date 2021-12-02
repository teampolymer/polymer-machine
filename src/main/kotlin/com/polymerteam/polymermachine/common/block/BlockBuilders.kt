package com.polymerteam.polymermachine.common.block

import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.material.Material


fun Block(material: Material, propertyBuilder: AbstractBlock.Properties.() -> Unit): Block {
    return net.minecraft.block.Block(AbstractBlock.Properties.of(material).apply(propertyBuilder))
}
