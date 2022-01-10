package com.polymerteam.polymermachine.common.recipe

import net.minecraft.item.crafting.Ingredient

data class IngredientWithAmount(
    val ingredient: Ingredient,
    val amount: Int,
)