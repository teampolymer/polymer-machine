package com.polymerteam.polymermachine.api.compoment

interface IComponentTypeCombinable<T> : IComponentType<T> {
    fun combine(vararg components: IComponent<T>): ICombinedComponent<T>?
}