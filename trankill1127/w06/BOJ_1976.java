import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1976 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        parent = new int[n];
        for (int i=0; i<n; i++) parent[i]=i;

        StringTokenizer st;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<n; j++){
                int in = Integer.parseInt(st.nextToken());
                if (in==1){
                    union(i,j);
                }
            }
        }

        boolean isPossible = true;
        st = new StringTokenizer(br.readLine(), " ");
        int first = Integer.parseInt(st.nextToken())-1;
        for (int i=1; i<m; i++){
            int notFirst = Integer.parseInt(st.nextToken())-1;
            if (find(first)!=find(notFirst)) {
                isPossible=false;
                break;
            }
        }

        if (isPossible) System.out.println("YES");
        else System.out.println("NO");
    }

    public static int[] parent;

    public static int find(int x){
        if (parent[x]==x) return x;
        else return parent[x]=find(parent[x]);
    }

    public static void union(int x1, int x2){
        int p1 = find(x1);
        int p2 = find(x2);

        if (p1==p2) return;
        else if (p1<p2) parent[p2]=p1;
        else parent[p1]=p2;
    }

}
