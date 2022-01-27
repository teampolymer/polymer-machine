package com.polymerteam.polymermachine.common.impl.component.item

import com.polymerteam.polymermachine.api.compoment.IConcreteComponent
import com.polymerteam.polymermachine.common.impl.component.common.AbstractComponent
import net.minecraft.inventory.InventoryHelper
import net.minecraft.nbt.CompoundNBT
import net.minecraft.nbt.INBT
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.ItemStackHandler

class ComponentItemHandler(var size: Int) : AbstractComponent<IItemHandler>(), IComponentItemHandler,
    IConcreteComponent<IItemHandler> {
    private var handlerInstance: ItemStackHandler? = null
    override val handler: IItemHandler
        get() {
            if (handlerInstance == null) {
                createHandler()
            }
            return handlerInstance!!
        }


    override fun createHandler(): IItemHandler {
        handlerInstance = ItemStackHandler(size)
        return handlerInstance!!
    }

    override fun destroyHandler(drop: Boolean) {
        if (drop) attachedTile.get()?.let {
            if (it.level != null && handlerInstance != null) {
                (0 until handler.slots).forEach { i ->
                    InventoryHelper.dropItemStack(
                        it.level!!,
                        it.blockPos.x.toDouble(),
                        it.blockPos.y.toDouble(),
                        it.blockPos.z.toDouble(),
                        handler.getStackInSlot(i)
                    )
                }
            }
        }
    }

    override fun serializeNBT(): INBT {
        return handlerInstance?.serializeNBT() ?: CompoundNBT()
    }

    override fun deserializeNBT(nbt: INBT) {
        handlerInstance?.deserializeNBT(nbt as CompoundNBT)
    }

}