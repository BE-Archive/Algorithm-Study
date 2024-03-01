package com.javajava.week03;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ10845 {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new Queue<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            if (command.equals("push")) {
                int num = Integer.parseInt(st.nextToken());
                queue.push(num);
            } else if (command.equals("pop")) {
                sb.append(queue.pop()).append("\n");
            } else if (command.equals("size")) {
                sb.append(queue.size()).append("\n");
            } else if (command.equals("empty")) {
                sb.append(queue.empty() ? 1 : 0).append("\n");
            } else if (command.equals("front")) {
                sb.append(queue.front()).append("\n");
            } else if (command.equals("back")) {
                sb.append(queue.back()).append("\n");
            }

        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}

class Queue<E>{
    private QueueNode<E> head;
    private QueueNode<E> tail;
    private int size;

    QueueNode<Integer> minus = new QueueNode<>(-1);

    public Queue() {
        this.head = new QueueNode<>();
        this.tail = null;
        this.size = 0;
    }

    public void push(E e) {
        QueueNode<E> node = new QueueNode<>(e);
        if (head.link == null) {
            head.link = node;
        }
        if (tail != null) {
            tail.link = node;
        }
        tail = node;
        size++;
    }

    public E pop(){
        if(empty()) return (E) minus.element;
        size--;
        QueueNode<E> node = head.link;
        head.link = node.link;
        return node.element;
    }

    public int size(){
        return size;
    }

    public boolean empty(){
        return size == 0;
    }

    public E front(){
        if(empty()) return (E) minus.element;
        return head.link.element;
    }

    public E back(){
        if (empty()) return (E) minus.element;
        return tail.element;
    }
}

class QueueNode<T>{
    T element;
    QueueNode<T> link;

    public QueueNode() {
    }

    public QueueNode(T element) {
        this.element = element;
        this.link = null;
    }
}
