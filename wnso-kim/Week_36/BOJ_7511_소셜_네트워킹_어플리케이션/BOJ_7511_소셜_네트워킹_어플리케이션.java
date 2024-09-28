import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_7511_소셜_네트워킹_어플리케이션 {

    static int N,K,M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());
        for(int t = 0; t<testCase; t++){
            N = Integer.parseInt(br.readLine());
            K = Integer.parseInt(br.readLine());

            UnionFind uf = new UnionFind(N);

            for(int k=0; k<K; k++){
                StringTokenizer stk = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(stk.nextToken());
                int b = Integer.parseInt(stk.nextToken());

                uf.union(a,b);
            }

            M = Integer.parseInt(br.readLine());

            answer.append("Scenario ").append(t+1).append(":").append("\n");
            for(int m=0; m<M; m++){
                StringTokenizer stk = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(stk.nextToken());
                int v = Integer.parseInt(stk.nextToken());

                answer.append(uf.isEqual(u,v)).append("\n");
            }

            answer.append("\n");
        }

        System.out.println(answer);
    }

    static class UnionFind{
        int size;
        int[] root;

        UnionFind(int size){
            this.size = size;
            this.root = new int[size];

            for(int i=0; i<size; i++){
                root[i] = -1;
            }
        }


        int find(int i1){
            if(root[i1]<0) return i1;
            return root[i1] = find(root[i1]);
        }

        int isEqual(int i1, int i2){
            int r1 = find(i1);
            int r2 = find(i2);

            return r1==r2? 1: 0;
        }

        void union(int i1, int i2){
            int r1 = find(i1);
            int r2 = find(i2);

            if(r1==r2) return;

            if(root[r1] <= root[r2]){
                root[r1] += root[r2];
                root[r2] = r1;
            } else {
                root[r2] += root[r1];
                root[r1] = r2;
            }
        }
    }

}
