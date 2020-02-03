package com.troytan.learn.support;

import java.util.ArrayList;
import java.util.List;

/**
 * 图的工具类
 *
 * @Author troytan
 * @Date 2/3/2020
 */
public class GraphUtils {
    /**
     * 生成用邻接表 表示的图
     * 有环图，测试dfs遍历
     *
     * @return
     */
    public static GraphNode[] generateGraph1() {
        GraphNode[] graphNodes = new GraphNode[5];
        for (int i = 0; i < graphNodes.length; i++) {
            graphNodes[i] = new GraphNode(i + "");
        }
        addNeighbors(graphNodes[0], graphNodes[2], graphNodes[4]);
        addNeighbors(graphNodes[1], graphNodes[0], graphNodes[2]);
        addNeighbors(graphNodes[2], graphNodes[3]);
        addNeighbors(graphNodes[3], graphNodes[4]);
        addNeighbors(graphNodes[4], graphNodes[3]);

        return graphNodes;
    }

    /**
     * 生成无环图，测试dag排序
     *
     * @return
     */
    public static GraphNode[] generateGraph2() {
        GraphNode[] graphNodes = new GraphNode[6];
        for (int i = 0; i < graphNodes.length; i++) {
            graphNodes[i] = new GraphNode(i + "");
            graphNodes[i].setNeighbors(new ArrayList<>());
        }
        addNeighbors(graphNodes[0], graphNodes[2], graphNodes[4]);
        addNeighbors(graphNodes[1], graphNodes[0], graphNodes[2]);
        addNeighbors(graphNodes[2], graphNodes[3]);
        addNeighbors(graphNodes[3], graphNodes[4]);

        return graphNodes;
    }

    /**
     * 添加邻接表
     *
     * @param node
     * @param nodes
     */
    private static void addNeighbors(GraphNode node, GraphNode... nodes) {
        List<GraphNode> neighbors = node.getNeighbors();
        for (GraphNode neighbor : nodes) {
            neighbors.add(neighbor);
        }

    }

}
