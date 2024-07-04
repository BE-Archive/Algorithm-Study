package Week_11.BOJ_15501;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15501 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        int start = arr1[0];
        int beginIdx=-1;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
            if(arr2[i]==start) beginIdx = i;
        }

        boolean answer = true;
        if(n==1){
            answer = (arr1[0]==arr2[0])?true:false;
        }
        else if(arr2[(beginIdx+1)%n]==arr1[1]){
            for (int i = 0; i < n; i++) {
                if(arr1[i]!= arr2[(beginIdx+i)%n]) {
                    answer = false;
                    break;
                }
            }
        }
        else {
            for (int i = 0; i < n; i++) {
                if(arr1[i]!=arr2[(beginIdx+n-i)%n]) {
                    answer = false;
                    break;
                }
            }
        }
        System.out.println(answer?"good puzzle":"bad puzzle");
    }
}
