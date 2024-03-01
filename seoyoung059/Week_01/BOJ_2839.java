// 백준 2839 설탕 배달

package seoyoung059.Week01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
public class BOJ_2839 {

  
    private static void solution (int n) {
        int[] arr = new int[n+1];
        for (int i = 3; i <= n; i++) {
            arr[i]=-1;
            if (i==3||i==5) arr[i]=1;
            else if (i>5) {
                if (arr[i - 5] > 0 && arr[i - 3] > 0)
                    arr[i] = Math.min(arr[i - 5], arr[i - 3]) + 1;
                else if (arr[i - 5] > 0) arr[i] = arr[i - 5] + 1;
                else if (arr[i - 3] > 0) arr[i] = arr[i - 3] + 1;
            }
        }
        if (arr[n]>0)
            System.out.println(arr[n]);
        else System.out.println(-1);
    }

    
    private static int solution2(int n) {
        int a = n/5;
        int b = n%5;
        while (a > -1){
            if (b%3==0)
                return a+b/3;
            else {
                a--;
                b += 5;
            }
        }
        return -1;
    }

    private static int solution3(int n) {
        int num = 0;
        while (n>-1) {
            if (n%5!=0){
                n-=3;
                num++;
            } else {
                num+=n/5;
                return num;
            }
        }
        return -1;
    }

    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
//        solution(n);
//        System.out.println(solution2(n));
        System.out.println(solution3(n));
    }
}
