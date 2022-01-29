package com.teampolymer.polymer.machine.api.machine

import com.teampolymer.polymer.machine.api.context.MachineContext
import net.minecraft.block.Block

interface CompliedMachine {
    val id: String
    fun tick(ctx: MachineContext)

    val block: Block
}