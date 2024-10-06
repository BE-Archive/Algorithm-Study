package Week_37.BOJ_13414;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_13414 {
    static class Node implements Comparable<Node>{
        String num;
        int order;

        Node(String num, int order) {
            this.num = num;
            this.order = order;
        }

        @Override
        public int compareTo(Node o) {
            return this.order - o.order;
        }
    }

    public static void main(String[] args) throws Exception {
        // 코드를 작성해주세요
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        Map<String, Integer> map = new HashMap<>();
        String tmp;
        for(int i=0; i<l; i++) {
            tmp = br.readLine();
            map.put(tmp, i);
        }

        int idx = 0;
        Node[] arr = new Node[map.size()];
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            arr[idx++] = new Node(entry.getKey(), entry.getValue());
        }
        Arrays.sort(arr);


        StringBuilder sb = new StringBuilder();
        for(int i=0; i<Math.min(k, arr.length); i++) {
            sb.append(arr[i].num).append("\n");
        }
        System.out.print(sb);
    }
}
