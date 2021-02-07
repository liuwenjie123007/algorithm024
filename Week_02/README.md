# 作业1：HashMap总结
# 从put方法看起
```java
public V put(K key, V value) {
    return putVal(hash(key), key, value, false, true);
}
```
可以看到先对Key求了hash，详细看一下hash算法
```java
static final int hash(Object key) {
    int h;
    return (key == null) ? 
            0 : 
            (h = key.hashCode()) ^ (h >>> 16);
    }
```
hash的求解方法为，在key本身的hashCode的基础上与它的右移16位进行异或操作。
为什么要这么做？注释解释道，为了快速求hash，这是一种妥协平衡的快速hash法。
但是哈希的均匀度看上去没什么保证。

而在进入实际的put方法putVal，好像也印证了这个观点。
```java
final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
    Node<K,V>[] tab; Node<K,V> p; int n, i;
    if ((tab = table) == null || (n = tab.length) == 0)
        n = (tab = resize()).length;
    if ((p = tab[i = (n - 1) & hash]) == null)
        tab[i] = newNode(hash, key, value, null);
    else {
        Node<K,V> e; K k;
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
            e = p;
        else if (p instanceof TreeNode)
            e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
        else {
            for (int binCount = 0; ; ++binCount) {
                if ((e = p.next) == null) {
                    p.next = newNode(hash, key, value, null);
                    if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                        treeifyBin(tab, hash);
                    break;
                }
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    break;
                p = e;
            }
        }
        if (e != null) { // existing mapping for key
            V oldValue = e.value;
            if (!onlyIfAbsent || oldValue == null)
                e.value = value;
            afterNodeAccess(e);
            return oldValue;
        }
    }
    ++modCount;
    if (++size > threshold)
        resize();
    afterNodeInsertion(evict);
    return null;
}
```
进入方法中用了一个平时不太常用的赋值方法，即在if语句中同时完成赋值与比较。因此要根据短路情况，小心判断赋值。

上来首先判断了table数组是否为空，来初始化，table数组。现阶段先不关注初始化。
但根据下面逻辑可以知道
1. table为所谓的哈希桶，是存放各个算法算出来的下标所对应的链表的头节点或树的根节点。
2. n为哈希桶的长度。

下面就是关键了。这里会判断tab[]中根据索引[i = (n - 1) & hash]来查看，是否存在元素，若不存在则创建一个新节点，放在索引位置。

因此hash算法是分两步的
1. 调用插入元素的hashCode方法，并右移16位进行异或操作，这个hash会保留在节点信息中。
2. 与(n - 1) 进行与操作保证在数组下标范围内。

不知道这种算法，在数学上能否保证均匀度，但我在看来好像是为了保证快速hash，这里是有碰运气的意思的。

```java
Node<K,V> newNode(int hash, K key, V value, Node<K,V> next) {
    return new Node<>(hash, key, value, next);
}
```

创建的新节点就比较好理解了。是一个HashMap内部类，实现了Map.Entry<K,V>接口。

```java
static class Node<K,V> implements Map.Entry<K,V> {
    final int hash;
    final K key;
    V value;
    Node<K,V> next;

    Node(int hash, K key, V value, Node<K,V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public final K getKey()        { return key; }
    public final V getValue()      { return value; }
    public final String toString() { return key + "=" + value; }

    public final int hashCode() {
        return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

    public final V setValue(V newValue) {
        V oldValue = value;
        value = newValue;
        return oldValue;
    }

    public final boolean equals(Object o) {
        if (o == this)
            return true;
        if (o instanceof Map.Entry) {
            Map.Entry<?,?> e = (Map.Entry<?,?>)o;
            if (Objects.equals(key, e.getKey()) &&
                Objects.equals(value, e.getValue()))
                return true;
        }
        return false;
    }
}
```
可以看到，是一个链表节点结构，因此哈希桶中，默认存放着的是链表。

