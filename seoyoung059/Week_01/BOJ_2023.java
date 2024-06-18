package seoyoung059.Week_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2023 {
    private static  StringBuilder sb = new StringBuilder();
    private static boolean isPrime(int n) {
        for (int i = 2; i*i<=n; i++) {
            if (n%i==0) return false;
        }
        return true;
    }
    private static void solution(int n) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 2; i < 10; i++) {
            if (isPrime(i)) q.offer(i);
        }
        int curr; int tmp;
        while(!q.isEmpty()){
            curr = q.poll();
            if((Integer.toString(curr)).length()==n) {
                sb.append(curr);
                sb.append("\n");
                continue;
            }
            for (int i = 0; i < 5; i++) {
                tmp=10*curr+i*2-1;
                if(Integer.toString(tmp).length()<=n && isPrime(tmp)) {
                    q.offer(tmp);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        solution(n);
        System.out.println(sb.toString());
    }
}
