package com.polymerteam.polymermachine.common.impl.component.item

import com.polymerteam.polymermachine.api.compoment.*
import net.minecraftforge.items.IItemHandler

object ComponentTypeItemHandler : IComponentTypeCombinable<IItemHandler> {
    override val type: Class<IItemHandler>
        get() = IItemHandler::class.java
    override val apis: Array<IComponentApi>
        get() = emptyArray()

    override fun combine(vararg components: IComponent<IItemHandler>): ICombinedComponent<IItemHandler>? {
        if (!validate(*components)) {
            return null
        }
        return CombinedComponentItemHandler(components)
    }
}