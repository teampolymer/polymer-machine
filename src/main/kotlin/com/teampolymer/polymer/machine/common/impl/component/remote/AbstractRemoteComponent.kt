package com.teampolymer.polymer.machine.common.impl.component.remote

import com.teampolymer.polymer.machine.api.compoment.IComponentType
import com.teampolymer.polymer.machine.api.compoment.ILazyComponent
import com.teampolymer.polymer.machine.api.compoment.IRemoteComponent
import com.teampolymer.polymer.machine.api.network.IComponentNetHandler
import com.teampolymer.polymer.machine.common.impl.component.common.AbstractComponent
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import java.util.function.Consumer

abstract class AbstractRemoteComponent<T>(
    override val type: IComponentType<T>
) : AbstractComponent<T>(), IRemoteComponent<T> {
    override var handler: T? = null
        protected set
    override var targetPos: BlockPos? = null
    override var isValid = false
    protected var listeners = mutableListOf<Consumer<ILazyComponent<T>>>()

    override val network: IComponentNetHandler
        get() = TODO("Not yet implemented")

    override fun resolve(): Boolean {
        if (isValid) {
            return true
        }
        if (targetPos == null) return false
        val level = attachedTile.get()?.level ?: return false
        val targetTE = level.getBlockEntity(targetPos!!) ?: return false
        handler = resolveHandler(targetTE) ?: return false
        isValid = true
        return true
    }

    abstract fun resolveHandler(targetTile: TileEntity): T?

    override fun invalidate() {
        if (!isValid) {
            return
        }
        isValid = false
        listeners.forEach { it.accept(this) }
        listeners.clear()
        handler = null
    }

    override fun addListener(listener: Consumer<ILazyComponent<T>>) {
        listeners.add(listener)
    }

}