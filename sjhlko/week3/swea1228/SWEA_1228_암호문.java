package sjhlko.week3.swea1228;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SWEA_1228_암호문 {
    static class MyList<T> {
        Node<T> head = null;
        static class Node<T> {
            T element;
            Node<T> next;

            public Node(T element, Node<T> next) {
                this.element = element;
                this.next = next;
            }
        }

        public void insert(int index, T insertElement) {
            if (index == 0) {
                head = new Node<>(insertElement, head);
                return;
            }
            Node<T> toInsert = head;
            for (int i = 1; i < index; i++) {
                if (toInsert == null) throw new NullPointerException();
                toInsert = toInsert.next;
            }
            toInsert.next = new Node<>(insertElement, toInsert.next);
        }

        public void delete(int index) {
            if (index == 0) {
                head = head.next;
                return;
            }
            Node<T> toDelete = head;
            for (int i = 1; i < index; i++) {
                if (toDelete == null) throw new NullPointerException();
                toDelete = toDelete.next;
            }
            if (toDelete == null) throw new NullPointerException();
            toDelete.next = toDelete.next.next;
        }

        public void add(T insertElement) {
            if (head == null) {
                head = new Node<>(insertElement, null);
                return;
            }
            Node<T> toInsert = head;
            while (toInsert.next != null)
                toInsert = toInsert.next;
            toInsert.next = new Node<>(insertElement, null);
        }

        public T get(int index) {
            Node<T> toGet = head;
            for (int i = 0; i < index; i++) {
                if (toGet == null) throw new NullPointerException();
                toGet = toGet.next;
            }
            return toGet.element;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; i <= 10; i++) {
            int n = Integer.parseInt(bf.readLine());
            MyList<String> myList = new MyList<>();
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < n; j++) {
                myList.add(st.nextToken());
            }
            int m = Integer.parseInt(bf.readLine());
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++) {
                char c = st.nextToken().charAt(0);
                switch (c) {
                    case 'I':
                        int index = Integer.parseInt(st.nextToken());
                        int count = Integer.parseInt(st.nextToken());
                        for (int k = 0; k < count; k++) {
                            myList.insert(index++, st.nextToken());
                        }
                        break;
                    case 'A':
                        count = Integer.parseInt(st.nextToken());
                        for (int k = 0; k < count; k++) {
                            myList.add(st.nextToken());
                        }
                        break;
                    case 'D':
                        index = Integer.parseInt(st.nextToken());
                        count = Integer.parseInt(st.nextToken());
                        for (int k = 0; k < count; k++) {
                            myList.delete(index);
                        }
                        break;
                }
            }
            System.out.print("#" + i + " ");
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < 10; j++) {
                sb.append(myList.get(j)).append(" ");
            }
            System.out.println(sb);
        }

    }
}