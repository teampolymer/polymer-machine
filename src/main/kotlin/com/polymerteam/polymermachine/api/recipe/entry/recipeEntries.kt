package com.polymerteam.polymermachine.api.recipe.entry

import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient
import net.minecraftforge.fluids.FluidStack

class IntEntry(val value: Int) : IRecipeEntry

class DoubleEntry(val value: Double) : IRecipeEntry

class StringEntry(val value: String) : IRecipeEntry

class ItemStackEntry(val value: ItemStack) : IRecipeEntry

class IngredientEntry(val value: Ingredient) : IRecipeEntry

class FluidStackEntry(val value: FluidStack) : IRecipeEntry

class IngredientListEntry(val value: MutableList<Ingredient>)

class ItemStackListEntry(val value: MutableList<ItemStack>)

class FluidStackListEntry(val value: MutableList<FluidStack>)

