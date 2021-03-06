package com.teampolymer.polymer.machine.common.utils

import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.material.Material


fun Block(material: Material, propertyBuilder: AbstractBlock.Properties.() -> Unit): Block {
    return net.minecraft.block.Block(AbstractBlock.Properties.of(material).apply(propertyBuilder))
}

fun newProperties(p_200945_0_: Material, propertyBuilder: AbstractBlock.Properties.() -> Unit): AbstractBlock.Properties {
    return AbstractBlock.Properties.of(p_200945_0_).apply(propertyBuilder)
}

