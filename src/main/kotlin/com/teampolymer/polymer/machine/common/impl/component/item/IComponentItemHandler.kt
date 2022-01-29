package com.teampolymer.polymer.machine.common.impl.component.item

import com.teampolymer.polymer.machine.api.compoment.IComponent
import com.teampolymer.polymer.machine.api.compoment.IComponentType
import com.teampolymer.polymer.machine.api.network.IComponentNetHandler
import net.minecraftforge.items.IItemHandler

interface IComponentItemHandler : IComponent<IItemHandler> {
    override val type: IComponentType<IItemHandler>
        get() = ComponentTypeItemHandler
    override val network: IComponentNetHandler
        get() = TODO("Not yet implemented")

}