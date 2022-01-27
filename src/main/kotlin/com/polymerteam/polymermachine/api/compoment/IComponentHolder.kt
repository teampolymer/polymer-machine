package com.polymerteam.polymermachine.api.compoment

interface IComponentHolder {
    fun <T> get(type: IComponentType<T>): IComponent<T>?
}