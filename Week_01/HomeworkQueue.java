package Week_01;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * <h1>用 add first 或 add last 这套新的 API 改写 Deque 的代码</h1>
 * @since 0.1
 * @author WENJIE
 */
public class HomeworkQueue {
    public static void main(String[] args) {
        Deque<String> deque = new ArrayDeque<>();
        deque.addLast("a");
        deque.addLast("b");
        deque.addLast("c");
        System.out.println(deque);
        String str = deque.removeLast();
        System.out.println(str);
        System.out.println(deque);
        while (deque.size() > 0) {
            System.out.println(deque.removeLast());
        }
        System.out.println(deque);
    }
}
