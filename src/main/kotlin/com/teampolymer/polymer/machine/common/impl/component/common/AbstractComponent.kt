package com.teampolymer.polymer.machine.common.impl.component.common

import com.teampolymer.polymer.machine.api.compoment.IComponent
import net.minecraft.tileentity.TileEntity
import java.util.function.Supplier

abstract class AbstractComponent<T>: IComponent<T> {
    override var attachedTile: Supplier<TileEntity?> = Supplier { null }
}

