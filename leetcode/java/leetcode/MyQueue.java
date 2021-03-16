package leetcode;

import java.util.Stack;

/**
 * @program: CodeAlgorithm
 * @description: 栈实现队列
 * @author: zwh
 * @create: 2021-03-05 08:43
 **/
public class MyQueue {

    private Stack<Integer> stackIn;
    private Stack<Integer> stackOut;

    /** Initialize your data structure here. */
    public MyQueue() {
        this.stackIn = new Stack<>();
        this.stackOut = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stackIn.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(stackOut.size() == 0){
            while(!stackIn.isEmpty()){
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.pop();
    }

    /** Get the front element. */
    public int peek() {
        if(stackOut.size() == 0){
            while (!stackIn.isEmpty()){
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stackIn.isEmpty() && stackOut.isEmpty();
    }
}
