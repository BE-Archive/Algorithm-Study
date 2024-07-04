import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1661 {
    static int N, P;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        List<Integer>[] disCounts = new ArrayList[3];
        for (int i = 0; i < 3; i++) {
            disCounts[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int price = Integer.parseInt(st.nextToken());
            int discount = Integer.parseInt(st.nextToken());
            if(discount == 1){
                disCounts[0].add(price);
            } else if(discount == 2){
                disCounts[1].add(price);
            } else {
                disCounts[2].add(price);
            }
        }
        for (int i = 0; i < 3; i++) {
            Collections.sort(disCounts[i]);
        }

        int[][] prefixSum = new int[3][];
        for (int i = 0; i < 3; i++) {
            prefixSum[i] = new int[disCounts[i].size() + 1];
            for (int j = 0; j < disCounts[i].size(); j++) {
                prefixSum[i][j + 1] = prefixSum[i][j] + disCounts[i].get(j);
            }
        }

        double res = P;
        for (int i = 0; i <= disCounts[0].size(); i++) {
            for (int j = 0; j <= disCounts[1].size(); j++) {
                for (int k = 0; k <= disCounts[2].size(); k++) {
                    int sum = prefixSum[0][i] + prefixSum[1][j] + prefixSum[2][k];
                    res = Math.min(res, Math.pow(0.99, i) * Math.pow(0.98, j) * Math.pow(0.97, k) * P + sum);
                }
            }
        }
        System.out.println(res);
    }
}