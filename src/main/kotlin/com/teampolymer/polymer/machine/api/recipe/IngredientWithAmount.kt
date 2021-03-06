package com.teampolymer.polymer.machine.api.recipe

import net.minecraft.item.crafting.Ingredient

data class IngredientWithAmount(
    val ingredient: Ingredient,
    val amount: Int,
) : IIngredient {
    override fun toVanilla(): Ingredient = ingredient
}