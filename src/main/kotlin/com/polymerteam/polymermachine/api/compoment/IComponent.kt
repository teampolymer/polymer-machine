package com.polymerteam.polymermachine.api.compoment

import com.polymerteam.polymermachine.api.network.IComponentNetHandler
import net.minecraft.nbt.CompoundNBT
import net.minecraftforge.common.util.INBTSerializable

/**
 * 代表一个组件
 */
interface IComponent<T> : INBTSerializable<CompoundNBT> {
    /**
     * 组件容纳的具体内容
     */
    val handler: T?

    /**
     * 组件的类型
     */
    val type: IComponentType<T>

    /**
     * 组件的网络管理器
     */
    val network: IComponentNetHandler
}