import java.io.*;
import java.util.*;

public class FriendNetwork {

    static int[] parents;
    static int[] level;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t<T; t++){
            int F = Integer.parseInt(br.readLine());

            makeSet(F * 2);

            HashMap<String, Integer> map = new HashMap<>(); // 이름, 인덱스
            StringTokenizer st;

            int index = 0;
            for(int i = 0; i<F; i++){
                st = new StringTokenizer(br.readLine());
                String f1 = st.nextToken();
                String f2 = st.nextToken();

                if(!map.containsKey(f1)){ // 없는 경우
                    map.put(f1, index++);
                }

                if(!map.containsKey(f2)){ // 없는 경우
                    map.put(f2, index++);
                }

                sb.append(union(map.get(f1), map.get(f2))+"\n");
            }

        }
        System.out.println(sb);


    }

    public static void makeSet(int num) {
        parents = new int[num];
        level = new int[num];

        for (int i = 0; i < num; i++) {
            parents[i] = i;
            level[i] = 1;
        }
    }

    public static int find(int a) {
        if (parents[a] == a)
            return a;

        return parents[a] = find(parents[a]); // 경로압축
    }

    public static int union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        if (rootA != rootB) {
            if (level[rootA] < level[rootB]) { // 항상 더 큰 집합 아래에 작은 집합을 합침
                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }
            parents[rootB] = rootA;
            level[rootA] += level[rootB];
        }
        return level[rootA]; 
    }

}
