package com.polymerteam.polymermachine.api.compoment

import net.minecraft.util.math.BlockPos

/**
 * 远程组件（多方快结构中位于其他方块的组件）
 */
interface IRemoteComponent<T>: ILazyComponent<T> {
    var targetPos: BlockPos?
}