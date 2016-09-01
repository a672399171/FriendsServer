import model.ListNode;
import model.TreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test2 {
    @Test
    public void test() {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        int[] arr = {3, 6, 7, 1};
        // TreeNode treeNode = reConstructTree(pre, 0, pre.length - 1, in, 0);
        // System.out.println(minNumberInRotateArray(arr));

        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        head.next = node2;
        node2.next = node3;

        // head = reverseList(head);
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