package com.polymerteam.polymermachine.common.utils

import kotlin.math.min


data class Edge(
    // Vertex v (or "to" vertex)
    // of a directed edge u-v. "From"
    // vertex u can be obtained using
    // index in adjacent array.
    val v: Int,
    // flow of data in edge
    var flow: Int,
    // capacity
    val c: Int,
    // To store index of reverse
    // edge in adjacency list so that
    // we can quickly find it.
    val rev: Int,
)

/**
 * Dinic算法的网络流图
 */
class Graph(private val vertexNumber: Int) {

    //图的分层
    private val levels: IntArray = IntArray(vertexNumber) { -1 }

    //图的边
    val adjacent: Array<MutableList<Edge>> = Array(vertexNumber) { mutableListOf() }

    // add edge to the graph
    /**
     * 向图添加一对边
     * @return 添加的正向边 index
     */
    fun addEdge(u: Int, v: Int, c: Int) {
        // Forward edge : 0 flow and C capacity
        val a = Edge(v, 0, c, adjacent[v].size)

        // Back edge : 0 flow and 0 capacity
        val b = Edge(u, 0, 0, adjacent[u].size)

        adjacent[u].add(a)
        adjacent[v].add(b) // reverse edge
    }

    // Finds if more flow can be sent from s to t.
    // Also assigns levels to nodes.
    fun bfs(s: Int, t: Int): Boolean {
        for (i in levels.indices)
            levels[i] = -1

        levels[s] = 0  // Level of source vertex

        // Create a queue, enqueue source vertex
        // and mark source vertex as visited here
        // level[] array works as visited array also.
        val queue = ArrayDeque<Int>()
        queue.add(s)
        while (queue.isNotEmpty()) {
            val u = queue.removeLast()

            for (edge in adjacent[u]) {
                if (levels[edge.v] < 0 && edge.flow < edge.c) {
                    // Level of current vertex is,
                    // level of parent + 1
                    levels[edge.v] = levels[u] + 1
                    queue.add(edge.v)
                }
            }
        }

        // IF we can not reach to the sink we
        // return false else true
        return levels[t] >= 0
    }

    // A DFS based function to send flow after BFS has
    // figured out that there is a possible flow and
    // constructed levels. This function called multiple
    // times for a single call of BFS.
    // flow : Current flow send by parent function call
    // start[] : To keep track of next edge to be explored.
    //           start[i] stores  count of edges explored
    //           from i.
    //  u : Current vertex
    //  t : Sink
    fun sendFlow(u: Int, flow: Int, t: Int, start: IntArray): Int {
        // Sink reached
        if (u == t)
            return flow

        // Traverse all adjacent edges one -by - one.
        while (start[u] < adjacent[u].size) {
            val edge = adjacent[u][start[u]]
            if (levels[edge.v] == levels[u] + 1 && edge.flow < edge.c) {
                // find minimum flow from u to t
                val currFlow = min(flow, edge.c - edge.flow)
                val tempFlow = sendFlow(edge.v, currFlow, t, start)

                // flow is greater than zero
                if (tempFlow > 0) {
                    // add flow  to current edge
                    edge.flow += tempFlow
                    // subtract flow from reverse edge
                    // of current edge
                    adjacent[edge.v][edge.rev].flow -= tempFlow
                    return tempFlow;
                }
            }
            start[u]++
        }
        return 0
    }

    // Returns maximum flow in graph
    fun dinicMaxFlow(s: Int, t: Int): Int {
        // Corner case
        if (s == t)
            return -1

        var total = 0  // Initialize result

        // Augment the flow while there is path
        // from source to sink
        while (bfs(s, t)) {
            // store how many edges are visited
            // from V { 0 to V }
            val start = IntArray(vertexNumber + 1) { 0 }

            // while flow is not zero in graph from S to D
            var flow = 0
            do {
                // Add path flow to overall flow
                total += flow
                flow = sendFlow(s, Int.MAX_VALUE, t, start)
            } while (flow != 0)
        }

        // return maximum flow
        return total
    }

}