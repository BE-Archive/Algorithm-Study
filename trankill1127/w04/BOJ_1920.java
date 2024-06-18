import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] arr1 = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i=0; i<n; i++){
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr1);

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        for (int i=0; i<m; i++){
            int target = Integer.parseInt(st.nextToken());

            int result=0;
            int s=0; int e=n-1;
            while (s<=e){
                int mid=(s+e)/2;
                if (arr1[mid]<target){
                    s=mid+1;
                }
                else if (arr1[mid]==target){
                    result=1;
                    break;
                }
                else {
                    e=mid-1;
                }
            }

            sb.append(result).append("\n");
        }

        System.out.println(sb.toString());

    }
}
