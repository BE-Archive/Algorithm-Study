import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2531 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] sushis = new int[N];
        for (int i = 0; i < N; i++) {
            sushis[i] = Integer.parseInt(br.readLine());
        }

        Map<Integer, Integer> counts = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < k; i++) {
            if(set.add(sushis[i])) {
                counts.put(sushis[i], 1);
                continue;
            }
            Integer count = counts.get(sushis[i]);
            counts.put(sushis[i], count + 1);
        }

        //슬라이딩 윈도우
        int size = set.size();
        int max = set.contains(c) ? size : size + 1;
        int left = 0;
        for (int right = k; right < N + k - 1; right++) { //순환 구조의 마지막 -> 마지막 원소 1개 + 시작 ~ d - 1개
            if(counts.get(sushis[left % N]) == 1) {
                set.remove(sushis[left % N]);
            }
            counts.put(sushis[left % N], counts.get(sushis[left % N]) - 1);

            if(counts.getOrDefault(sushis[right % N], 0) == 0){
                set.add(sushis[right % N]);
            };
            counts.put(sushis[right % N], counts.getOrDefault(sushis[right % N], 0) + 1);

            size = set.contains(c) ? set.size() : set.size() + 1;
            max = Math.max(max, size);
            left++;
        }
        System.out.println(max);
    }
}