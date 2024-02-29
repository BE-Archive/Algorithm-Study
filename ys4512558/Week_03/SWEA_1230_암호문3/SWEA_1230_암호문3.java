import java.io.*;
import java.util.StringTokenizer;
 
public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        int T = 10;
 
        for (int i = 1; i <= T; i++) {
            sb.append("#").append(i).append(" ");
            solve();
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
 
    private static void solve() throws IOException {
        LinkedList<Integer> list = new LinkedList<>();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.valueOf(st.nextToken()));
        }
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            String command = st.nextToken();
            if (command.equals("I")) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
 
                for (int j = 0; j < y; j++) {
                    int code = Integer.parseInt(st.nextToken());
                    list.insert(x + j, code);
                }
            } else if (command.equals("D")) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                list.delete(x, y);
            } else if (command.equals("A")) {
                int y = Integer.parseInt(st.nextToken());
                for (int j = 0; j < y; j++) {
                    int s = Integer.parseInt(st.nextToken());
                    list.add(s);
                }
            }
        }
        sb.append(list.toString()).append("\n");
    }
}
 
class LinkedList<E> {
    private ListNode<E> head;
    private ListNode<E> tail;
 
    public LinkedList() {
        this.head = new ListNode<>();
    }
 
    public void add(E e) {
        ListNode<E> node = new ListNode<>(e);
        if (head.link == null) {
            head.link = node;
        }
        if (tail != null) {
            tail.link = node;
        }
        tail = node;
    }
 
    public void insert(int x, E e) {
        ListNode<E> node = new ListNode<>(e);
        ListNode<E> pre = getNode(x);
 
        node.link = pre.link;
        pre.link = node;
    }
 
    public void delete(int x, int y) {
        ListNode<E> pre = getNode(x);
        ListNode<E> next = getNode(x + y);
        pre.link = next.link;
    }
 
    private ListNode<E> getNode(int x) {
        ListNode<E> current = head;
        for (int i = 0; i < x; i++) {
            current = current.link;
        }
        return current;
    }
 
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int N = 10;
        ListNode<E> current = head.link;
        for (int i = 0; i < N; i++, current = current.link) {
            sb.append(current.cryptogram).append(" ");
        }
        return sb.toString();
    }
}
 
class ListNode<T>{
    T cryptogram;
    ListNode<T> link;
 
    public ListNode() {
    }
 
    public ListNode(T cryptogram) {
        this.cryptogram = cryptogram;
        this.link = null;
    }
}