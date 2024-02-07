package sjhlko.week3.BOJ10828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;
import java.util.StringTokenizer;

public class BOJ_10828_스택_서정희 {
    //https://www.acmicpc.net/problem/10828
    //스택
    static int N;
    static MyStack<Integer> myStack = new MyStack<>();
    static class MyStack<T> {
        Node<T> top = null;

        static class Node<T> {
            T element;
            Node<T> next;

            public Node(T element, Node<T> next) {
                this.element = element;
                this.next = next;
            }


        }

        public void push(T element) {
            if (top == null) {
                top = new Node<>(element, null);
                return;
            }
            top = new Node<>(element, top);
        }

        public T top() {
            if (top == null) throw new EmptyStackException();
            return top.element;
        }

        public int size() {
            Node<T> start = top;
            int count = 0;
            while (start != null) {
                count++;
                start = start.next;
            }
            return count;
        }

        public T pop() {
            if (top == null) throw new EmptyStackException();
            T element = top.element;
            top = top.next;
            return element;
        }

        public boolean isEmpty() {
            return top == null;
        }

    }

    static void solution(String command, int element) {
        switch (command) {
            case "push":
                myStack.push(element);
                break;
            case "top":
                try {
                    System.out.println(myStack.top());
                } catch (EmptyStackException e){
                    System.out.println(-1);
                }
                break;
            case "size":
                System.out.println(myStack.size());
                break;
            case "empty":
                System.out.println(myStack.isEmpty()?1:0);
                break;
            case "pop":
                try {
                    System.out.println(myStack.pop());
                } catch (EmptyStackException e){
                    System.out.println(-1);
                }
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            if(st.countTokens()==2)
                solution(st.nextToken(), Integer.parseInt(st.nextToken()));
            else solution(st.nextToken(), -1);
        }
    }
}
