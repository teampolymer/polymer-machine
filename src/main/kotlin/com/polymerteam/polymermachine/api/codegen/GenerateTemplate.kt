package com.polymerteam.polymermachine.api.codegen

import kotlin.reflect.KClass

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class GenerateTemplate(val template: KClass<*>)
