# 学习笔记

## 字典树 Trie

### 本节内容

- 字典树的数据结构
- 字典树的核心思想
- 字典树的基本性质



### 基本结构

字典树，即Tire树，又称单词查找树或键树，是一种树形结构。

典型的应用是用于统计和排序大量的字符串（但不仅限于字符串），所以经常被搜索引擎系统用于文本词频统计。



它的优点是：最大限度地减少无谓的字符串比较，查询效率比哈希表高。



### 基本性质

- 节点本身不存完整单词；
- 从根节点到某一节点，路径上经过的字符连接起来，为该节点对应的字符串；
- 每个节点的所有子节点路径代表的字符都不相同。





### 核心思想

Trie树的核心思想是空间换时间。

利用字符串的公共前缀来降低查询时间的开销以达到提高效率的目的。



### 代码模板

```java
class Trie {
  private boolean isEnd;
  private Trie[] next;

  public Trie() {
    isEnd = false;
    next = new Trie[26];
  }

  public void insert(String word) {
    if (word == null || word.length() == 0) 
      return;
    Trie curr = this;
    for (int i = 0; i < word.length(); i++) {
      int n = word.charAt(i) - 'a';
      if (curr.next[n] == null)
        curr.next[n] = new Trie();
      curr = curr.next[n];
    }
    curr.isEnd = true;
  }

  public boolean search(String word) {
    Trie node = searchPrefix(word);
    return node != null && node.isEnd;
  }

  private Trie searchPrefix(String word) {
    Trie node = this;
    for (int i = 0; i < word.length(); i++) {
      node = node.next[word.charAt(i) - 'a'];
      if (node == null)
        return null;
    }
    return node;
  }

  public boolean startsWith(String prefix) {
    return searchPrefix(prefix) != null;
  }
}
```



## 并查集 Disjoint Set

### 适用场景

- 组团、配对问题
- Group or not ?



### 基本操作

- makeSet(s): 
  - 建立一个新的并查集，其中包含s个单元素集合。
- unionSet(x, y): 
  - 把元素x和元素y所在的集合合并， 要求x和y所在的集合不相交，如果相交则不合并。
- find(x):
  - 找到元素x所在的集合的代表，该操作也可以用于判断两个元素是否位于同一个集合，只要将它们各自的代表比较一下就可以了。



# 位运算

## 目录

- 位运算符
- 算数位移与逻辑位移
- 位运算的应用



## 为什么需要位运算

- 机器里的数字表示方式和存储格式就是二进制
- 时间至 <-> 二进制： 如何转换
  - 4(d): 0100
  - 8(d): 01000
  - 5(d): 0101
  - 6(d): 0110



## 位运算符

| 含义                         | 运算符 | 示例                 |
| ---------------------------- | ------ | -------------------- |
| 左移                         | <<     | 0011 => 0110         |
| 右移                         | >>     | 0110 => 0011         |
| 按位或                       | \|     | 0011 \| 1011 => 1011 |
| 按位与                       | &      | 0011 \| 1011 => 0011 |
| 按位取反                     | ~      | 0011 => 1100         |
| 按位异或（相同为零不同为一） | ^      | 0011 ^ 1011 => 1000  |

## XOR - 异或

- 异或： 相同为0， 不同为1. 也可用“不进位加法”来理解
- 异或操作的一些特点：
  - x ^ 0 = x
  - x ^ 1s = ~x // 注意 1s = ~0
  - x ^ (~x) = 1s
  - x ^ x = 0
  - c = a ^ b => a ^ c =b , b ^ c => a // 交换两个数
  - a ^ b ^ c = a ^ (b ^ c) = (a ^ b) ^ c   // associative



## 指定位置的位运算

- 将 x 最右边的n位清零
  - x & (~0 << n)
- 获取 x 的第n位值 (0或者1) : (x >> n) & 1
- 获取x的第n位的幂值: x & (1 << n)
- 仅将第n位置为1: x | (1 << n)
- 仅将第n位置为0: x & (~(1 << n))
- 将x最高位至第n位(含) 清零: x & ((1 << n) - 1)



## 实战位运算要点

- 判断奇偶
  - x % 2 == 1 ----> (x & 1) == 1
  - x % 2 == 0 ----> (x & 1) == 0
- x >> 1 --> x / 2
  - 即 x = x / 2 ---> x = x >> 1;
  - mid = (left + right) / 2; ---> mid = (left + right) >> 1;
- x = x & (x - 1)
  - 清零最低位的1
- x & -x => 得到最低位的 1
- x & ~x => 0


# 高级树、AVL树和红黑树

## 二叉搜索树如何平衡？

## AVL树

- 发明者G.M.Adelson-Velsky和Evgenii Landis
- Balance Factor (平衡因子)
  - 是它的左子树高度减去它的右子树的高度（有时相反）
  - balance factor = {-1, 0, 1}
- 通过旋转操作进行平衡（四种）



### 旋转做操

- 左旋
  - 右右子树
- 右旋
  - 左左子树
- 左右旋
  - 左右子树
- 右左旋
  - 右左子树

### AVL 总结

- 平衡二叉搜索树
- 每个节点存balance factor = {-1, 0 , 1}
- 四种旋转操作
- 不足： 节点需要存储额外信息，且调整次数频繁





## 红黑树

近似平衡二叉树

红黑树是一种近似平衡的二叉搜索树，它能够确保任何一个节点的左右子树的高度差小于两倍。具体来说，红黑树是满足如下条件的二叉搜索树：

- 每个节点要么是红色，要么是黑色
- 根节点是黑色
- 每个叶节点（NIL节点，空节点）是黑色的。
- 不能有相邻接的两个红色节点
- 从任一节点到其每个叶子节点的所有路径包含相同数目的黑色节点。



### 关键性质

从根到叶子的最长的可能路径不多于最短的可能路径的两倍长。



### 对比

- AVL树的话提供了更快的lookups
- 红黑树提供了更快的插入和删除的操作
- AVL树要更多的内存
- 读多写少AVL，插入和查询一半一半红黑树