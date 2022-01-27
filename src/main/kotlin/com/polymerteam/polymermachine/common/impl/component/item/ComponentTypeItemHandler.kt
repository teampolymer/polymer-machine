package com.polymerteam.polymermachine.common.impl.component.item

import com.polymerteam.polymermachine.api.compoment.IComponentApi
import com.polymerteam.polymermachine.api.compoment.IComponentType
import net.minecraftforge.items.IItemHandler

class ComponentTypeItemHandler: IComponentType<IItemHandler> {
    override val type: Class<IItemHandler>
        get() = IItemHandler::class.java
    override val apis: Array<IComponentApi>
        get() = emptyArray()
}