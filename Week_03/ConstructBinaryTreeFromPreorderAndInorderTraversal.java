package Week_03;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>105. 从前序与中序遍历序列构造二叉树</h1>
 * <a>https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/</a>
 * <h3>解法：递归, 分治</h3>
 * <p>前序遍历:[根节点, 左子树, 右子树]</p>
 * <p>中序遍历:[左子树, 根节点, 右子树]</p>
 * <p>时间复杂度O(n), 空间复杂度O(n)</p>
 *
 * @author WENJIE
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildDynamic(0, preorder.length, 0, inorder.length, map, preorder, inorder);
    }

    private TreeNode buildDynamic(int preleft, int preLength, int inLeft, int inLength, Map<Integer, Integer> map, int[] preorder, int[] inorder) {
        if (preLength == 0) return null;
        TreeNode root = new TreeNode(preorder[preleft]);
        Integer inRootIndex = map.get(root.val);
        int lefTreeLength = inRootIndex - inLeft;
        int rightTreeLength = inLength - lefTreeLength - 1;
        root.left = buildDynamic(preleft + 1, lefTreeLength, inRootIndex - lefTreeLength, lefTreeLength, map, preorder, inorder);
        root.right = buildDynamic(preleft + lefTreeLength + 1, rightTreeLength, inRootIndex + 1, rightTreeLength, map, preorder, inorder);
        return root;
    }
}
