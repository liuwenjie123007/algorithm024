package Week_02;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <h1>589. N叉树的前序遍历</h1>
 * <a>https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/</a>
 * <h3>解法1：递归</h3>
 * <p>时间复杂度O(n), 空间复杂度O(n)</p>
 * <h3>解法2：手动维护栈迭代</h3>
 * <p>时间复杂度O(n), 空间复杂都O(n)</p>
 * @author WENJIE
 */
public class NAryTreePreOrder {
    //解法1
    public List<Integer> preorder1(Node root) {
        List<Integer> ret = new ArrayList<>();
        preOrder(ret, root);
        return ret;
    }

    private void preOrder(List<Integer> ret, Node root) {
        if (root == null) return;
        ret.add(root.val);
        root.children.forEach(node -> preOrder(ret, node));
    }

    //解法2
    public List<Integer> preorder2(Node root) {
        List<Integer> ret = new ArrayList<>();
        Deque<Node> stack = new LinkedList<>();
        if (root == null)  return ret;
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            ret.add(node.val);
            Collections.reverse(node.children);
            node.children.forEach(stack::push);
        }
        return ret;
    }
}
