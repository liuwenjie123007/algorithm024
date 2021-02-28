

# 学习笔记

## 题解：使用二分查找，寻找一个半有序数组 [4, 5, 6, 7, 0, 1, 2] 中间无序的地方

### 实现具体参考Week_04.BinarySearch#findDeviceIndex

### 解题思路

- 题目分析

  - 由于是有序数组的旋转数组，寻找无序的地方等效于寻找最小元素下标。
  - 下面题解转化为寻找最小元素下标题解。

- 二分查找解法

  - 确定最小元素所在区间

    - 旋转数组及其子区间会存在三种情况
      - 最小元素在中间下标左侧
        - 则必有nums[left] > nums[mid]
        - 下一步取子区间[left, mid - 1], 也将是包含最小元素的旋转数组或包含最小元素的排序数组。
      - 最小元素在中间下标右侧
        - 则必有nums[left] < nums[mid]
        - 下一步取子区间[mid + 1, right]，也将是包含最小元素的旋转数组或包含最小元素的排序数组。
      - 最小元素在中间下标上
        - 则必有nums[left]  < nums[right] 
        - 下一步取子区间[left, mid]，也将是包含最小元素的旋转数组或包含最小元素的排序数组。

  - 确定循环结束条件

    - 在子区间中只可能出现三种情况
      - 只有一个元素
        - 此时nums[left] = nums[right]
        - 返回当前元素即解
      - 是旋转数组
        - 此时nums[left] > nums[right]
        - 继续递归查找
      - 排序数组
        - 此时nums[left] > nums[right]
        - 返回left即解

  - 在二分查找模板上套用上述分析

    - 确定循环结束条件

      ```java
      if (nums[left] <= nums[right])
             return left;
      ```

    - 旋转数组及其子区间会存在三种情况

      ```java
       	int mid = left + (right - left) / 2;
        if (nums[mid] >= nums[left]) {
          left = mid + 1;
        } else {
          // 这里合并了第一，第三种情况
          right = mid;
        }
      ```

  - 完整代码

    ```java
    		public static int findDeviceIndex(int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                if (nums[left] <= nums[right])
                    return left;
                int mid = left + (right - left) / 2;
                if (nums[mid] >= nums[left]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            return -1;
        }
    ```

  - 测试用例

    ```java
    		public static void main(String[] args) {
            int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
            int deviceIndex = findDeviceIndex(nums1);
            System.out.println(deviceIndex);
    
            int[] nums2 = {5, 6, 7, 0, 1, 2, 4};
            deviceIndex = findDeviceIndex(nums2);
            System.out.println(deviceIndex);
    
            int[] nums3 = {6, 7, 0, 1, 2, 4, 5};
            deviceIndex = findDeviceIndex(nums3);
            System.out.println(deviceIndex);
    
            int[] nums4 = {0, 1, 2, 4, 5, 6, 7};
            deviceIndex = findDeviceIndex(nums4);
            System.out.println(deviceIndex);
    
            int[] nums5 = {1, 2, 4, 5, 6, 7, 0};
            deviceIndex = findDeviceIndex(nums5);
            System.out.println(deviceIndex);
        }
    ```

    - 最小元素位于下标，4，3，2，0，6

## DFS代码模板

```java
		public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> allResults = new ArrayList<>();
        if (root == null) {
            return allResults;
        }
        travel(root, 0, allResults);
        return allResults;
    }

    private void travel(TreeNode root, int level, List<List<Integer>> results) {
        if (results.size() == level) {
            results.add(new ArrayList<>());
        }
        results.get(level).add(root.val);
        if (root.left != null) {
            travel(root.left, level + 1, results);
        }
        if (root.right != null) {
            travel(root.right, level + 1, results);
        }
    }
```

## BFS代码模板

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
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
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

## 二分查找模板

```java
    public int binarySearch(int[] array, int target) {
        int left = 0, right = array.length - 1, mid;
        while (left <= right) {
            mid = (right - left) / 2 + left;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
```

