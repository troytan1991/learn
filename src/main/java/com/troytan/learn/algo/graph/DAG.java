package com.troytan.learn.algo.graph;

import com.troytan.learn.support.GraphNode;
import com.troytan.learn.support.GraphUtils;

import java.util.*;

/**
 * Directed Acyclic Graph: 拓扑排序
 */
public class DAG {


    /**
     * 拓扑排序具体实现
     *
     * @param graphNodes 待排序的图
     * @return
     * @throws Exception
     */
    public static List<GraphNode> dagSort(GraphNode[] graphNodes) throws Exception {
        List<GraphNode> result = new ArrayList<>();
        //访问状态map
        Map<String, Boolean> visitMap = new HashMap<>();
        //入度map
        Map<String, Integer> inCountMap = new HashMap<>();

        //计算初始状态的各节点入度
        for (GraphNode node : graphNodes) {
            List<GraphNode> neighbors = node.getNeighbors();
            for (GraphNode neighbor : neighbors) {
                inCountMap.put(neighbor.getId(), inCountMap.getOrDefault(neighbor.getId(), 0) + 1);
            }
        }
        //辅助队列，存储入度为0的节点
        Queue<GraphNode> queue = new LinkedList<>();
        for (GraphNode graphNode : graphNodes) {
            if (inCountMap.getOrDefault(graphNode.getId(), 0) == 0) {
                queue.add(graphNode);
            }
        }

        while (!queue.isEmpty()) {
            GraphNode node = queue.poll();
            visitMap.put(node.getId(), true);
            result.add(node);
            for (GraphNode neighbor : node.getNeighbors()) {
                //将邻接节点入度减1
                String id = neighbor.getId();
                int inCount = inCountMap.get(id) - 1;
                inCountMap.put(id, inCount);
                //当入度为0时，进入队列
                if (inCount == 0 && !visitMap.getOrDefault(id, false)) {
                    queue.add(neighbor);
                }
            }
        }
        //还有未遍历的节点，则图中存在环
        for (GraphNode graphNode : graphNodes) {
            if (!visitMap.getOrDefault(graphNode.getId(), false)) {
                throw new Exception("存在环，无法排序");
            }
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        GraphNode[] graphNodes = GraphUtils.generateGraph2();
        List<GraphNode> result = dagSort(graphNodes);
        for (GraphNode graphNode : result) {
            System.out.print(graphNode.getId());
        }
    }
}
