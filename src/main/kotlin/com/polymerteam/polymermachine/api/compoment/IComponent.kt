package com.polymerteam.polymermachine.api.compoment

import com.polymerteam.polymermachine.api.network.IComponentNetHandler
import net.minecraft.tileentity.TileEntity
import java.util.function.Supplier

/**
 * 代表一个组件
 */
interface IComponent<T> {
    /**
     * 组件容纳的具体内容
     */
    val handler: T?

    /**
     * 组件的类型
     */
    val type: IComponentType<T>

    var attachedTile: Supplier<TileEntity?>

    /**
     * 组件的网络管理器
     */
    val network: IComponentNetHandler
}