再回到putVal方法，若节点已经存在的话就比较有意思了。
要分三种情况处理：
1. 首先判断头节点的hash与key与传入节点和hash与Key进行比较，若二者相等，则用新value覆盖老的value.
2. 若hash与key两者有一个不相等，则先判断，头节点是否为树的根节点。若是树的根节点，则在树中添加插入节点。因此，这个方法中，一定存在将链表转化为树的逻辑。到目前还没有出现。
3. 若节点不为树的根节点，即为链表的头节点，则从头节点开始遍历链表。并且初始化一个计数器binCount。
   1. 若链表中存在，hash与key相同的节点，则与头节点相同，覆盖value。
   2. 若不存在，则创建新元素后，添加到链表尾部。但根据binCount即链表长度，再次进行判断。
      1. 若链表长度大于等于TREEIFY_THRESHOLD - 1，即大于等于7进行treeifyBin操作。

```java
final void treeifyBin(Node<K,V>[] tab, int hash) {
    int n, index; Node<K,V> e;
    if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)
        resize();
    else if ((e = tab[index = (n - 1) & hash]) != null) {
        TreeNode<K,V> hd = null, tl = null;
        do {
            TreeNode<K,V> p = replacementTreeNode(e, null);
            if (tl == null)
                hd = p;
            else {
                p.prev = tl;
                tl.next = p;
            }
            tl = p;
        } while ((e = e.next) != null);
        if ((tab[index] = hd) != null)
            hd.treeify(tab);
    }
}
```

这个方法内部就更有意思了。
首先会判断hash桶长度。
   1. 小于MIN_TREEIFY_CAPACITY即64时，会调整哈希桶的大小，并保持链表结构。这里分析先不深入内部，但可以预见到，应该又是一种权衡的操作，根据注释可以看到TreeNode的内存占用是链表节点的两倍左右，因此可以通过调整表大小来，调整哈希碰撞概率时，这么做的成本会更低。但为什么是64这个神奇的数字呢？
   2. 当哈希桶长度，大于或等于64时进行，向树的转换操作。转换操作也是分两步的。
      1. 首先将需要转换的链表节点通过replacementTreeNode方法转换为TreeNode节点，再把TreeNode节点组织为一个双向链表。
         1. TreeNode节点为实现了LinkedHashMap.Entry<K,V>接口的HashMap内部类。
      2. 其次通过根节点的treeify方法来，将双向链表转换为树。

```java
final void treeify(Node<K,V>[] tab) {
    TreeNode<K,V> root = null;
    for (TreeNode<K,V> x = this, next; x != null; x = next) {
        next = (TreeNode<K,V>)x.next;
        x.left = x.right = null;
        if (root == null) {
            x.parent = null;
            x.red = false;
            root = x;
        }
        else {
            K k = x.key;
            int h = x.hash;
            Class<?> kc = null;
            for (TreeNode<K,V> p = root;;) {
                int dir, ph;
                K pk = p.key;
                if ((ph = p.hash) > h)
                    dir = -1;
                else if (ph < h)
                    dir = 1;
                else if ((kc == null &&
                            (kc = comparableClassFor(k)) == null) ||
                            (dir = compareComparables(kc, k, pk)) == 0)
                    dir = tieBreakOrder(k, pk);

                TreeNode<K,V> xp = p;
                if ((p = (dir <= 0) ? p.left : p.right) == null) {
                    x.parent = xp;
                    if (dir <= 0)
                        xp.left = x;
                    else
                        xp.right = x;
                    root = balanceInsertion(root, x);
                    break;
                }
            }
        }
    }
    moveRootToFront(tab, root);
}
```
嘛，黑魔法T_T, 转换的是一个所谓的红黑树，不过目前还没有知识储备。学完再回过来搞吧。。。

总的下来可以看到链表向红黑树的转换是单条链的转换的，并不是HashMap内部全部转换。

