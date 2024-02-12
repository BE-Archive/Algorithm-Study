import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int[] arr;
    static int N, K, max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        int[] numbers = new int[100001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int left = 0;
        for (int i = 0; i < N; i++) {
            if(numbers[arr[i]] != K){
                numbers[arr[i]]++;
                max = Math.max(max, i - left + 1);
                continue;
            }
            for (int j = left; j < i; j++) {
                if (arr[i] == arr[j]) {
                    left = j + 1; //다음 것 ~ i까지가 부분 수열
                    max = Math.max(max, i - left + 1);
                    break;
                }
                numbers[arr[j]]--;
            }
        }

        sb.append(max);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}