package com.polymerteam.polymermachine.api.compoment

/**
 * 组件类型
 */
interface IComponentType<T> {
    @Suppress("UNCHECKED_CAST")
    fun cast(obj: Any): T = obj as T

    fun validate(vararg components: IComponent<T>) = components.all { validate(it.type) }
    fun validate(other: IComponentType<T>) = this.type.isAssignableFrom(other.type)

    val type: Class<T>

    /**
     * 该种类型组件需要实现的api
     */
    val apis: Array<IComponentApi>
}