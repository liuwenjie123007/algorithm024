package Week_01;

import java.util.LinkedList;

/**
 * <h1>641. 设计循环双端队列</h1>
 * <a>https://leetcode-cn.com/problems/design-circular-deque/</a>
 * <h3>解法：实现双向链表，提供双端队列API</h3>
 * <p>出于方便使用LinkedList实现</p>
 * @author WENJIE
 */
public class MyCircularDeque {
    private int size;
    private LinkedList<Integer> linkedList;
    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        this.size = k;
        this.linkedList = new LinkedList<>();
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (size <= linkedList.size()) {
            return false;
        }
        this.linkedList.addFirst(value);
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (size <= linkedList.size()) {
            return false;
        }
        this.linkedList.addLast(value);
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if(linkedList.size() == 0) return false;
        this.linkedList.removeFirst();
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if(linkedList.size() == 0) return false;
        this.linkedList.removeLast();
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        return linkedList.isEmpty() ? -1 : this.linkedList.getFirst();
    }

    /** Get the last item from the deque. */
    public int getRear() {
        return linkedList.isEmpty() ? -1 : this.linkedList.getLast();
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return linkedList.size() == size;
    }
}
