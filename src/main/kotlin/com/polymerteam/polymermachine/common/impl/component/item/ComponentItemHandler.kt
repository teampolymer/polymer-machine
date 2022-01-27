package com.polymerteam.polymermachine.common.impl.component.item

import com.polymerteam.polymermachine.api.compoment.IComponent
import com.polymerteam.polymermachine.api.compoment.IComponentType
import com.polymerteam.polymermachine.api.compoment.IConcreteComponent
import com.polymerteam.polymermachine.api.network.IComponentNetHandler
import net.minecraft.nbt.CompoundNBT
import net.minecraft.nbt.INBT
import net.minecraftforge.items.IItemHandler

class ComponentItemHandler(
    override val handler: IItemHandler?,
    override val type: IComponentType<IItemHandler>,
    override val network: IComponentNetHandler
) : IConcreteComponent<IItemHandler> {
    override fun createHandler(): IItemHandler {
        TODO("Not yet implemented")
    }

    override fun destroyHandler(drop: Boolean) {
        TODO("Not yet implemented")
    }

    override fun serializeContent(): INBT {
        TODO("Not yet implemented")
    }

    override fun deserializeContent(nbt: INBT?) {
        TODO("Not yet implemented")
    }

    override fun serializeNBT(): CompoundNBT {
        TODO("Not yet implemented")
    }

    override fun deserializeNBT(nbt: CompoundNBT?) {
        TODO("Not yet implemented")
    }
}