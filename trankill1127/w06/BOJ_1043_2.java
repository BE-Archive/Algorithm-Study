import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1043_2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken()); //사람의 수 (1~n)
        int m = Integer.parseInt(st.nextToken()); //파티 수

        parent = new int[n+1];
        for (int i=1; i<=n; i++) parent[i]=i;

        st = new StringTokenizer(br.readLine(), " ");
        int knowCnt = Integer.parseInt(st.nextToken());
        if (knowCnt>0){
            for (int i=0; i<knowCnt; i++)
                union(0, Integer.parseInt(st.nextToken()));
        }

        ArrayList<ArrayList<Integer>> parties = new ArrayList<ArrayList<Integer>>();
        for (int i=0; i<m; i++){
            ArrayList<Integer> party = new ArrayList<>();
            st = new StringTokenizer(br.readLine(), " ");
            int attendCnt = Integer.parseInt(st.nextToken());
            int first = Integer.parseInt(st.nextToken());
            party.add(first);
            for (int j=1; j<attendCnt; j++){
                int notFirst = Integer.parseInt(st.nextToken());
                union(first, notFirst);
                party.add(notFirst);
            }
            parties.add(party);
        }

        //System.out.println(Arrays.toString(parent));

        boolean canTalk=true;
        int cnt=0;
        for (int i=0; i<m; i++) {

            for (int p : parties.get(i)){
                if (find(0)==find(p)) {
                    canTalk=false;
                    break;
                }
            }

            if (canTalk) cnt++;
            canTalk=true;
        }

        System.out.println(cnt);
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

        if (p1<p2) parent[p2]=p1;
        else parent[p1]=p2;
    }

}
