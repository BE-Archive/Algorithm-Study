package seoyoung059.Week_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1230 {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static class Node {
        Node prev;
        Node next;
        int value;

        public Node (int v) {
            this.value = v;
        }
    }
    static class LinkedList {
        Node head;
        Node tail;
        int size;

        public LinkedList() {
            this.head = new Node(-1);
            this.tail = new Node(-1);
            this.head.next = this.tail;
            this.tail.prev = this.head;
        }

        Node getNode(int k){
            if(size<k) return null;
            Node tmp = this.head;
            for (int i = 0; i < k; i++) {
                tmp = tmp.next;
            }
            return tmp;
        }

        void insertTail(Node newNode){
            Node tmp = this.tail.prev;
            newNode.next = this.tail;
            this.tail.prev = newNode;
            tmp.next = newNode;
            newNode.prev = tmp;
            size++;
        }

        boolean insertList(LinkedList insertList, int idx){
            if(this.size<idx) return false;
            Node insertStart = this.getNode(idx);
            Node insertEnd = insertStart.next;
            insertEnd.prev = insertList.tail.prev;
            insertList.tail.prev.next = insertEnd;
            insertStart.next = insertList.head.next;
            insertList.head.next.prev = insertStart;
            this.size+=insertList.size;
            return true;
        }


        boolean removeNNode(int idx, int n){
            Node front = this.getNode(idx);
            Node end = this.getNode(idx+n+1);
            front.next = end;
            end.prev = front;
            size-=n;
            return true;
        }

        @Override
        public String toString() {
            StringBuilder listsb = new StringBuilder();
            Node tmp = this.head.next;
            for (int i = 0; i < this.size; i++) {
                listsb.append(tmp.value);
                listsb.append(" ");
                tmp = tmp.next;
            }
            return listsb.toString();
        }

        public String first10() {
            StringBuilder listsb = new StringBuilder();
            Node tmp = this.head.next;
            for (int i = 0; i < 10; i++) {
                listsb.append(tmp.value);
                listsb.append(" ");
                tmp = tmp.next;
            }
            return listsb.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;
        int n, m;
        for (int test_case = 1; test_case <= T; test_case++) {
            n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            LinkedList origin = new LinkedList();
            for (int i = 0; i < n; i++) {
                origin.insertTail(new Node(Integer.parseInt(st.nextToken())));
            }

            m = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int x, y;
            for (int i = 0; i < m; i++) {
                switch (st.nextToken().charAt(0)) {
                    case 'I':
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());
                        LinkedList insertList = new LinkedList();
                        for (int j = 0; j < y; j++) {
                            insertList.insertTail(new Node(Integer.parseInt(st.nextToken())));
                        }
                        origin.insertList(insertList,x);
                        break;
                    case 'D':
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());
                        origin.removeNNode(x,y);

                        break;
                    case 'A':
                        y = Integer.parseInt(st.nextToken());
                        for (int j = 0; j < y; j++) {
                            origin.insertTail(new Node(Integer.parseInt(st.nextToken())));
                        }
                        break;

                }
            }

            sb.append("#");
            sb.append(test_case);
            sb.append(" ");
            sb.append(origin.first10());
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}