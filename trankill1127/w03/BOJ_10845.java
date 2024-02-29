import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10845 {

    static class MyQueue{
        private int[] storage;
        private int usedStorage;

        public MyQueue(int n) {
            storage = new int[n];
            usedStorage=0;
        }

        public void push(int value) {
            storage[usedStorage]=value;
            usedStorage++;
        }
        public int pop() {
            if (usedStorage==0) return -1;

            int tmp = storage[0];
            for (int i=1; i<usedStorage; i++) {
                storage[i-1]=storage[i];
            }
            usedStorage--;
            return tmp;
        }
        public int size() {
            return usedStorage;
        }
        public int empty() {
            if (usedStorage>0) return 0;
            return 1;
        }
        public int front() {
            if (usedStorage==0) return -1;
            return storage[0];
        }
        public int back() {
            if (usedStorage==0) return -1;
            return storage[usedStorage-1];
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        MyQueue queue = new MyQueue(n);
        while (n>0) {
            st = new StringTokenizer(br.readLine(), " ");

            String command = st.nextToken();

            if (command.equals("push")) {
                queue.push(Integer.parseInt(st.nextToken()));
            }
            else if (command.equals("pop")) {
                sb.append(queue.pop()).append("\n");
            }
            else if (command.equals("size")) {
                sb.append(queue.size()).append("\n");
            }
            else if (command.equals("empty")) {
                sb.append(queue.empty()).append("\n");
            }
            else if (command.equals("front")) {
                sb.append(queue.front()).append("\n");
            }
            else { //back
                sb.append(queue.back()).append("\n");
            }

            n--;
        }

        System.out.print(sb.toString());
    }

}