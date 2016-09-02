package model;

public class ListNode {
    public int value;
    public ListNode next = null;

    public ListNode(int value) {
        this.value = value;
    }

    public static ListNode createList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        ListNode head = new ListNode(arr[0]);
        ListNode node = head;
        for (int i = 1; i < arr.length; i++) {
            ListNode temp = new ListNode(arr[i]);
            node.next = temp;
            node = temp;
        }
        return head;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode node = this;

        while (node != null) {
            sb.append(node.value + ">");
            node = node.next;
        }
        return sb.toString();
    }
}
