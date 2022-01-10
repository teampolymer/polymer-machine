package com.polymerteam.polymermachine.api.machine

import com.polymerteam.polymermachine.api.context.MachineContext
import net.minecraft.block.Block

interface CompliedMachine {
    val id: String
    fun tick(ctx: MachineContext)

    val block: Block
}