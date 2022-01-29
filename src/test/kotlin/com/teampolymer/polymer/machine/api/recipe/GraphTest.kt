package com.teampolymer.polymer.machine.api.recipe

import com.polymerteam.polymermachine.common.utils.Graph
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis


fun main() {
    measureTimeMillis {
        for (i in 0..100000) {
            val g = Graph(6)
            g.addEdge(0, 1, 16)
            g.addEdge(0, 2, 13)
            g.addEdge(1, 2, 10)
            g.addEdge(1, 3, 12)
            g.addEdge(2, 1, 4)
            g.addEdge(2, 4, 14)
            g.addEdge(3, 2, 9)
            g.addEdge(3, 5, 20)
            g.addEdge(4, 3, 7)
            g.addEdge(4, 5, 4)
            g.dinicMaxFlow(0, 5)
        }
    }.let {
        println(it)
    }


}
