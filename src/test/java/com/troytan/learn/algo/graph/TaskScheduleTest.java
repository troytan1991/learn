package com.troytan.learn.algo.graph;

import com.troytan.learn.algo.graph.TaskSchedual;
import com.troytan.learn.support.GraphNode;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.stream.Collectors;

public class TaskScheduleTest {

    private TaskSchedual taskSchedual = new TaskSchedual();
    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void test01() throws Exception {
        String input = "T1->T2,T2->T3,T2->T4,T3->T4,T4->T5,T6";
        Assert.assertEquals(graphNodesToString(taskSchedual.sortTask(input)), "T5,T6,T4,T3,T2,T1");
    }

    @Test
    public void test02() throws Exception {
        String input = "T1->T2,T2->T3,T3->T1";
        expected.expectMessage("有环无法排序");
        taskSchedual.sortTask(input);
    }

    @Test
    public void test03() throws Exception {
        String input = "K1,K4,T3";
        expected.expectMessage("输入有误");
        taskSchedual.sortTask(input);
    }

    private String graphNodesToString(List<GraphNode> nodes) {
        return nodes.stream().map(node -> node.getId()).collect(Collectors.joining(","));
    }


}
