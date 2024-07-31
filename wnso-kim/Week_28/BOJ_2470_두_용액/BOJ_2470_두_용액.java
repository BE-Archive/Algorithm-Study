import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2470_두_용액 {

    static int N;
    static int[] arr;
    static int l,r;
    static int L,R,min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer stk = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(stk.nextToken());
        }

        Arrays.sort(arr);

        L = l = 0;
        R = r = arr.length-1;
        min = 2_000_000_000;

        while(l<r){
            if(Math.abs(arr[l]+arr[r]) < Math.abs(min)){
                min = Math.abs(arr[l]+arr[r]);
                L = l; R = r;
            }

            if(Math.abs(arr[l+1]+arr[r]) < Math.abs(arr[l]+arr[r-1])){
                l++;
            }else{
                r--;
            }
        }

        System.out.println(arr[L] + " " + arr[R]);
    }
}
