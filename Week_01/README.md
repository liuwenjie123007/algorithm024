# 作业：分析 Queue 和 Priority Queue 的源码
## 首先考察Queue接口设计
1. 支持泛型
2. 单向队列设计
3. 两组API
   1. 第一组: 操作失败返回Exception
      1. add(e)
      2. remove()
      3. element()
   2. 第二组：操作失败返回特殊值
      1. offer(e) --- false
      2. poll() --- null
      3. peek() --- null

## 考察Priority Queue 实现
1. 继承 --- AbstractQueue
   1. AbstractQueue实现 --- Queue
### 从插入方法入手
```
public boolean offer(E e) {
    if (e == null)
        throw new NullPointerException();
    modCount++;
    int i = size;
    if (i >= queue.length)
        grow(i + 1);
    size = i + 1;
    if (i == 0)
        queue[0] = e;
    else
        siftUp(i, e);
    return true;
 }
```
Priority Queue的插入操作是O(logn)，在这段代码中除了特殊值，与扩容操作，可以看出是在siftUp(i,e)中实现的具体操作。
```
private void siftUp(int k, E x) {
    if (comparator != null)
        siftUpUsingComparator(k, x);
    else
        siftUpComparable(k, x);
}
```
在创建Priority Queue实例是可以自定义内部排序器Comparetor,为了方便分析，只考察默认排序器，siftUpComparable(k, x);
```
private void siftUpComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>) x;
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            if (key.compareTo((E) e) >= 0)
                break;
            queue[k] = e;
            k = parent;
        }
        queue[k] = key;
}
```
参数k : 插入前队列中元素个数

key: 为插入元素默认实现的Comparable接口

这里的关键就是parent为何要如此计算，

并且将插入元素与查找到的元素进行比较，如果查找到的元素比插入元素小，移动查找到的元素到k位置。

遗憾的是以现在的知识储备对于这个东西只能认为是黑魔法。
看一下，别人的解释

```
最小堆是一个完全二叉树，所谓的完全二叉树是一种没有空节点的二叉树。

最小堆的完全二叉树有一个特性是根节点必定是最小节点，子女节点一定大于其父节点。还有一个特性是叶子节点数量=全部非叶子节点数量+1

在 PriorityQueue队列中，基于数组保存了完全二叉树。所以在已知任意一个节点在数组中的位置，就可以通过一个公式推算出其左子树和右子树的下标。已知节点下标是i，那么他的左子树是2*i+1，右子树是2*i+2。

siftUp 方法，为什么叫 up 呢，因为插入的位置是数组的最后，也就是二叉树的最后一个节点，所以要向上调整，这里就涉及堆排序的调整。
comparator 为空时，用 compareTo 比较，直到 parent 比插入的元素大，否则交换，就是这么简单。
```

可以知道可以看到，通过这种奇怪的方法可以生成一个最小堆，

每一次插入要进行堆排序而这个的时间复杂度就是O(lonN)

### 查看peek方法
```
public E peek() {
    return (size == 0) ? null : (E) queue[0];
}
```
可以看到，优先队列中最大元素，总是放在数组第一个，因此可以实现O(1)的查看操作

### 查看出队操作
```
public E poll() {
    if (size == 0)
        return null;
    int s = --size;
    modCount++;
    E result = (E) queue[0];
    E x = (E) queue[s];
    queue[s] = null;
    if (s != 0)
        siftDown(0, x);
    return result;
}
```
可以看到从对手直接拿元素，并且进行了siftDown.
因此此操作时间复杂度由siftDown决定。

```
private void siftDownComparable(int k, E x) {
    Comparable<? super E> key = (Comparable<? super E>)x;
    int half = size >>> 1;        // loop while a non-leaf
    while (k < half) {
        int child = (k << 1) + 1; // assume left child is least
        Object c = queue[child];
        int right = child + 1;
        if (right < size &&
            ((Comparable<? super E>) c).compareTo((E) queue[right]) > 0)
            c = queue[child = right];
        if (key.compareTo((E) c) <= 0)
            break;
        queue[k] = c;
        k = child;
    }
    queue[k] = key;
}
```
看上去进行了类似二分查找的再排序，因此出队操作为O(logN).

*此文应在学习完堆排序后再次完善