import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_21606_아침산책 {
    static int N;
    static int[] A;
    static long answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        A = new int[N+1];

        String input = br.readLine();
        for(int i=1; i<=N; i++){
            A[i] = input.charAt(i-1)-'0';
        }

        UF uf = new UF(N+1);

        for(int i=1; i<N; i++){
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(stk .nextToken());
            int b = Integer.parseInt(stk .nextToken());
                
            // 유니온 파인드
            uf.union(a, b);
        }

        uf.sum();

        System.out.println(answer);
    }

    static class UF{
        int[] root;

        UF(int size){
            root = new int[size];
        }

        int find(int index){
            if(root[index] <= 0) return index;
            return root[index] = find(root[index]);
        }

        void union(int a, int b){
            int r1 = find(a);
            int r2 = find(b);
            
            if(A[a] == 1 && A[b] == 1){ // 실내-실내
                answer+=2;
            }else if(A[a] == 1 && A[b] == 0){  // 실내-야외
                root[r2]--;
            }else if(A[a] == 0 && A[b] == 1){  // 야외-실내
                root[r1]--;
            }else if(r1 < r2){              // 야외-야외
                if(root[r2] <0) root[r1] += root[r2]; 
                root[r2] = r1;
            }else{
                if(root[r1] <0) root[r2] += root[r1];
                root[r1] = r2;
            }
        }

        void sum(){
            for(int size: root){
                if(size >=0) continue;

                answer += size * (size + 1l);
            }
        }
    }

    
}