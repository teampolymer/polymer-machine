package com.teampolymer.polymer.machine.common.impl.component.item

import com.teampolymer.polymer.machine.api.compoment.ICombinedComponent
import com.teampolymer.polymer.machine.api.compoment.IComponent
import com.teampolymer.polymer.machine.common.impl.component.common.AbstractCombinedComponent
import com.teampolymer.polymer.machine.common.impl.component.compat.CombinedItemHandlerWrapper
import net.minecraftforge.items.IItemHandler

class CombinedComponentItemHandler(children: Array<out IComponent<IItemHandler>>) :
    AbstractCombinedComponent<IItemHandler>(children), IComponentItemHandler, ICombinedComponent<IItemHandler> {
    override fun createWrapper(): IItemHandler? {
        val handlers = this.children.mapNotNull { it.handler }
        if (handlers.isEmpty()) {
            return null
        }
        return CombinedItemHandlerWrapper(handlers)
    }

}