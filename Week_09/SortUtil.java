package Week_09;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class SortUtil {

    /**
     * 冒泡排序
     * 时间复杂度: O(n^2)
     * 空间复杂度O(1)
     *
     * @param array
     * @param comparator
     * @param <T>
     */
    public static <T> void bubbleSort(T[] array, Comparator<T> comparator) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (comparator.compare(array[i], array[j]) < 0)
                    swap(array, j, j + 1);
            }
        }
    }

    /**
     * 交换数组中指定两个下标元素
     *
     * @param array
     * @param i
     * @param j
     * @param <T>
     */
    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * 简单选择排序
     * 时间复杂度: O(n^2)
     * 空间复杂度: O(1)
     *
     * @param array
     * @param comparator
     * @param <T>
     */
    public static <T> void selectionSort(T[] array, Comparator<T> comparator) {
        int n = array.length;
        int minIndex;
        for (int i = 0; i < n - 1; i++) {
            minIndex = i;
            for (int j = 0; j < n; j++) {
                if (comparator.compare(array[j], array[minIndex]) < 0)
                    minIndex = j;
            }
            swap(array, i, minIndex);
        }
    }

    /**
     * 简单插入排序
     * 时间复杂度: O(n^2)
     * 空间复杂度: O(1)
     *
     * @param array
     * @param comparator
     * @param <T>
     */
    public static <T> void insertSort(T[] array, Comparator<T> comparator) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            int preIndex = i - 1;
            T cur = array[i];
            while (preIndex >= 0 && comparator.compare(array[preIndex], cur) > 0) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = cur;
        }
    }

    /**
     * 希尔排序
     * 时间复杂度O(n^1.3)
     * 空间复杂度O(1)
     *
     * @param array
     * @param comparator
     * @param <T>
     */
    public static <T> void shellSort(T[] array, Comparator<T> comparator) {
        int n = array.length;
        for (int gap = (int) Math.floor(n >> 1); gap > 0; gap = (int) Math.floor(gap >> 1)) {
            for (int i = gap; i < n; i++) {
                int j = i;
                T cur = array[i];
                while (j - gap >= 0 && comparator.compare(cur, array[j - gap]) > 0) {
                    array[j] = array[j - gap];
                    j = j - gap;
                }
                array[j] = cur;
            }
        }
    }

    /**
     * 归并排序
     * 时间复杂度O(NLogN)
     * 空间复杂度O(N)
     *
     * @param array
     * @param comparator
     * @param <T>
     */
    public static <T> void mergeSort(T[] array, Comparator<T> comparator) {
        mergeSort(array, comparator, 0, array.length - 1);
    }

    private static <T> void mergeSort(T[] array, Comparator<T> comparator, int left, int right) {
        if (right <= left) return;
        int mid = (left + right) >> 1;
        mergeSort(array, comparator, left, mid);
        mergeSort(array, comparator, mid + 1, right);
        merge(array, comparator, left, mid, right);
    }

    private static <T> void merge(T[] array, Comparator<T> comparator, int left, int mid, int right) {
        Object[] temp = new Object[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right)
            temp[k++] = comparator.compare(array[i], array[j]) <= 0 ? array[i++] : array[j++];
        while (i <= mid) temp[k++] = array[i++];
        while (j <= right) temp[k++] = array[j++];
        if (temp.length >= 0) System.arraycopy(temp, 0, array, left, temp.length);
    }

    /**
     * 快速排序
     * 时间复杂度O(NLogN)
     * 空间复杂度O(NLogN)
     *
     * @param array
     * @param comparator
     * @param <T>
     */
    public static <T> void quickSort(T[] array, Comparator<T> comparator) {
        quickSort(array, comparator, 0, array.length - 1);
    }

    private static <T> void quickSort(T[] array, Comparator<T> comparator, int begin, int end) {
        if (end <= begin) return;
        int pivot = partition(array, comparator, begin, end);
        quickSort(array, comparator, begin, pivot - 1);
        quickSort(array, comparator, pivot + 1, end);
    }

    private static <T> int partition(T[] array, Comparator<T> comparator, int begin, int end) {
        int counter = begin;
        for (int i = begin; i < end; i++) {
            if (comparator.compare(array[i], array[end]) < 0) {
                swap(array, counter, i);
                counter++;
            }
        }
        swap(array, end, counter);
        return counter;
    }

    /**
     * 堆排序
     * 时间复杂度O(NLogN)
     * 空间复杂度O(N)
     *
     * @param array
     * @param comparator
     * @param <T>
     */
    public static <T> void heapSort(T[] array, Comparator<T> comparator) {
        PriorityQueue<T> pq = new PriorityQueue<>(comparator);
        pq.addAll(Arrays.asList(array));
        for (int i = 0; i < array.length; i++)
            array[i] = pq.poll();
    }
}
