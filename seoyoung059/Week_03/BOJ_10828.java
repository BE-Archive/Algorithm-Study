package seoyoung059.Week_03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10828 {
    static class Stack{
        static final int MAXSIZE=10_002;
        private final int[] arr;
        private int top;
        private int size;
        public Stack(){
            this.arr = new int[MAXSIZE];
            this.top=0;
            this.size=0;
        }

        public void push(int n){
            arr[this.top++]=n;
            this.size++;
        }

        public int pop(){
            if(this.size==0)
                return -1;
            this.size--;
            return this.arr[--this.top];
        }

        public int size(){
            return this.size;
        }

        public int empty(){
            if(size==0) return 1;
            return 0;
        }
        public int top(){
            if(size==0) return -1;
            return this.arr[this.top-1];
        }
    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        Stack stk = new Stack();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            switch(st.nextToken()){
                case "push":
                    stk.push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    sb.append(stk.pop()).append("\n");
                    break;
                case "empty":
                    sb.append(stk.empty()).append("\n");
                    break;
                case "size":
                    sb.append(stk.size()).append("\n");
                    break;
                case "top":
                    sb.append(stk.top()).append("\n");
                    break;
            }

        }
        System.out.print(sb);
    }
}