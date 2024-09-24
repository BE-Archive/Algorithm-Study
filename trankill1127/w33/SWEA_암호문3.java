import java.util.*;
import java.io.*;

public class SWEA_암호문3 {

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        LinkedList<Integer> list = new LinkedList<>();
        int T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            list.clear();

            int n = Integer.parseInt(br.readLine().trim());
            st = new StringTokenizer(br.readLine().trim());
            for (int i = 0; i < n; i++) list.add(Integer.parseInt(st.nextToken()));

            int operations = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine().trim());
            while (operations-- > 0) {
                String detailOp = st.nextToken();
                if (detailOp.equals("I")) {
                    int idx = Integer.parseInt(st.nextToken());
                    int addCnt = Integer.parseInt(st.nextToken());
                    for (int j = 0; j < addCnt; j++) {
                        list.add(idx++, Integer.parseInt(st.nextToken()));
                    }
                    //System.out.println("======="+detailOp+" "+idx+" "+addCnt);
                } else if (detailOp.equals("A")) {
                    int addCnt = Integer.parseInt(st.nextToken());
                    for (int j = 0; j < addCnt; j++) {
                        list.addLast(Integer.parseInt(st.nextToken()));
                    }
                    //System.out.println("======="+detailOp+" "+addCnt);
                } else if (detailOp.equals("D")) {
                    int idx = Integer.parseInt(st.nextToken());
                    int delCnt = Integer.parseInt(st.nextToken());
                    for (int j = 0; j < delCnt; j++) {
                        list.remove(idx);
                    }
                    //System.out.println("======="+detailOp+" "+idx+" "+delCnt);
                }
            }

            sb.append("#").append(test_case);
            for (int i = 0; i < 10 && i < list.size(); i++) {
                sb.append(" ").append(list.get(i));
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
