package com.polymerteam.polymermachine.common.impl.component.item

import com.polymerteam.polymermachine.api.compoment.IComponent
import com.polymerteam.polymermachine.api.compoment.IComponentType
import com.polymerteam.polymermachine.api.network.IComponentNetHandler
import net.minecraft.nbt.CompoundNBT
import net.minecraftforge.items.IItemHandler

interface IComponentItemHandler : IComponent<IItemHandler> {
    override val type: IComponentType<IItemHandler>
        get() = ComponentTypeItemHandler
    override val network: IComponentNetHandler
        get() = TODO("Not yet implemented")

}