package seoyoung059.Week_39.BOJ_1188;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1188 {

    static int gcd(int a, int b){
        if (a < b) {
            return gcd(b, a);
        }
        if(b==0) return a;
        return gcd(b,a%b);
    }

    static int lcm(int a, int b){
        return a*b / gcd(a, b);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());


        int length = lcm(n, m);



        System.out.println(m - length/lcm(length/n, length/m));

    }
}
