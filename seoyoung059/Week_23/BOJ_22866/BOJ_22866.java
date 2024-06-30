package Week_23.BOJ_22866;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_22866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];
        int[][] answer = new int[n][];

        ArrayDeque<int[]> stack = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = i+1;

            while(!stack.isEmpty() && stack.peekLast()[0] <= arr[i][0]){
                stack.pollLast();
            }

            if(stack.peekLast()==null){
                answer[i] = new int[] {0,-999_999_999};
            } else{
                answer[i] = new int[] {stack.size(), stack.peekLast()[1]};
            }
            stack.offerLast(arr[i]);
        }


        stack.clear();
        for (int i = n-1; i >= 0 ; i--) {
            while(!stack.isEmpty() && stack.peekLast()[0] <= arr[i][0]){
                stack.pollLast();
            }

            answer[i][0]+= stack.size();
            if(!stack.isEmpty() && Math.abs(answer[i][1]-(i+1)) > Math.abs(stack.peekLast()[1]-(i+1))){
                answer[i][1] = stack.peekLast()[1];
            }
            stack.offerLast(arr[i]);
        }


        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if(answer[i][1]!=-999_999_999){
                sb.append(answer[i][0]).append(" ").append(answer[i][1]);
            } else sb.append(0);
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
