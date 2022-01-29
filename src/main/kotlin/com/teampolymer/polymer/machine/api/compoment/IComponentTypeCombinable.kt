package com.teampolymer.polymer.machine.api.compoment

interface IComponentTypeCombinable<T> : IComponentType<T> {
    fun combine(vararg components: IComponent<T>): ICombinedComponent<T>?
}