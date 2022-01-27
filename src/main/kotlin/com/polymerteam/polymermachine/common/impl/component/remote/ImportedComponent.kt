package com.polymerteam.polymermachine.common.impl.component.remote

import com.polymerteam.polymermachine.api.capability.COMPONENT_HOLDER
import com.polymerteam.polymermachine.api.compoment.IComponentType
import net.minecraft.tileentity.TileEntity

class ImportedComponent<T>(type: IComponentType<T>) : AbstractRemoteComponent<T>(type) {

    override fun resolveHandler(targetTile: TileEntity): T? {
        val capability = targetTile.getCapability(COMPONENT_HOLDER)
        if (!capability.isPresent) return null
        return capability.resolve().get().get(type)?.handler
    }
}