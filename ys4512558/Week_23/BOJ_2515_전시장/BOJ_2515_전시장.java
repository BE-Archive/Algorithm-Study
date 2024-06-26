import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2179 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        Map<Integer, Integer> map = new HashMap<>();
        TreeSet<Picture> treeSet = new TreeSet<>();
        TreeSet<Picture> origin = new TreeSet<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int cost = map.getOrDefault(h, -1);
            if (cost == -1) {
                map.put(h, c);
                continue;
            }
            map.put(h, Math.max(c, cost));
        }
        for (Integer h : map.keySet()) {
            int c = map.get(h);
            Picture picture = new Picture(h, c);
            treeSet.add(picture);
            origin.add(picture);
        }

        int[] dp = new int[treeSet.last().h + 1];
        int max = 0;
        int size = treeSet.size();
        for (int i = 0; i < size; i++) {
            Picture picture = treeSet.pollFirst();

            int target = picture.h - S;
            int preH = 0;
            while (true) {
                Picture floor = origin.floor(new Picture(target, 0));
                if(floor == null || preH - floor.h >= S) break;
                target = floor.h - 1;
                dp[picture.h] = Math.max(dp[picture.h], dp[floor.h]);
            }
            dp[picture.h] += picture.c;
            max = Math.max(max, dp[picture.h]);
        }
        System.out.println(max);
    }
}

class Picture implements Comparable<Picture> {

    int h, c;

    public Picture(int h, int c) {
        this.h = h;
        this.c = c;
    }

    @Override
    public int compareTo(Picture o) {
        return Integer.compare(this.h, o.h);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Picture picture = (Picture) o;
        return h == picture.h && c == picture.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(h, c);
    }
}