package com.teampolymer.polymer.machine.api.recipe

import net.minecraft.item.crafting.Ingredient

interface IIngredient {
    fun toVanilla(): Ingredient
}