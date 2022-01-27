package com.polymerteam.polymermachine.api.compoment

/**
 * 懒加载组件
 */
interface ILazyComponent<T>: IComponent<T> {
    /**
     * 计算组件的实际值
     */
    fun resolve()

    /**
     * 使计算的值武无效
     */
    fun invalidate()
}