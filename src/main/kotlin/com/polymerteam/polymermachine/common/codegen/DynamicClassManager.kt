package com.polymerteam.polymermachine.common.codegen

import com.polymerteam.polymermachine.api.codegen.GenerateTemplate

object DynamicClassManager {
    private val classes = mutableMapOf<String, ByteArray>()
    fun addClass(
        superClass: Class<*>,
        template: Class<*>?, name: String,
        vararg extensions: Class<*>
    ) {
        classes[name] = generateClass(name, superClass, template, *extensions)
    }

    fun addClass(superClass: Class<*>, name: String, vararg extensions: Class<*>) {
        val template = superClass.getAnnotation(GenerateTemplate::class.java)?.template?.java
        addClass(superClass, template, name, *extensions)
    }

    inline fun <reified T> addClass(name: String, vararg extensions: Class<*>) {
        addClass(T::class.java, name, *extensions)
    }

    class DynamicClassLoader(parent: ClassLoader?) : ClassLoader(parent) {
        private val customClasses: MutableMap<String, Class<*>> = HashMap()

        override fun loadClass(name: String): Class<*> {
            if (customClasses.containsKey(name)) return customClasses[name]!!
            if (classes.containsKey(name)) {
                val bytes = classes[name]
                customClasses[name] = defineClass(name, bytes, 0, bytes!!.size, null)
                return customClasses[name]!!
            }
            return parent.loadClass(name)
        }
    }
}