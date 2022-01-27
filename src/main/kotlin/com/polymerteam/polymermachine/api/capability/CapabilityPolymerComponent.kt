package com.polymerteam.polymermachine.api.capability

import com.polymerteam.polymermachine.api.compoment.IComponentHolder
import net.minecraft.nbt.INBT
import net.minecraft.util.Direction
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityInject
import net.minecraftforge.common.capabilities.CapabilityManager


@CapabilityInject(IComponentHolder::class)
lateinit var COMPONENT_HOLDER: Capability<IComponentHolder>

fun registerCapabilities() {
    CapabilityManager.INSTANCE.register(IComponentHolder::class.java, object : Capability.IStorage<IComponentHolder> {
        override fun writeNBT(
            capability: Capability<IComponentHolder>?,
            instance: IComponentHolder?,
            side: Direction?
        ): INBT? {
            TODO("Not yet implemented")
        }

        override fun readNBT(
            capability: Capability<IComponentHolder>?,
            instance: IComponentHolder?,
            side: Direction?,
            nbt: INBT?
        ) {
            TODO("Not yet implemented")
        }

    }) { null }
}