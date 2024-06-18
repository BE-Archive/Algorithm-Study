package seoyoung059.Week_03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10845 {
    static class Queue{
        static final int MAXSIZE=10_002;
        private final int[] arr;
        private int front;
        private int back;
        private int size;
        public Queue(){
            this.arr = new int[MAXSIZE];
            this.front=0;
            this.back=0;
            this.size=0;
        }

        public void push(int n){
            arr[this.back++]=n;
            this.size++;
        }

        public int pop(){
            if(this.size==0)
                return -1;
            this.size--;
            return this.arr[this.front++];
        }

        public int size(){
            return this.size;
        }

        public int empty(){
            if(size==0) return 1;
            return 0;
        }
        public int front(){
            if(size==0) return -1;
            return this.arr[this.front];
        }
        public int back(){
            if(size==0) return -1;
            return this.arr[this.back-1];
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        Queue q = new Queue();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            switch(st.nextToken()){
                case "push":
                    q.push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    sb.append(q.pop()).append("\n");
                    break;
                case "empty":
                    sb.append(q.empty()).append("\n");
                    break;
                case "size":
                    sb.append(q.size()).append("\n");
                    break;
                case "front":
                    sb.append(q.front()).append("\n");
                    break;
                case "back":
                    sb.append(q.back()).append("\n");
            }

        }
        System.out.print(sb);
    }
}

