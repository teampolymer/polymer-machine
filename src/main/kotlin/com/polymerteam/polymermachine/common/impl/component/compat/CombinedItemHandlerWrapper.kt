package com.polymerteam.polymermachine.common.impl.component.compat

import net.minecraft.item.ItemStack
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.wrapper.EmptyHandler
import javax.annotation.Nonnull

// combines multiple IItemHandlerModifiable into one interface
open class CombinedItemHandlerWrapper(private val itemHandler: List<IItemHandler>) : IItemHandler {
    private val baseIndex // index-offsets of the different handlers
            : IntArray = IntArray(itemHandler.size)
    private val slotCount // number of total slots
            : Int

    init {
        var index = 0
        for (i in itemHandler.indices) {
            index += itemHandler[i].slots
            baseIndex[i] = index
        }
        slotCount = index
    }

    // returns the handler index for the slot
    private fun getIndexForSlot(slot: Int): Int {
        if (slot < 0) return -1
        for (i in baseIndex.indices) {
            if (slot - baseIndex[i] < 0) {
                return i
            }
        }
        return -1
    }

    private fun getHandlerFromIndex(index: Int): IItemHandler {
        return if (index < 0 || index >= itemHandler.size) {
            EmptyHandler.INSTANCE as IItemHandler
        } else itemHandler[index]
    }

    private fun getSlotFromIndex(slot: Int, index: Int): Int {
        return if (index <= 0 || index >= baseIndex.size) {
            slot
        } else slot - baseIndex[index - 1]
    }

    override fun getSlots(): Int {
        return slotCount
    }

    @Nonnull
    override fun getStackInSlot(slot: Int): ItemStack {
        var slot = slot
        val index = getIndexForSlot(slot)
        val handler = getHandlerFromIndex(index)
        slot = getSlotFromIndex(slot, index)
        return handler.getStackInSlot(slot)
    }

    @Nonnull
    override fun insertItem(slot: Int, @Nonnull stack: ItemStack, simulate: Boolean): ItemStack {
        var slot = slot
        val index = getIndexForSlot(slot)
        val handler = getHandlerFromIndex(index)
        slot = getSlotFromIndex(slot, index)
        return handler.insertItem(slot, stack, simulate)
    }

    @Nonnull
    override fun extractItem(slot: Int, amount: Int, simulate: Boolean): ItemStack {
        var slot = slot
        val index = getIndexForSlot(slot)
        val handler = getHandlerFromIndex(index)
        slot = getSlotFromIndex(slot, index)
        return handler.extractItem(slot, amount, simulate)
    }

    override fun getSlotLimit(slot: Int): Int {
        val index = getIndexForSlot(slot)
        val handler = getHandlerFromIndex(index)
        val localSlot = getSlotFromIndex(slot, index)
        return handler.getSlotLimit(localSlot)
    }

    override fun isItemValid(slot: Int, @Nonnull stack: ItemStack): Boolean {
        val index = getIndexForSlot(slot)
        val handler = getHandlerFromIndex(index)
        val localSlot = getSlotFromIndex(slot, index)
        return handler.isItemValid(localSlot, stack)
    }
}
