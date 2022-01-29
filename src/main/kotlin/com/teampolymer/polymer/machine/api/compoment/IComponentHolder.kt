package com.teampolymer.polymer.machine.api.compoment

interface IComponentHolder {
    fun <T> get(type: IComponentType<T>): IComponent<T>?
}