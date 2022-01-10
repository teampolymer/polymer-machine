package com.polymerteam.polymermachine.api.recipe

import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient

class IngredientTransformed(
    val ingredient: Ingredient,
    val transformer: (ItemStack) -> ItemStack,
): IIngredient {
    override fun toVanilla(): Ingredient = ingredient
}