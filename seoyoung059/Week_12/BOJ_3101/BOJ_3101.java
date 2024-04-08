package Week_12.BOJ_3101;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class BOJ_3101 {


    static long getVal(long y, long x, long n){
        long tmp = x+y;
        long tmp2;

        if(tmp < n){
            tmp2 = tmp*(tmp+1)/2;
            if(tmp%2==0){
                tmp2+=1+x;
            }
            else {
                tmp2+=1+y;
            }
        }else {
            tmp2 = n*n - (2*n-tmp)*(2*n-tmp-1)/2;
            if(tmp%2==0){
                tmp2+=x-(tmp-n);
            }
            else {
                tmp2+=y-(tmp-n);
            }
        }
        return tmp2;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        String str = br.readLine();
        long y=0, x = 0;
        long sum = 1;
        for (int i = 0; i < k; i++) {
            switch (str.charAt(i)){
                case 'U':
                    y--;
                    break;
                case 'D':
                    y++;
                    break;
                case 'L':
                    x--;
                    break;
                case 'R':
                    x++;
                    break;
            }
            sum+= getVal(y, x, n);
        }
        System.out.println(sum);
    }
}