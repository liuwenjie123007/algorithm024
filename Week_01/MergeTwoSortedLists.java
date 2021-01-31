package Week_01;

/**
 * <h1>21. 合并两个有序链表</h1>
 * <a>https://leetcode-cn.com/problems/merge-two-sorted-lists/</a>
 * <h3>方法：顺序拼接</h3>
 * <p>时间复杂度O(n),空间复杂度O(1)</p>
 * @author WENJIE
 */
public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                head.next = l2;
                break;
            }
            if (l2 == null) {
                head.next = l1;
                break;
            }
            if (l1.val > l2.val) {
                head.next = l2;
                l2 = l2.next;

            } else {
                head.next = l1;
                l1 = l1.next;
            }
            head = head.next;
            head.next = null;
        }
        return dummy.next;
    }


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
