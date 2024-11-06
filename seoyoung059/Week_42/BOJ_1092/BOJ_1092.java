package seoyoung059.Week_42.BOJ_1092;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1092 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxWeight = Integer.MIN_VALUE;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            maxWeight = Math.max(maxWeight, arr[i]);
        }

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int maxBox = Integer.MIN_VALUE, tmp;
        for (int i = 0; i < m; i++) {
            tmp = Integer.parseInt(st.nextToken());
            maxBox = Math.max(maxBox, tmp);
            if(map.containsKey(tmp)) {
                map.put(tmp, map.get(tmp) + 1);
            } else map.put(tmp, 1);
        }

        int answer = 0; Integer key;
        if(maxBox <= maxWeight) {
            while(map.size() > 0) {
                for (int i = 0; i < n; i++) {
                    key = map.floorKey(arr[i]);
                    if(key==null) continue;
                    if (map.get(key) > 1) {
                        map.put(key, map.get(key) - 1);
                    } else map.remove(key);
                }
                answer++;
            }
        } else answer = -1;
        System.out.println(answer);

    }
}
