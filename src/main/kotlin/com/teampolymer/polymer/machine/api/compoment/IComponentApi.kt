package com.teampolymer.polymer.machine.api.compoment

/**
 * 代表一个组件的api
 */
interface IComponentApi {
    /**
     * 将这个api应用于方块构造器
     */
    fun apply(builder: com.teampolymer.polymer.machine.api.block.IBlockBuilder)
}