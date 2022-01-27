package com.polymerteam.polymermachine.api.recipe

import com.polymerteam.polymermachine.api.recipe.entry.IRecipeEntry

class Recipe {
    private val entries: MutableMap<String, IRecipeEntry> = mutableMapOf()
    operator fun get(key: String): IRecipeEntry? = entries[key]

}