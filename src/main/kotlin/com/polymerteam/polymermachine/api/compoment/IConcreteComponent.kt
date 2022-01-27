package com.polymerteam.polymermachine.api.compoment

import net.minecraft.nbt.INBT
import net.minecraftforge.common.util.INBTSerializable

/**
 * 具体的一个组件
 */
interface IConcreteComponent<T> : IComponent<T>, INBTSerializable<INBT> {
    fun createHandler(): T
    fun destroyHandler(drop: Boolean)
}