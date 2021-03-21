学习笔记

# 总结双向 BFS 代码模版

首先回顾一下单向BFS模板

```java
public class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  
  TreeNode(int x) {
    val = x;
  }
}

public List<List<Integer>> levelOrder(TreeNode root) {
  List<List<Integer>> allResults = new ArrayList<>();
  if (root == null) {
    return allResults;
  }
  Queue<TreeNode> nodes = new LinkedList();
  nodes.add(root);
  while(!nodes.isEmpty()) {
    int size = nodes.size();
    List<Integer> results = new ArrayList<>();
    for (int i = 0; i < size; i++) {
      TreeNode node = nodes.poll();
      results.add(node.val);
      if (node.left != null) {
        nodes.add(node.left);
      }
      if (node.right != null) {
        nodes.add(node.right);
      }
    }
    allResults.add(results);
  }
  return allResults;
}
```



这是一个基于二叉树的层序遍历BFS遍历的模板。

显然，双向BFS是需要知道头尾节点的，只给出了根节点的这个模板之中并不能扩展出一个双向BFS模板。



目前看来，双向BFS适宜解决：最短路径，最短步数等问题。

因此，下面展开一种基于计算最短步数的模板；

```java
Set<Node> startQueue = new HashSet<>();
Set<Node> endQueue = new HashSet<>();
Set<Node> memo = new HashSet<>();

// 双向dfs
public int doubleBfs(Node start, Node end) {
  startQueue.add(start);
  endQueue.add(end);
  int step = 0;
  while (!startQueue.isEmpty() && !endQueue.isEmpty()) {
    step++;
    // 每次展开节点数少的一端
    if (startQueue.size() > endQueue.size()) {
      Set<Node> temp = endQueue;
      endQueue = startQueue;
      startQueue = temp;
    }

    Set<Node> nextQueue = new HashSet<>();

    for (Node node : startQueue) {
      // 根据条件展开到下一步
      for (condition) {
        Node nextNode = getNextNode(node);
        if (endQueue.contains(next))
          return step;
        if (!memo.contains(next)) {
          memo.add(nextNode);
          nextQueue.add(nextNode);
        }
      }
    }
    startQueue = nextQueue;
  }
  return -1;
}
```

