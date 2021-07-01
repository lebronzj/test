package com.test.jmh.linkedlist;

/**
 * 基于单链表实现LRU缓存淘汰算法
 * 理解指针（引用）的意义，头指针是一个data与next均为null的空元素，存储了单链表的基地址，
 * 尾结点的next指向空
 * 元素的删除与插入实际是移动头指针改变相邻元素引用关系删除数据的过程
 *
 * @author MagicQ
 * @version 1.0
 * @date 2019/1/10
 */
public class LRUSingleLinkedList<T> {
    /**
     * 默认缓存容量
     */
    private final static int DEFAULT_CAPACITY = 10;
    /**
     * 头结点
     */
    private SNode<T> headNode;
    /**
     * 链表实际长度
     */
    private int length;
    /**
     * 缓存容量
     */
    private int capacity;

    public LRUSingleLinkedList() {
        this.headNode = new SNode<>();
        this.capacity = DEFAULT_CAPACITY;
        this.length = 0;
    }

    public LRUSingleLinkedList(int capacity) {
        this.headNode = new SNode<>();
        this.capacity = capacity;
        this.length = 0;
    }

    public void add(T data) {
        SNode preNode = findPreNode(data);
        //链表中存在，删除原数据，再插入链表的头部
        if (preNode != null) {
            deleteElemOptim(preNode);
            intsertElemAtBegin(data);
        } else {
            //当前缓存是否满
            if (length >= this.capacity) {
                //删除尾结点
                deleteElemAtEnd();
            }
            intsertElemAtBegin(data);

        }
    }

    private void printAll() {
        SNode node = headNode.getNext();
        System.out.println(headNode.getData() + "," + headNode.getNext());
        while (node != null) {
            System.out.println(node.getData() + "," + node.getNext());
            node = node.getNext();
        }
    }

    /**
     * 获取查找到元素的前一个结点
     *
     * @param data
     * @return
     */
    private SNode findPreNode(T data) {
        SNode node = headNode;
        while (node.getNext() != null) {
            if (data.equals(node.getNext().getData())) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    /**
     * 删除preNode结点下一个元素
     *
     * @param preNode
     */
    private void deleteElemOptim(SNode preNode) {
        SNode temp = preNode.getNext();
        preNode.setNext(temp.getNext());
        temp = null;
        length--;
    }

    /**
     * 删除尾结点
     */
    private void deleteElemAtEnd() {
        SNode ptr = headNode;
        // 空链表直接返回
        if (ptr.getNext() == null) {
            return;
        }

        //移动指针至倒数第二个结点
        while (ptr.getNext().getNext() != null) {
            ptr = ptr.getNext();
        }
        //尾结点
        SNode tmp = ptr.getNext();
        ptr.setNext(null);
        tmp = null;
        length--;
    }

    /**
     * 链表头部插入节点
     *
     * @param data
     */
    private void intsertElemAtBegin(T data) {
        SNode next = headNode.getNext();
        headNode.setNext(new SNode(data, next));
        length++;
    }

    public static class SNode<T> {
        private T data;
        private SNode<T> next;

        public SNode(T data) {
            this.data = data;
        }

        public SNode(T data, SNode next) {
            this.data = data;
            this.next = next;
        }

        public SNode() {
            this.next = null;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public SNode getNext() {
            return next;
        }

        public void setNext(SNode next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LRUSingleLinkedList list = new LRUSingleLinkedList();
        list.add("1");
        list.add("2");
        list.add("1");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");
        list.add("10");
        list.add("11");
        list.add("12");
        // list.printAll();
    }
}
