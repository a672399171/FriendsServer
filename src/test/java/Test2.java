import model.ListNode;
import model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Test2 {
    @Test
    public void test() {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        int[] arr = {3, 6, 7, 1};
        // TreeNode treeNode = reConstructTree(pre, 0, pre.length - 1, in, 0);
        // System.out.println(minNumberInRotateArray(arr));

        /*ListNode head = ListNode.createList(new int[]{1, 2, 3});
        head = reverseList(head);*/

        /*ListNode head1 = ListNode.createList(new int[]{1, 3, 7});
        ListNode head2 = ListNode.createList(new int[]{2, 4, 5, 6});
        ListNode head = mergeList(head1, head2);
        System.out.println(head);*/

        /*int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        printMatrix(matrix);*/
    }

    // 22.从上到下，从左到右打印二叉树
    private void printTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        if(root == null) {
            return;
        }
        queue.offer(root);
        while(!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            System.out.println(temp.value);
            if(temp.left != null) {
                queue.offer(temp.left);
            }
            if(temp.right != null) {
                queue.offer(temp.right);
            }
        }
    }

    // 19.顺时针打印矩阵
    private void printMatrix(int[][] matrix) {
        int circle = 0;
        if (matrix == null) {
            return;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int min = Math.min(rows, cols);
        circle = min <= 3 ? 1 : min - 2;
        for (int i = 0; i < circle; i++) {
            // 从左往右
            for (int j = i; j < cols - i; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            // 从上往下
            for (int j = i + 1; j < cols - i; j++) {
                System.out.print(matrix[j][cols - 1 - i] + " ");
            }
            // 从右往左
            for (int j = cols - 2 - i; j >= i; j--) {
                System.out.print(matrix[rows - 1 - i][j] + " ");
            }
            // 从下往上
            for (int j = rows - 2 - i; j > i; j--) {
                System.out.print(matrix[j][i] + " ");
            }
        }
    }

    // 16.合并递增链表
    private ListNode mergeList(ListNode head1, ListNode head2) {
        ListNode head = null;
        if (head1 == null) {
            head = head2;
        }
        if (head2 == null) {
            head = head1;
        }
        ListNode node1 = head1, node2 = head2, node = head;
        if (head1.value < head2.value) {
            head = head1;
            node1 = head1.next;
        } else {
            head = head2;
            node2 = head2.next;
        }

        node = head;
        if (node != null) {
            while (node1 != null && node2 != null) {
                if (node1.value < node2.value) {
                    node.next = node1;
                    node1 = node1.next;
                } else {
                    node.next = node2;
                    node2 = node2.next;
                }
                node = node.next;
            }
            if (node1 != null) {
                node.next = node1;
            } else if (node2 != null) {
                node.next = node2;
            }
        }

        return head;
    }

    // 15.逆转链表
    private ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head, current = head.next, next = head.next.next;
        pre.next = null;
        while (next != null) {
            ListNode temp = next.next;
            current.next = pre;
            next.next = current;

            pre = current;
            current = next;
            next = temp;
        }
        return current;
    }

    // 9.旋转数组最小值
    private int minNumberInRotateArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int low = 0, high = arr.length - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] > arr[high]) {
                low = mid + 1;
            } else if (arr[mid] == arr[high]) {
                high--;
            } else {
                high = mid;
            }
        }
        return arr[low];
    }

    // 7.由前序遍历和中序遍历生成后序遍历
    private TreeNode reConstructTree(int[] pre, int startPre, int endPre, int[] in, int startIn) {
        if (pre == null || in == null) {
            return null;
        }
        int index = startIn;
        TreeNode root = null;
        if (startPre <= endPre) {
            root = new TreeNode(pre[startPre]);
            while (in[index] != pre[startPre] && index <= startIn + endPre - startPre) {
                index++;
            }
            TreeNode left = reConstructTree(pre, startPre + 1, startPre + index - startIn, in, startIn);
            TreeNode right = reConstructTree(pre, startPre + index - startIn + 1, endPre, in, index + 1);
            if (left != null) {
                left.parent = root;
            }
            if (right != null) {
                right.parent = root;
            }
            root.left = left;
            root.right = right;
        }
        return root;
    }
}