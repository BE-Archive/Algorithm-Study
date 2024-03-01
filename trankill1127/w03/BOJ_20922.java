import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20922 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n =Integer.parseInt(st.nextToken());
        int k =Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        int[] arr = new int[n];
        for (int i=0; i<n; i++) arr[i]=Integer.parseInt(st.nextToken());

        int maxLen=0;
        int[] map = new int[100001];
        int j=0;
        for (int i=0; i<n; i++){
            map[arr[i]]++;

            while (map[arr[i]]>k){
                map[arr[j]]--;
                j++;
            }
            maxLen = Math.max(maxLen, i-j+1);
        }

        System.out.println(maxLen);
    }
}
