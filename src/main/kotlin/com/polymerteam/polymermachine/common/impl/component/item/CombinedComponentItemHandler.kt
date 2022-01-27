package com.polymerteam.polymermachine.common.impl.component.item

import com.polymerteam.polymermachine.api.compoment.ICombinedComponent
import com.polymerteam.polymermachine.api.compoment.IComponent
import com.polymerteam.polymermachine.api.compoment.ILazyComponent
import com.polymerteam.polymermachine.common.impl.component.common.AbstractCombinedComponent
import com.polymerteam.polymermachine.common.impl.component.common.AbstractComponent
import com.polymerteam.polymermachine.common.impl.component.compat.CombinedItemHandlerWrapper
import net.minecraftforge.items.IItemHandler
import java.util.function.Consumer

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