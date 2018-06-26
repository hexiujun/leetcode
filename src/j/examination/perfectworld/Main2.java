package j.examination.perfectworld;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by j on 2017/9/26.
 */
public class Main2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        MyStack myStack = new MyStack();
        for (int i = 0; i < n; i++) {
            myStack.push(in.nextInt());
        }
        myStack.pop();

        System.out.println(myStack.max() + "," + myStack.min());
    }

    static class MyStack {
        Stack<Integer> stack, min, max;

        public MyStack() {
            stack = new Stack<>();
            min = new Stack<>();
            max = new Stack<>();
        }

        void push(int data) {
            stack.push(data);
            int minVal = data, maxVal = data;
            if (!min.empty() && minVal > min.peek())
                minVal = min.peek();
            if (!max.empty() && maxVal < max.peek())
                maxVal = max.peek();
            min.push(minVal);
            max.push(maxVal);
        }

        int pop() {
            int val = stack.pop();
            min.pop();
            max.pop();
            return val;
        }

        int min() {

            return min.peek();
        }

        int max() {

            return max.peek();
        }
    }
}
