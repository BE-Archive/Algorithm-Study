import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PowerSet {

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        number=new int[n];
        for (int i=0; i<n; i++) number[i]=i+1;

        visited=new int[n];

        powerSet(0);
        System.out.println(sb.toString());
    }

    public static int n;
    public static int[] number;
    public static int[] visited;
    public static StringBuilder sb;

    public static void powerSet(int idx){
        if (idx==n){
            for (int i=0; i<n; i++){
                if (visited[i]==1){
                    sb.append(number[i]+" ");
                }
            }
            sb.append("\n");
            return;
        }

        visited[idx]=1;
        powerSet(idx+1);
        visited[idx]=0;
        powerSet(idx+1);
    }
}
