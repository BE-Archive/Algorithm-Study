import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SWEA_7465 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        StringBuilder sb =new StringBuilder();
        for (int tc=1; tc<=t; tc++){
            st = new StringTokenizer(br.readLine(), " ");

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            set(n);
            for (int i=0; i<m; i++){
                st = new StringTokenizer(br.readLine(), " ");
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                union(v1,v2);
            }

            Map<Integer, Boolean> map= new HashMap<>();
            for (int i=1; i<=n; i++){
                int p = find(parent[i]);
                if (map.containsKey(p)) continue;
                map.put(p,true);
            }

            sb.append("#").append(tc).append(" ").append(map.size()).append("\n");

        }

        System.out.println(sb.toString());
    }

    public static int[] parent;

    public static void set(int size){
        parent = new int[size+1];
        for (int i=1; i<=size; i++) parent[i]=i;
    }
    public static void union(int v1, int v2){
        int h1=find(v1);
        int h2=find(v2);
        if (h1==h2) return;
        else if (h1<h2) parent[h2]=h1;
        else parent[h1]=h2;
    }
    public static int find(int x){
        if (parent[x]==x) return x;
        return parent[x]=find(parent[x]);
    }

}

/*
N(1~N)
서로 아는 관계+건너 건너 아는 관계=아는 관계=무리
몇 개의 무리가 존재?

N : 1~100
관계 중복은 없다.

UnionFind 가자!
 */