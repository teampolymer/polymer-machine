package com.teampolymer.polymer.machine.api.recipe

import com.teampolymer.polymer.machine.api.recipe.entry.IRecipeEntry

class Recipe {
    private val entries: MutableMap<String, IRecipeEntry> = mutableMapOf()
    operator fun get(key: String): IRecipeEntry? = entries[key]

}