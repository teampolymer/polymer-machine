package com.polymerteam.polymermachine.common.impl.component.common

import com.polymerteam.polymermachine.api.compoment.IComponent
import net.minecraft.tileentity.TileEntity
import java.util.function.Supplier

abstract class AbstractComponent<T>: IComponent<T> {
    override var attachedTile: Supplier<TileEntity?> = Supplier { null }
}

