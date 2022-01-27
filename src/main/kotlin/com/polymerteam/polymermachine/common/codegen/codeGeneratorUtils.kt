package com.polymerteam.polymermachine.common.codegen

import org.objectweb.asm.*
import org.objectweb.asm.Opcodes.*


fun generateClass(
    name: String, superClass: Class<*>, template: Class<*>?, vararg extensions: Class<*>
): ByteArray {
    if (template != null && !superClass.isAssignableFrom(template)) {
        //TODO: 抛异常
    }
    val superName = ClassReader(superClass.name).superName
    val templateReader = template?.name?.let { ClassReader(it) }
    val extensionReaders = extensions.map { ClassReader(it.name) }
    val output = ClassWriter(0)

    val interfaces = extensionReaders.flatMap { it.interfaces.asSequence() }.filterNotNull().toSet()
    val methods = mutableSetOf<String>()
    val fields = mutableSetOf<String>()

    if (templateReader != null) {
        val templateInterfaces = templateReader.interfaces.filterNotNull().toSet()
        val finalInterfaces = interfaces + templateInterfaces
        output.visit(
            V1_8, ACC_PUBLIC, name, null, superName, finalInterfaces.toTypedArray()
        )
        templateReader.accept(object : ClassVisitor(ASM7) {
            override fun visitMethod(
                access: Int, name: String, descriptor: String, signature: String?, exceptions: Array<out String>?
            ): MethodVisitor? {
                val methodName = name + descriptor
                if (methodName in methods) return null
                methods.add(methodName)
                return output.visitMethod(access, name, descriptor, signature, exceptions)
            }

            override fun visitField(
                access: Int, name: String, descriptor: String, signature: String?, value: Any?
            ): FieldVisitor? {
                if (name in fields) return null
                fields.add(name)
                return output.visitField(access, name, descriptor, signature, value)
            }
        }, ClassReader.SKIP_DEBUG)
    } else {
        output.visit(
            V1_8, ACC_PUBLIC, name, null, superName, interfaces.toTypedArray()
        )

        //没有template， 就生成默认无参构造函数
        val con: MethodVisitor = output.visitMethod(
            ACC_PUBLIC, "<init>", "()V", null, null
        )
        con.visitCode()
        con.visitVarInsn(ALOAD, 0) //load this
        con.visitMethodInsn(INVOKESPECIAL, superName, "<init>", "()V", false) // super()
        con.visitInsn(RETURN) //return
        con.visitMaxs(1, 1)    // Specify max stack and local vars
    }

    extensionReaders.forEach {
        it.accept(object : ClassVisitor(ASM7) {
            override fun visitMethod(
                access: Int, name: String, descriptor: String, signature: String?, exceptions: Array<out String>?
            ): MethodVisitor? {
                //跳过构造函数
                if ("<init>" == name) {
                    return null
                }
                val methodName = name + descriptor
                if (methodName in methods) return null
                methods.add(methodName)
                return output.visitMethod(access, name, descriptor, signature, exceptions)
            }

            override fun visitField(
                access: Int, name: String, descriptor: String, signature: String?, value: Any?
            ): FieldVisitor? {
                if (name in fields) return null
                fields.add(name)
                return output.visitField(access, name, descriptor, signature, value)
            }
        }, ClassReader.SKIP_DEBUG)
    }

    output.visitEnd()
    return output.toByteArray()

}
