package com.polymerteam.polymermachine.api.compoment

/**
 * 组件类型
 */
interface IComponentType<T> {
    @Suppress("UNCHECKED_CAST")
    fun cast(obj: Any): T = obj as T

    val type: Class<T>

    /**
     * 该种类型组件需要实现的api
     */
    val apis: Array<IComponentApi>
}