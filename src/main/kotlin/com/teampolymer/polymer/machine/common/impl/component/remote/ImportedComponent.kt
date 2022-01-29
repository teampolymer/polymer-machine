package com.teampolymer.polymer.machine.common.impl.component.remote

import com.teampolymer.polymer.machine.api.capability.COMPONENT_HOLDER
import com.teampolymer.polymer.machine.api.compoment.IComponentType
import net.minecraft.tileentity.TileEntity

class ImportedComponent<T>(type: IComponentType<T>) : AbstractRemoteComponent<T>(type) {

    override fun resolveHandler(targetTile: TileEntity): T? {
        val capability = targetTile.getCapability(COMPONENT_HOLDER)
        if (!capability.isPresent) return null
        return capability.resolve().get().get(type)?.handler
    }
}