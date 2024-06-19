import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_30805 {

    public static int n;
    public static int m;
    public static int[] A;
    public static int[] B;
    public static List<List<Integer>> subsets = new ArrayList<>();
    public static List<Integer> same;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());
        B = new int[m];
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        same = findLCS();
        allSubset(new ArrayList<>(), 0);
        Collections.sort(subsets, new LexicographicalComparator());
        List<Integer> answer = subsets.get(subsets.size() - 1);
        System.out.println(answer.size());
        for (int i = 0; i < answer.size(); i++) {
            System.out.print(answer.get(i) + " ");
        }
    }

    public static void allSubset(List<Integer> l, int s) {
        if (s == same.size()) {
            subsets.add(new ArrayList<>(l)); // 현재 리스트의 복사본을 추가
            return;
        }

        // 현재 원소를 포함하지 않는 경우
        allSubset(l, s + 1);

        // 현재 원소를 포함하는 경우
        l.add(same.get(s));
        allSubset(l, s + 1);
        l.remove(l.size() - 1); // backtrack
    }

    public static List<Integer> findLCS() {
        int[][] dp = new int[n + 1][m + 1];
        List<Integer>[][] paths = new List[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                paths[i][j] = new ArrayList<>();
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A[i - 1] == B[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    paths[i][j] = new ArrayList<>(paths[i - 1][j - 1]);
                    paths[i][j].add(A[i - 1]);
                } else {
                    if (dp[i - 1][j] > dp[i][j - 1]) {
                        dp[i][j] = dp[i - 1][j];
                        paths[i][j] = new ArrayList<>(paths[i - 1][j]);
                    } else if (dp[i - 1][j] < dp[i][j - 1]) {
                        dp[i][j] = dp[i][j - 1];
                        paths[i][j] = new ArrayList<>(paths[i][j - 1]);
                    } else {
                        dp[i][j] = dp[i - 1][j]; // Same length, we need to choose the lexicographically larger one
                        paths[i][j] = lexicographicallyMax(new ArrayList<>(paths[i - 1][j]), new ArrayList<>(paths[i][j - 1]));
                    }
                }
            }
        }

        return paths[n][m];
    }

    public static List<Integer> lexicographicallyMax(List<Integer> a, List<Integer> b) {
        int len = Math.min(a.size(), b.size());
        for (int i = 0; i < len; i++) {
            int cmp = Integer.compare(a.get(i), b.get(i));
            if (cmp != 0) {
                return cmp > 0 ? a : b;
            }
        }
        return a.size() >= b.size() ? a : b;
    }

    static class LexicographicalComparator implements Comparator<List<Integer>> {
        @Override
        public int compare(List<Integer> a, List<Integer> b) {
            int len = Math.min(a.size(), b.size());
            for (int i = 0; i < len; i++) {
                int cmp = Integer.compare(a.get(i), b.get(i));
                if (cmp != 0) {
                    return cmp;
                }
            }
            return Integer.compare(a.size(), b.size());
        }
    }
}
