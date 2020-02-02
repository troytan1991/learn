package com.troytan.learn.support;

import lombok.Data;

import java.util.List;
/**
 *图的邻接表节点，用于不带权的图表示
 *
 *@Author troytan
 *@Date 2/3/2020
 */
@Data
public class GraphNode {

    private String id;
    private List<GraphNode> neighbors;

    public GraphNode(String id) {
        this.id = id;
    }
}
