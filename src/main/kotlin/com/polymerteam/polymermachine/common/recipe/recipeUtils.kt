package com.polymerteam.polymermachine.common.recipe

import com.polymerteam.polymermachine.api.recipe.IngredientWithAmount
import com.polymerteam.polymermachine.common.utils.Graph
import net.minecraft.inventory.IInventory
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.item.crafting.Ingredient


fun matchRecipe(recipe: Array<IngredientWithAmount>, inputInventory: Inventory): MutableList<RecipeMatchResult>? {
    //顶点下标
    var index = 1

    val vertIngredients = recipe.map { VertexIngredient(index++, it) }
    val vertItemBeginIndex = index
    val vertItemStacks = mutableListOf<VertexItemInput>()
    for (i in 0 until inputInventory.containerSize) {
        val item = inputInventory.getItem(i)
        if (item.isEmpty) {
            val itemInput = VertexItemInput(index++, item, i)
            vertItemStacks.add(itemInput)
        }
    }

    //容器内的物品
    val vertexNumber = vertIngredients.size + vertItemStacks.size + 2
    val graph = Graph(vertexNumber)

    //源点
    val vertSource = 0
    //汇点
    val vertTarget = vertexNumber - 1

    //边

    var totalInputNumbers = 0
    vertIngredients.forEach {
        totalInputNumbers += it.number
        graph.addEdge(vertSource, it.index, it.number)
    }

    vertItemStacks.forEach {
        graph.addEdge(it.index, vertTarget, it.data.count)
    }
    for (vertIngredient in vertIngredients) {
        var hasIngredient = false
        for (vertItemStack in vertItemStacks) {
            if (vertIngredient.data.test(vertItemStack.data)) {
                hasIngredient = true
                graph.addEdge(vertIngredient.index, vertItemStack.index, Int.MAX_VALUE)
            }
        }
        if (!hasIngredient)
            return null

    }

    val maxFlow = graph.dinicMaxFlow(vertSource, vertTarget)
    if (maxFlow != totalInputNumbers) {
        return null
    }

    //获取消耗的物品（最终图边的流）

    //连着汇点的边
    val result = mutableListOf<RecipeMatchResult>()
    for (reverseEdge in graph.adjacent[vertTarget]) {
        val edge = graph.adjacent[reverseEdge.v][reverseEdge.rev]
        val flow = edge.flow - reverseEdge.flow
        val vert = vertItemStacks[reverseEdge.v - vertItemBeginIndex]
        if (flow > 0) {
            result.add(RecipeMatchResult(vert.slot, flow))
        }
    }

    return result.ifEmpty { null }

}


fun List<RecipeMatchResult>.doConsume(inventory: IInventory) {
    this.forEach {
        inventory.removeItem(it.slot, it.count)
    }
}

open class RecipeMatchResult(
    val slot: Int,
    val count: Int
)


data class VertexIngredient(
    val index: Int,
    val data: Ingredient,
    val number: Int = 1,
) {
    constructor(index: Int, ingredient: IngredientWithAmount) : this(
        index,
        ingredient.ingredient,
        ingredient.amount
    )
}

data class VertexItemInput(
    val index: Int,
    val data: ItemStack,
    val slot: Int
)
