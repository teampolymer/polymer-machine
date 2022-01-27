package com.polymerteam.polymermachine.api.compoment

import net.minecraft.nbt.INBT

/**
 * 具体的一个组件
 */
interface IConcreteComponent<T> : IComponent<T> {
    fun createHandler(): T
    fun destroyHandler(drop: Boolean)
    fun serializeContent(): INBT
    fun deserializeContent(nbt: INBT?)
}