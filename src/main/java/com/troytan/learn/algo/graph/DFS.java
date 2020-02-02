package com.troytan.learn.algo.graph;

import com.troytan.learn.support.GraphNode;
import com.troytan.learn.support.GraphUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 深度优先遍历
 */
public class DFS {

    public static void dfsGraph(GraphNode node, Map<String, Boolean> visitMap) {
        if (visitMap.getOrDefault(node.getId(), false)) {
            //已遍历直接返回
            return;
        }
        visitMap.put(node.getId(), true);
        System.out.print(node.getId());
        for (GraphNode neighbor : node.getNeighbors()) {
            dfsGraph(neighbor, visitMap);
        }
    }

    public static void main(String[] args) {
        GraphNode[] graphNodes = GraphUtils.generateGraph1();

        for (GraphNode node : graphNodes) {
            //测试以每个节点作为起始节点遍历结果
            System.out.println("start from node:" + node.getId());
            Map<String, Boolean> visitMap = new HashMap<>();
            dfsGraph(node, visitMap);
            System.out.println();
        }
    }
}