还有一个遗留问题：TREEIFY_THRESHOLD = 8，即为什么链表长度大于8时才会尝试转换为红黑树。
```java
/*
* Because TreeNodes are about twice the size of regular nodes, we
* use them only when bins contain enough nodes to warrant use
* (see TREEIFY_THRESHOLD). And when they become too small (due to
* removal or resizing) they are converted back to plain bins.  In
* usages with well-distributed user hashCodes, tree bins are
* rarely used.  Ideally, under random hashCodes, the frequency of
* nodes in bins follows a Poisson distribution
* (http://en.wikipedia.org/wiki/Poisson_distribution) with a
* parameter of about 0.5 on average for the default resizing
* threshold of 0.75, although with a large variance because of
* resizing granularity. Ignoring variance, the expected
* occurrences of list size k are (exp(-0.5) * pow(0.5, k) /
* factorial(k)). The first values are:
*
* 0:    0.60653066
* 1:    0.30326533
* 2:    0.07581633
* 3:    0.01263606
* 4:    0.00157952
* 5:    0.00015795
* 6:    0.00001316
* 7:    0.00000094
* 8:    0.00000006
*/
```

class的开头写着这么一段注释，当hashCode离散的非常随机时，他认为击中哈希桶的元素会按照泊松分布分配。因此链表长度等于8的概率只有一亿分之6。而转换为树节点会显著增加内存占用，因此不失为一个很好的考量。但是hash算法在我看来也过于黑魔法。。。

总结一下put方法。总体来说是经过精心设计的。
1. 默认会按照哈希桶数组 + 链表的形式存储元素。 因此插入操作时间复杂度介于O(1)~O(n)。
2. 当哈希碰撞严重时，内部会单独将碰撞严重的链表转化为红黑树，用空间换取时间。最坏时间复杂度从O(n)降为O(logN)。

# get方法
```java
public V get(Object key) {
    Node<K,V> e;
    return (e = getNode(hash(key), key)) == null ? null : e.value;
}
```
调用hash方法，创给getNode方法，进行实际的get操作。
```java
final Node<K,V> getNode(int hash, Object key) {
    Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
    if ((tab = table) != null && (n = tab.length) > 0 &&
        (first = tab[(n - 1) & hash]) != null) {
        if (first.hash == hash && // always check first node
            ((k = first.key) == key || (key != null && key.equals(k))))
            return first;
        if ((e = first.next) != null) {
            if (first instanceof TreeNode)
                return ((TreeNode<K,V>)first).getTreeNode(hash, key);
            do {
                if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
            } while ((e = e.next) != null);
        }
    }
    return null;
}
```
相比于put方法内部逻辑就简单很多了。

首先判断，[(n - 1) & hash]是否存在头节点，若没有返回null。
若存在头节点
1.  先看头节点key与hash是否与传入key的相同，若相同，则发回头节点。
2.  不相同的情况下，又要判断是链表还是红黑树。
    1.  链表的情况就是，单次扫描链表查看是否存在元素。若存在则返回。
    2.  红黑树的情况调用getTreeNode方法，这里应该就是红黑树的遍历问题了，留给以后解决。。。
3.  上述情况都没有找到节点，则返回null。

总结下来get方法比较简单，就是hash后再遍历。
1. 遍历链表的情况，时间复杂度O(1) ~ O(n)
2. 遍历红黑树的情况，时间复杂度O(1) ~ O(LogN)

# remove方法
```java
public V remove(Object key) {
    Node<K,V> e;
    return (e = removeNode(hash(key), key, null, false, true)) == null ?
        null : e.value;
}
```
remove方法据先计算hash，实际通过removeNode完成删除操作。

removeNode方法可以指定value也要相同的情况下才删除，不过这里讨论只根据key删除的情况。

