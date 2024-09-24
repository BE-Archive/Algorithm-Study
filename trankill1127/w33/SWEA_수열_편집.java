import java.util.*;
import java.io.*;

public class SWEA_수열_편집 {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        LinkedList<Integer> list = new LinkedList<>();
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            list.clear();

            st = new StringTokenizer(br.readLine().trim());
            int n = Integer.parseInt(st.nextToken()); //수열 길이
            int m = Integer.parseInt(st.nextToken()); //추가 횟수
            int l = Integer.parseInt(st.nextToken()); //출력 인덱스 번호

            st = new StringTokenizer(br.readLine().trim());
            for (int i = 0; i < n; i++) list.add(Integer.parseInt(st.nextToken()));

            while (m-- > 0) {
                st = new StringTokenizer(br.readLine().trim());
                String detailOp = st.nextToken();
                if (detailOp.equals("I")) {
                    int idx = Integer.parseInt(st.nextToken());
                    int num = Integer.parseInt(st.nextToken());
                    list.add(idx, num);
                } else if (detailOp.equals("C")) {
                    int idx = Integer.parseInt(st.nextToken());
                    int num = Integer.parseInt(st.nextToken());
                    list.set(idx,num);
                } else if (detailOp.equals("D")) {
                    int idx = Integer.parseInt(st.nextToken());
                    list.remove(idx);
                }
            }

            sb.append("#").append(test_case).append(" ");
            if (list.size()<l) sb.append("-1");
            else sb.append(list.get(l));
            sb.append("\n");
        }
        System.out.print(sb);
    }

}
