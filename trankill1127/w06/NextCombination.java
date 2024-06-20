import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class NextCombination {

    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        int[] number=new int[n];
        for (int i=0; i<n; i++) number[i]=i+1;

        int m = Integer.parseInt(br.readLine());
        visited=new int[n];
        for (int i=m; i<n; i++) visited[i]=1;

        do {
            for (int i=0; i<n; i++){
                if (visited[i]==0){
                    sb.append(number[i]+" ");
                }
            }
            sb.append("\n");
        } while(nextCombination());

        System.out.println(sb.toString());
    }

    public static int n;
    public static int[] visited;

    public static boolean nextCombination(){
        int i=n-1;
        while (visited[i-1]>=visited[i]) {
            i--;
            if (i == 0) return false;
        }

        int j=n-1;
        while (visited[i-1]>=visited[j]) j--;

        int tmp=visited[i-1];
        visited[i-1]=visited[j];
        visited[j]=tmp;

        Arrays.sort(visited, i, n);
        return true;
    }

}
