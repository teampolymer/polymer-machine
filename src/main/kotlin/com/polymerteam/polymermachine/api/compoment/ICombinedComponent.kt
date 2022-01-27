package com.polymerteam.polymermachine.api.compoment

/**
 * 代表一个复合组件
 */
interface ICombinedComponent<T>: ILazyComponent<T> {
    val children: Array<out IComponent<T>>
}