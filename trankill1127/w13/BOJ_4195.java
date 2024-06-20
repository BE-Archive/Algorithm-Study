import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_4195 {

    public static HashMap<String, Integer> names = new HashMap<>();
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t=Integer.parseInt(br.readLine());
        for (int tc=1; tc<=t; tc++){

            int f=Integer.parseInt(br.readLine());
            parent=new int[f*2+1];
            Arrays.fill(parent, -1);

            names.clear();
            int nameIdx=1;

            StringTokenizer st;
            for (int i=0; i<f; i++){
                st = new StringTokenizer(br.readLine().trim());
                String left = st.nextToken();
                String right = st.nextToken();

                if (!names.containsKey(left)){
                    names.put(left, nameIdx);
                    nameIdx++;
                }
                if (!names.containsKey(right)){
                    names.put(right, nameIdx);
                    nameIdx++;
                }
                int leftIdx=names.get(left);
                int rightIdx=names.get(right);

                int std = union(leftIdx, rightIdx);
                sb.append(-parent[std]).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    public static int find(int x){
        if (parent[x]<0) return x;
        return parent[x] = find(parent[x]);
    }

    public static int union(int idx1, int idx2){
        int p1=find(idx1);
        int p2=find(idx2);

        if (p1==p2) return p1;
        else if (p1<p2){
            parent[p1]+=parent[p2];
            parent[p2]=p1;
            return p1;
        }
        else {
            parent[p2]+=parent[p1];
            parent[p1]=p2;
            return p2;
        }
    }

}