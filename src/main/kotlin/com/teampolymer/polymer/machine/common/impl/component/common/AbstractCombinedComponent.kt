package com.teampolymer.polymer.machine.common.impl.component.common

import com.teampolymer.polymer.machine.api.compoment.ICombinedComponent
import com.teampolymer.polymer.machine.api.compoment.IComponent
import com.teampolymer.polymer.machine.api.compoment.ILazyComponent
import java.util.function.Consumer

abstract class AbstractCombinedComponent<T>(components: Array<out IComponent<T>>) :
    AbstractComponent<T>(), ICombinedComponent<T> {

    protected var listeners = mutableListOf<Consumer<ILazyComponent<T>>>()
    override var isValid: Boolean = false
        protected set
    final override var handler: T? = null
        protected set
    final override val children: Array<IComponent<T>>

    init {
        val result = ArrayList<IComponent<T>>(components.size)
        components.forEach {
            if (it is ICombinedComponent) {
                it.children.forEach { ch ->
                    result.add(ch)
                    (ch as? ILazyComponent)?.addListener { this.invalidate() }
                }
            } else {
                result.add(it)
            }
            (it as? ILazyComponent)?.addListener { this.invalidate() }
        }
        this.children = result.toTypedArray()
    }

    override fun resolve(): Boolean {
        if (isValid) {
            return true
        }
        handler = createWrapper() ?: return false
        isValid = true
        return true
    }

    abstract fun createWrapper(): T?

    override fun invalidate() {
        if (!isValid) {
            return
        }
        isValid = false
        listeners.forEach { it.accept(this) }
        listeners.clear()
        handler = null
    }

    override fun addListener(listener: Consumer<ILazyComponent<T>>) {
        listeners.add(listener)
    }

}