```java
final Node<K,V> removeNode(int hash, Object key, Object value,
                            boolean matchValue, boolean movable) {
    Node<K,V>[] tab; Node<K,V> p; int n, index;
    if ((tab = table) != null && (n = tab.length) > 0 &&
        (p = tab[index = (n - 1) & hash]) != null) {
        Node<K,V> node = null, e; K k; V v;
        if (p.hash == hash &&
            ((k = p.key) == key || (key != null && key.equals(k))))
            node = p;
        else if ((e = p.next) != null) {
            if (p instanceof TreeNode)
                node = ((TreeNode<K,V>)p).getTreeNode(hash, key);
            else {
                do {
                    if (e.hash == hash &&
                        ((k = e.key) == key ||
                            (key != null && key.equals(k)))) {
                        node = e;
                        break;
                    }
                    p = e;
                } while ((e = e.next) != null);
            }
        }
        if (node != null && (!matchValue || (v = node.value) == value ||
                                (value != null && value.equals(v)))) {
            if (node instanceof TreeNode)
                ((TreeNode<K,V>)node).removeTreeNode(this, tab, movable);
            else if (node == p)
                tab[index] = node.next;
            else
                p.next = node.next;
            ++modCount;
            --size;
            afterNodeRemoval(node);
            return node;
        }
    }
    return null;
}
```
前半部分其实是与get方法一样的，相当于调用get方法拿到了，要删除的节点。
下半部分三种情况。
1. 若为红黑树节点，则调用节点的removeTreeNode方法，删除节点。
2. 若为链表节点，切为头节点，则将哈希桶指向一下个节点。
3. 若不是头节点，则从链表中删除节点。

最后令map的size - 1;

```java
final void removeTreeNode(HashMap<K,V> map, Node<K,V>[] tab,
                            boolean movable) {
    int n;
    if (tab == null || (n = tab.length) == 0)
        return;
    int index = (n - 1) & hash;
    TreeNode<K,V> first = (TreeNode<K,V>)tab[index], root = first, rl;
    TreeNode<K,V> succ = (TreeNode<K,V>)next, pred = prev;
    if (pred == null)
        tab[index] = first = succ;
    else
        pred.next = succ;
    if (succ != null)
        succ.prev = pred;
    if (first == null)
        return;
    if (root.parent != null)
        root = root.root();
    if (root == null
        || (movable
            && (root.right == null
                || (rl = root.left) == null
                || rl.left == null))) {
        tab[index] = first.untreeify(map);  // too small
        return;
    }
    TreeNode<K,V> p = this, pl = left, pr = right, replacement;
    if (pl != null && pr != null) {
        TreeNode<K,V> s = pr, sl;
        while ((sl = s.left) != null) // find successor
            s = sl;
        boolean c = s.red; s.red = p.red; p.red = c; // swap colors
        TreeNode<K,V> sr = s.right;
        TreeNode<K,V> pp = p.parent;
        if (s == pr) { // p was s's direct parent
            p.parent = s;
            s.right = p;
        }
        else {
            TreeNode<K,V> sp = s.parent;
            if ((p.parent = sp) != null) {
                if (s == sp.left)
                    sp.left = p;
                else
                    sp.right = p;
            }
            if ((s.right = pr) != null)
                pr.parent = s;
        }
        p.left = null;
        if ((p.right = sr) != null)
            sr.parent = p;
        if ((s.left = pl) != null)
            pl.parent = s;
        if ((s.parent = pp) == null)
            root = s;
        else if (p == pp.left)
            pp.left = s;
        else
            pp.right = s;
        if (sr != null)
            replacement = sr;
        else
            replacement = p;
    }
    else if (pl != null)
        replacement = pl;
    else if (pr != null)
        replacement = pr;
    else
        replacement = p;
    if (replacement != p) {
        TreeNode<K,V> pp = replacement.parent = p.parent;
        if (pp == null)
            root = replacement;
        else if (p == pp.left)
            pp.left = replacement;
        else
            pp.right = replacement;
        p.left = p.right = p.parent = null;
    }

    TreeNode<K,V> r = p.red ? root : balanceDeletion(root, replacement);

    if (replacement == p) {  // detach
        TreeNode<K,V> pp = p.parent;
        p.parent = null;
        if (pp != null) {
            if (p == pp.left)
                pp.left = null;
            else if (p == pp.right)
                pp.right = null;
        }
    }
    if (movable)
        moveRootToFront(tab, r);
}
```

removeTreeNode方法内部也是异常的复杂，等学完红黑树再说。。。

因此总结remove方法，时间复杂度与get方法类似。但根据节点是否为红黑树稍有不同。
1. 链表的情况，时间复杂度O(1) ~ O(n)
2. 红黑树的情况，就算是头节点，因为总是要重排红黑树，时间复杂度O(LogN)

*由于时间关系HashMap就总结到这。其他方法以后再说。。。