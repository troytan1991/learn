package com.troytan.learn.algo.tree;

import com.troytan.learn.support.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *二叉树的四种遍历方式
 *
 *@Author troytan
 *@Date 2/3/2020
 */
public class TreeTraversal {

    /**
     * 先序遍历：父节点->左节点->右节点的顺序
     *
     * @param root
     * @param result
     */
    public void preOrder(TreeNode root, List<TreeNode> result) {
        if (root == null) {
            return;
        }
        result.add(root);
        preOrder(root.left, result);
        preOrder(root.right, result);
    }

    /**
     * 中序遍历：左节点->父节点->右节点
     *
     * @param root
     * @param result
     */
    public void midOrder(TreeNode root, List<TreeNode> result) {
        if (root == null) {
            return;
        }
        midOrder(root.left, result);
        result.add(root);
        midOrder(root.right, result);
    }

    /**
     * 后序遍历：左节点->右节点->父节点
     *
     * @param root
     * @param result
     */
    public void lastOrder(TreeNode root, List<TreeNode> result) {
        if (root == null) {
            return;
        }
        lastOrder(root.left, result);
        lastOrder(root.right, result);
        result.add(root);
    }

    /**
     * 层序遍历：按照树的深度，每层遍历
     *
     * @param root
     * @param result
     */
    public void levelOrder(TreeNode root, List<TreeNode> result) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            result.add(node);
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
    }
}
