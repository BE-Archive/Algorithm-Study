package sjhlko.week3.BOJ10845;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10845_큐_서정희 {
    //https://www.acmicpc.net/problem/10845
    //큐
    
    static int N;
    static MyQueue<Integer> myQueue = new MyQueue<>();

    static class MyQueue<T> {
        Node<T> front = null;
        Node<T> back = null;

        static class Node<T> {
            T element;
            Node<T> next;

            public Node(T element, Node<T> next) {
                this.element = element;
                this.next = next;
            }
        }

        public void push(T element) {
            if (front == null) {
                front = back = new Node<>(element, null);
                return;
            }
            back.next = new Node<>(element, null);
            back = back.next;
        }

        public T pop() {
            if (front == null) return null;
            T ret = front.element;
            front = front.next;
            return ret;
        }

        public T front() {
            if (front == null) return null;
            return front.element;
        }

        public T back() {
            if (isEmpty()) return null;
            return back.element;
        }

        public int size() {
            int count = 0;
            Node<T> start = front;
            while (start != null) {
                count++;
                start = start.next;
            }
            return count;
        }

        public boolean isEmpty() {
            return size() == 0;
        }

    }

    static void solution(String command, int element) {
        switch (command) {
            case "push":
                myQueue.push(element);
                break;
            case "back":
                Integer back;
                System.out.println((back = myQueue.back()) == null ? -1 : back);
                break;
            case "front":
                Integer front;
                System.out.println((front = myQueue.front()) == null ? -1 : front);
                break;
            case "size":
                System.out.println(myQueue.size());
                break;
            case "empty":
                System.out.println(myQueue.isEmpty() ? 1 : 0);
                break;
            case "pop":
                Integer pop;
                System.out.println((pop = myQueue.pop()) == null ? -1 : pop);
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            if (st.countTokens() == 2)
                solution(st.nextToken(), Integer.parseInt(st.nextToken()));
            else solution(st.nextToken(), -1);
        }
    }
}
