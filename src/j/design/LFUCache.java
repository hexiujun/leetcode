package j.design;

import java.util.HashMap;

/**
 * Created by j on 2017/6/4.
 */
public class LFUCache {
    int capacity;
    HashMap<Integer, LFUNode> map;
    LFUNode head;

    public LFUCache(int capacity) {
        map = new HashMap<>(capacity);
        this.capacity = capacity;
        head = new LFUNode();
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        LFUNode node = map.get(key), insertNode = node;
        node.freq++;
        while (insertNode.next != null && insertNode.next.freq <= node.freq)
            insertNode = insertNode.next;
        if (insertNode != node) {
            node.delete();
            insertNode.insert(node);
        }

        return map.get(key).val;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        LFUNode node;
        if (map.containsKey(key)) {
            node = map.get(key);
            node.val = value;
            node.freq++;
        } else {
            node = new LFUNode(key, value, 1);
            if (map.size() == capacity) {   //if full, delete one
                LFUNode deleteNode = head.next;
                deleteNode.delete();
                map.remove(deleteNode.key);
            }
            map.put(key, node);
            head.insert(node);
        }

        LFUNode insertNode = node;
        while (insertNode.next != null && insertNode.next.freq <= node.freq)
            insertNode = insertNode.next;
        if (insertNode != node) {
            node.delete();
            insertNode.insert(node);
        }
    }

    private class LFUNode {
        int key;
        int val;
        int freq;
        LFUNode next, pre;

        public LFUNode() {}
        public LFUNode(int key, int val, int freq) {
            this.key = key;
            this.val = val;
            this.freq = freq;
        }

        /**
         * 队列中插入一个节点至当前节点后
         * @parameter node，被插入的节点
         */
        public void insert(LFUNode node) {
            node.next = this.next;
            if (this.next != null)
                this.next.pre = node;
            this.next = node;
            node.pre = this;
        }

        /**
         * 将当前节点从队列中删除
         */
        public void delete() {
            this.pre.next = this.next;
            if(this.next != null)
                this.next.pre = this.pre;
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
