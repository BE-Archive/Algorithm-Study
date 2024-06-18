import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10974 {

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        number=new int[n];
        for (int i=0; i<n; i++) number[i]=i+1;

        memo=new int[n];
        visited=new int[n];

        permutation(0);

        System.out.println(sb.toString());
    }

    public static int n;
    public static int[] number;
    public static int[] memo;
    public static int[] visited;
    public static StringBuilder sb;

    public static void permutation(int idx){
        if (idx==n){
            for (int i=0; i<idx; i++){
                sb.append(memo[i]+" ");
            }
            sb.append("\n");
            return;
        }

        for (int i=0; i<n; i++){
            if (visited[i]==0){
                visited[i]=1;
                memo[idx]=number[i];
                permutation(idx+1);
                visited[i]=0;
            }
        }

    }
}
