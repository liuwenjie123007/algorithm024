package Week_02;

import java.util.*;

/**
 * <h1>94. 二叉树的中序遍历</h1>
 * <a>https://leetcode-cn.com/problems/binary-tree-inorder-traversal/</a>
 * <h3>解法1：递归</h3>
 * <p>时间复杂度O(n), 空间复杂度O(n)</p>
 * <h3>解法2：手动维护栈迭代</h3>
 * <p>时间复杂度O(n), 空间复杂都O(n)</p>
 * @author WENJIE
 */
public class BinaryTreeInorderTraversal {
    // 解法1
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        inorder(ret, root);
        return ret;
    }

    private void inorder(List<Integer> ret, TreeNode root) {
        if (root == null) return;
        inorder(ret, root.left);
        ret.add(root.val);
        inorder(ret, root.right);
    }

    // 解法2
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            ret.add(root.val);
            root = root.right;
        }
        return ret;
    }
}
