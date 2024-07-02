package com.javajava.week03;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ10828 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if(cmd.equals("push")){
                stack.push(Integer.valueOf(st.nextToken()));
            } else if(cmd.equals("pop")){
                sb.append(stack.pop()).append("\n");
            } else if(cmd.equals("size")){
                sb.append(stack.size()).append("\n");
            } else if(cmd.equals("empty")){
                sb.append(stack.empty() ? 1 : 0).append("\n");
            } else{
                sb.append(stack.top()).append("\n");
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

class Stack<E>{
    StackNode<E> top;
    StackNode<Integer> minus = new StackNode<>(-1);
    int size;
    public Stack() {
        this.top = new StackNode<>();
        size = 0;
    }

    public void push(E e) {
        StackNode<E> stackNode = new StackNode<>(e);
        stackNode.link = top;
        top = stackNode;
        size++;
    }

    public E pop() {
        if (empty()) return (E) minus.element;
        StackNode<E> stackNode = top;
        top = stackNode.link;
        size--;
        return stackNode.element;
    }

    public int size(){
        return size;
    }

    public boolean empty(){
        return size == 0;
    }

    public E top(){
        if (empty()) return (E) minus.element;
        return top.element;
    }
}

class StackNode<T>{
    T element;
    StackNode<T> link;

    public StackNode() {
    }

    public StackNode(T element) {
        this.element = element;
        this.link = null;
    }
}