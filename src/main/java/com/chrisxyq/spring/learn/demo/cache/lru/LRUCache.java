package com.chrisxyq.spring.learn.demo.cache.lru;

import java.util.HashMap;
import java.util.Map;

class LRUCache {
    class Node {
        int key;
        int value;
        Node prev;
        Node next;
        Node() {
        }
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    private int size;
    private int                capacity;
    private Map<Integer, Node> cache;
    /* 虚拟头节点 */
    private Node               head;
    /* 虚拟尾节点 */
    private Node tail;
    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        cache = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }
    public int get(int key) {
        Node node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 将最近这次访问的数据移到头部
        moveToHead(node);
        return node.value;
    }
    public void put(int key, int value) {
        Node node = cache.get(key);
        if (node == null) {
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            // 将最近新增的数据放到头部
            addToHead(newNode);
            ++size;
            // 若数据量超过设定的最大容量，移除尾部（最不常访问）的节点数据
            if (size > capacity) {
                Node tail = removeTail();
                // 缓存淘汰，移除尾部节点数据
                cache.remove(tail.key);
                --size;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }
    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    private void addToHead(Node node) {
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
        node.prev = head;
    }
    private Node removeTail() {
        Node node = tail.prev;
        removeNode(node);
        return node;
    }
}
