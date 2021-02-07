package Week_02;

import java.util.*;

/**
 * <h1>429. N 叉树的层序遍历</h1>
 * <a>https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/</a>
 * <h3>解法1：递归</h3>
 * <p>时间复杂度O(n), 空间复杂度O(n)</p>
 * @author WENJIE
 */
public class NAryTreeLevelOrderTraversal {
    //解法1
    public List<List<Integer>> levelOrder1(Node root) {
        List<List<Integer>> ret = new ArrayList<>();
        levelOrder(ret, root, 0);
        return ret;
    }

    private void levelOrder(List<List<Integer>>  ret, Node root, int level) {
        if (root == null) return;
        if (level == ret.size()) {
            ret.add(level, new ArrayList<>());
        }
        ret.get(level).add(root.val);
        root.children.forEach(node -> levelOrder(ret, node, level + 1));
    }
}
