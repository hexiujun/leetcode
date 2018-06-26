package j.examination.wap;


import java.util.Scanner;

/**
 * Created by j on 2017/9/24.
 */
public class Main2 {
    class MyNode {
        int val;
        MyNode next, pre;

        public MyNode() {}

        public MyNode(int x) {
            val = x;
        }
    }

    MyNode head, tail;

    public Main2() {
        head = new MyNode();
        tail = head;
    }

    public void push_front(int x) {
        if (tail == head) {
            tail = new MyNode(x);
            head.next = tail;
            tail.pre = head;
        }

        MyNode node = new MyNode(x);
        node.next = head.next;
        node.next.pre = node;
        head.next = node;
        node.pre = head;
    }

    public void push_back(int x) {
        MyNode node = new MyNode(x);
        node.pre = tail;
        tail.next = node;
        tail = node;
    }

    public int pop_front() {
        if (head == tail) return -1;
        MyNode node = head.next;
        if (tail == head.next) {
            head.next = null;
            tail = head;
        } else {
            head.next = node.next;
            node.next.pre = head;
        }

        return node.val;
    }

    public int pop_back() {
        if (head == tail) return -1;
        int val = tail.val;
        tail = tail.pre;
        tail.next = null;
        return val;
    }

    public void reverse() {
        if (head == tail) return;
        MyNode newHead = new MyNode(), node = head.next, newTail = newHead;
        while (node != null) {
            MyNode tmp = node.next;
            node.next = newHead;
            newHead.pre = node;
            newHead = newHead.pre;
            newHead.pre = null;
            node = tmp;
        }

        tail = newTail.pre;
        tail.next = null;
        head = new MyNode();
        head.next = newHead;
        newHead.pre = head;
    }

    public int max() {
        if (tail == head) return -1;
        MyNode node = head.next;
        int max = -1;
        while (node != null) {
            max = Math.max(max, node.val);
            node = node.next;
        }

        return max;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Main2 instance = new Main2();
        int n = in.nextInt();
        in.nextLine();
        while (n-- > 0) {
            String line = in.nextLine();
            String[] strings = line.split(" ");
            switch (strings[0]) {
                case "push_front" :{
                    instance.push_front(Integer.parseInt(strings[1]));
                    break;}
                case "push_back" :{
                    instance.push_back(Integer.parseInt(strings[1]));
                    break;}
                case "pop_front" : {
                    int val = instance.pop_front();
                    System.out.println(val < 0 ? "Error" : val);
                    break;}
                case "pop_back" :{
                    int val = instance.pop_back();
                    System.out.println(val < 0 ? "Error" : val);
                    break;}
                case "reverse" : {
                    instance.reverse();
                    break;
                }
                case "max" : {
                    int val = instance.max();
                    System.out.println(val < 0 ? "Error" : val);
                    break;
                }
                default:break;
            }
        }
    }
}
