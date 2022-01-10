package com.polymerteam.polymermachine.api.recipe

import net.minecraft.item.crafting.Ingredient

interface IIngredient {
    fun toVanilla(): Ingredient
}