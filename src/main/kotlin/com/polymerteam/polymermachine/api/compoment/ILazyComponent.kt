package com.polymerteam.polymermachine.api.compoment

import java.util.function.Consumer

/**
 * 懒加载组件
 */
interface ILazyComponent<T> : IComponent<T> {
    /**
     * 计算组件的实际值
     */
    fun resolve(): Boolean

    /**
     * 使计算的值无效
     */
    fun invalidate()

    val isValid: Boolean

    fun addListener(listener: Consumer<ILazyComponent<T>>)

}