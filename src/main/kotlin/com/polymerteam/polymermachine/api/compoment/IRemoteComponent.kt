package com.polymerteam.polymermachine.api.compoment

/**
 * 远程组件（多方快结构中位于其他方块的组件）
 */
interface IRemoteComponent<T>: ILazyComponent<T> {
    val target: IComponent<T>?
}