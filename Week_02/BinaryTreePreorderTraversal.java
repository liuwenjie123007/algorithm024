package Week_02;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * <h1>144. 二叉树的前序遍历</h1>
 * <a>https://leetcode-cn.com/problems/binary-tree-preorder-traversal/</a>
 * <h3>解法1：递归</h3>
 * <p>时间复杂度O(n), 空间复杂度O(n)</p>
 * <h3>解法2：手动维护栈迭代</h3>
 * <p>时间复杂度O(n), 空间复杂都O(n)</p>
 * @author WENJIE
 */
public class BinaryTreePreorderTraversal {
    // 解法1
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        preorder(ret, root);
        return ret;
    }

    private void preorder(List<Integer> ret, TreeNode root) {
        if (root == null) return;
        ret.add(root.val);
        preorder(ret, root.left);
        preorder(ret, root.right);
    }

    // 解法2
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            if (root != null) {
                ret.add(root.val);
                stack.push(root.right);
                stack.push(root.left);
            }
        }
        return ret;
    }
}
