package com.troytan.learn.algo.graph;

import com.troytan.learn.support.GraphNode;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 任务排序试题
 *
 * @Author troytan
 * @Date 2/3/2020
 */
public class TaskSchedual {

    public static void main(String[] args) throws Exception {
        TaskSchedual taskSchedual = new TaskSchedual();
        //T1->T2表示T1依赖T2任务执行完成
        String input1 = "T1->T2,T2->T3,T2->T4,T3->T4,T4->T5,T6";
        String input2 = "T1->T2,T2->T3,T3->T1";
        String input3 = "K1,K2";
        List<GraphNode> nodes = taskSchedual.sortTask(input1);
        System.out.println(nodes.stream().map(node -> node.getId()).collect(Collectors.joining(",")));
    }

    /**
     * 实现方法
     *
     * @param input
     * @return
     * @throws Exception
     */
    public List<GraphNode> sortTask(String input) throws Exception {
        List<GraphNode> result = new ArrayList<>();
        //校验输入
        validate(input);
        //生成有向图
        HashMap<String, GraphNode> graph = mapToGraph(input);
        //访问map
        Map<String, Boolean> visitMap = new HashMap<>();
        //入度map
        Map<String, Integer> inCountMap = new HashMap<>();
        //遍历计算各节点的初始入度
        for (GraphNode node : graph.values()) {
            if (inCountMap.get(node.getId()) == null) {
                inCountMap.put(node.getId(), 0);
            }
            for (GraphNode neighbor : node.getNeighbors()) {
                int inCount = inCountMap.getOrDefault(neighbor.getId(), 0) + 1;
                inCountMap.put(neighbor.getId(), inCount);
            }
        }
        //两个辅助队列，解决入度都为0时的排序问题
        //当前入度为0的队列
        Queue<GraphNode> queueCur = new PriorityQueue<>(TaskSchedual::compare);
        //新晋入度为0的队列
        Queue<GraphNode> queueNext = new LinkedList<>();
        //找出入度为0的节点入队列
        for (Map.Entry<String, Integer> inCount : inCountMap.entrySet()) {
            if (inCount.getValue() == 0) {
                queueCur.add(graph.get(inCount.getKey()));
            }
        }
        //遍历入度为0的节点
        while (!queueCur.isEmpty()) {
            GraphNode node = queueCur.poll();
            result.add(node);
            //当前节点标记为已遍历
            visitMap.put(node.getId(), true);
            for (GraphNode neighbor : node.getNeighbors()) {
                //邻接点的入度减1
                int inCount = inCountMap.get(neighbor.getId()) - 1;
                inCountMap.put(neighbor.getId(), inCount);
                //入度为0的邻接点加入下个队列
                if (inCount == 0) {
                    queueNext.add(neighbor);
                }
            }
            if (queueCur.isEmpty()) {
                //当前队列为空，将下个队列元素批量加入
                queueCur.addAll(queueNext);
                //清空
                queueNext.clear();
            }
        }
        //检查是否还有未遍历的节点
        for (String key : graph.keySet()) {
            //未遍历的节点表示成环，无法排序
            if (visitMap.get(key) == null) {
                throw new Exception("有环无法排序");
            }
        }

        return result;
    }


    /**
     * 输入合法性校验
     *
     * @param input
     * @throws Exception
     */
    private void validate(String input) throws Exception {
        String regex = "T\\d+(->T\\d+)?(,T\\d+(->T\\d+)?)*";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(input);
        if (!matcher.matches()) {
            throw new Exception("输入有误");
        }
    }

    /**
     * 根据输入字符串，转化为有向图
     *
     * @param input
     * @return
     */
    private HashMap<String, GraphNode> mapToGraph(String input) {
        HashMap<String, GraphNode> nodeMap = new HashMap<>();
        //逗号分割的邻接关系
        String[] nodeStrs = input.split(",");
        for (String nodeStr : nodeStrs) {
            //解析子串 T1->T2 或者 T1
            String[] nodes = nodeStr.split("->");
            GraphNode node0 = nodeMap.get(nodes[0]);
            if (null == node0) {
                node0 = new GraphNode(nodes[0]);
                nodeMap.put(nodes[0], node0);
            }
            //T1->T2,依赖关系解析
            if (nodes.length == 2) {
                GraphNode node1 = nodeMap.get(nodes[1]);
                if (null == node1) {
                    node1 = new GraphNode(nodes[1]);
                    nodeMap.put(nodes[1], node1);
                }
                //这里的关系容易弄反，被依赖的节点为起点，有向图的方向与输入的箭头相反
                node1.getNeighbors().add(node0);
            }
        }

        return nodeMap;
    }

    /**
     * 节点输出顺序比较函数
     *
     * @param a
     * @param b
     * @return
     */
    private static int compare(GraphNode a, GraphNode b) {
        int i1 = Integer.parseInt(a.getId().substring(1));
        int i2 = Integer.parseInt(b.getId().substring(1));
        return Integer.compare(i1, i2);
    }
}
