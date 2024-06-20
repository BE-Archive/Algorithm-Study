import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3307 {

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc=1; tc<=t; tc++){
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i=0; i<n; i++){
                arr[i]=Integer.parseInt(st.nextToken());
            }

            int[] tmpArr = new int[n];
            tmpArr[0]=arr[0];
            for (int i=1; i<n; i++){
                int tmpIdx=0;
                while (tmpArr[tmpIdx]!=0 && tmpArr[tmpIdx] < arr[i]){
                    tmpIdx++;
                }
                tmpArr[tmpIdx]=arr[i];
            }

            for (int i=0; i<n; i++){
                if (tmpArr[i]==0){
                    sb.append("#").append(tc).append(" ").append(i).append("\n");
                    break;
                }
            }
        }

        System.out.println(sb.toString());
    }
}
