package com.polymerteam.polymermachine.api.compoment

import com.polymerteam.polymermachine.api.block.IBlockBuilder

/**
 * 代表一个组件的api
 */
interface IComponentApi {
    /**
     * 将这个api应用于方块构造器
     */
    fun apply(builder: IBlockBuilder)
}