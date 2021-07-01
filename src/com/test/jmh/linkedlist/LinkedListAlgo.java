package com.test.jmh.linkedlist;

import java.util.HashMap;

/**
 * 单链表常见操作
 * <p>
 * 1）单链表反转
 * 2）链表中环检测
 * 3）有序链表合并
 * 4）删除链表中倒数第n个结点
 * 5）链表中间结点
 *
 * @author MagicQ
 * @version 1.0
 * @date 2019/1/11
 */
public class LinkedListAlgo {

    /**
     * 单链表反转，双指针迭代法：
     * <p>
     * head 指针用于结点遍历，设置每个结点；
     * prev指针的移动，为head指针遍历结点后设置next提供支持，使其指向之前的结点，实现反转。
     *
     * @param head 当前结点
     * @return 目标结点（第一个结点）
     */
    public static Node reverseList(Node head) {
        Node prev = null;
        while (head != null) {
            Node tmp = head.next;
            head.next = prev;
            prev = head;
            head = tmp;
        }
        return prev;
    }

    /**
     * 单链表反转，头结点插入法：
     * <p>
     * 遍历链表结点，将其设置为头结点的下一个结点，
     * 设置它的下一个结点为头结点原来指向的结点，实现反转。
     *
     * @param current 当前结点
     * @return 反转后的链表第一个结点（即头结点的下一个结点）
     */
    public static Node reverseByHeadInsert(Node current) {
        Node dummy = new Node(-1);
        Node pCur = current;
        while (pCur != null) {
            Node pNex = pCur.next;
            pCur.next = dummy.next;
            dummy.next = pCur;
            pCur = pNex;
        }
        return dummy.next;
    }

    /**
     * 单链表反转，递归法
     *
     * @param head
     * @return
     */
    public static Node reverseListByRecursion(Node head) {
        //哨兵
        if (head == null || head.next == null) {
            return head;
        }
        Node prev = reverseListByRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return prev;
    }

    /**
     * 链表中环检测，快慢指针法：
     * <p>
     * 当快指针遍历到链表结尾时，说明不存在环；
     * 如果在遍历过程中快慢指针相遇，则表中存在环。
     *
     * @param headNode
     * @return
     */
    public static boolean hasCircle(Node headNode) {

        if (headNode == null) {
            return false;
        }
        Node p = headNode;
        Node q = headNode.next;

        while (q != null && q.next != null) {
            p = p.next; // 遍历一个节点
            q = q.next.next; // 遍历两个节点

            // 已到链表末尾
            if (q == null) {
                return false;
            } else if (p == q) {
                // 快慢指针相遇，存在环
                return true;
            }
        }

        return false;
    }

    /**
     * 链表中环检测，足迹法：
     * <p>
     * 使用Map存储遍历过的结点，如果Map中再次出现该结点，则说明链表中存在环。
     *
     * @param list
     * @return
     */
    public static Boolean hasCircle1(Node list) {
        HashMap map = new HashMap();
        while (list != null) {
            if (map.containsKey(list)) {
                return true;
            }
            map.put(list, list.data);
            list = list.next;
        }
        return false;
    }

    /**
     * 删除倒数第n个元素：
     * <p>
     * 将链表反转，删除正数第n个元素后再次反转。
     *
     * @param list 当前结点
     * @return
     */
    public static Node delIndexNode(Node list, int index) {
        Node currentNode = list;
        Node reversNode = null;
        if (currentNode != null && currentNode.next != null) {
            reversNode = reverseByHeadInsert(currentNode);
        }
        int i = 0;
        Node indexNode = null;
        if (i >= index - 2) {
            return reversNode;
        }
        while (reversNode != null && i < (index - 2)) {
            //index 前一个元素
            indexNode = reversNode.next;
            Node delNode = indexNode.next;
            indexNode.next = delNode.next;
            delNode = null;
            i++;
            reversNode = reversNode.next;
        }
        reversNode = reverseByHeadInsert(currentNode);
        return reversNode;
    }

    /**
     * 链表中间结点,快慢指针法：
     * <p>
     * 快指针遍历到链表结尾处时，
     * 慢指针所指的位置即为链表中间位置。
     *
     * @param curNode
     * @return
     */
    public static Node findMidlleNode(Node curNode) {
        if (curNode == null) {
            return null;
        }
        Node slow = curNode;
        Node fast = curNode;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 结点
     */
    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }

    }

    /**
     * 打印结点
     *
     * @param list
     */

    public static void printAll(Node list) {
        Node p = list;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node node1 = new Node(1, null);
        Node node2 = new Node(2, node1);
        Node node3 = new Node(3, node2);
        Node node4 = new Node(4, node3);
        Node node5 = new Node(5, node4);
        Node node6 = new Node(6, node5);
        Node node7 = new Node(7, node6);
        Node node8 = new Node(8, node7);
        Node node9 = new Node(9, node8);
        // Node reversed = reverseList(node3);
        Node reversed = reverseListByRecursion(node9);
        // Node reversed = reverseByHeadInsert(node3);
        // printAll(reversed);
        // Node node4 = new Node(4);
        // Node node5 = new Node(5);
        // Node node6 = new Node(6);
        // Node node7 = new Node(7);
        // node4.next = node5;
        // node5.next = node6;
        // node6.next = node7;
        // node7.next = node4;
        // Node node = delIndexNode(node3, 2);
        // Node node = findMidlleNode(node9);
        printAll(reversed);
    }
}
