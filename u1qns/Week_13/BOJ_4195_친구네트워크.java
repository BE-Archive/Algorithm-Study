import java.io.*;
import java.util.*;

public class BOJ_4195_친구네트워크 {

    static int[] root;
    static Map<String, Integer> nameMapper;

    static int solve(final int x, final int y)
    {
        int r1 = find(x);
        int r2 = find(y);

        if(r1 == r2)
        {
            // 부모가 같으면 이미 친구 관계 root가 음수인 애가 사이즈를 가지고 있음
            if(root[r1] < 0)
                return root[r1]*-1;
            if(root[r2] < 0)
                return root[r2]*-1;
        }

        if(r1 > r2)
        {
            root[r1] += root[r2];
            root[r2] = r1;
            return root[r1]*-1;
        }
        else
        {
            root[r2] += root[r1];
            root[r1] = r2;
            return root[r2]*-1;
        }
    }

    static int find(int x)
    {
        if(root[x] < 0) return x;
        return root[x] = find(root[x]);
    }

    public static void main(String[] args) throws IOException {

        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String name1, name2;
        int N, T, idx1, idx2;

        T = Integer.parseInt(br.readLine());
        while(T-- > 0)
        {
            int idx = 1;

            nameMapper = new HashMap<String, Integer>();
            N = Integer.parseInt(br.readLine());
            int MAX = (N + 1) * 2;
            root = new int[MAX];
            Arrays.fill(root, -1);

            for(int i=0; i<N; ++i)
            {
                st = new StringTokenizer(br.readLine());
                name1 = st.nextToken();
                name2 = st.nextToken();

                idx1 = nameMapper.getOrDefault(name1, -1);
                if(idx1 == -1)
                {
                    nameMapper.put(name1, idx);
                    idx1 = idx++;
                }

                idx2 = nameMapper.getOrDefault(name2, -1);
                if(idx2 == -1)
                {
                    nameMapper.put(name2, idx);
                    idx2 = idx++;
                }
                sb.append(solve(idx1, idx2)).append("\n");
            }
        }
        System.out.print(sb);
    } // main
}
