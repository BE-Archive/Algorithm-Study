import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10828 {

    static class MyStack{
        private int[] storage;
        private int usedStorage;

        public MyStack(int n) {
            storage = new int[n];
            usedStorage=0;
        }

        public void push(int value) {
            storage[usedStorage]=value;
            usedStorage++;
        }
        public int pop() {
            if (usedStorage==0) return -1;

            usedStorage--;
            return storage[usedStorage];
        }
        public int size() {
            return usedStorage;
        }
        public int empty() {
            if (usedStorage>0) return 0;
            return 1;
        }
        public int top() {
            if (usedStorage==0) return -1;
            return (int)storage[usedStorage-1];
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        MyStack stack = new MyStack(n);
        while (n>0) {
            st = new StringTokenizer(br.readLine(), " ");

            String command = st.nextToken();

            if (command.equals("push")) {
                stack.push(Integer.parseInt(st.nextToken()));
            }
            else if (command.equals("pop")) {
                sb.append(stack.pop()).append("\n");
            }
            else if (command.equals("size")) {
                sb.append(stack.size()).append("\n");
            }
            else if (command.equals("empty")) {
                sb.append(stack.empty()).append("\n");
            }
            else if (command.equals("top")) {
                sb.append(stack.top()).append("\n");
            }

            n--;
        }

        System.out.print(sb.toString());
    }
}