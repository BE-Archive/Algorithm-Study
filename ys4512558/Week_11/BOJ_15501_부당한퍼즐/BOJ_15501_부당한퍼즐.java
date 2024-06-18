import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        boolean isValid1 = true;
        boolean isValid2 = true;
        int start = arr2[0];
        int idx = 0;

        //시작점 찾기
        while (start != arr1[idx]) {
            idx++;
        }
        //그대로 확인해보기 (앞에서 뒤로)
        for (int i = 0; i < n; i++) {
            if (arr2[i] != arr1[(idx + i) % n]) {
                isValid1 = false;
                break;
            }
        }

        //뒤집고 확인해보기 (뒤에서 앞으로)
        for (int i = 0; i < n; i++) {
            if (arr2[i] != arr1[(idx - i + n) % n]) {
                isValid2 = false;
            }
        }

        sb.append(isValid1 | isValid2 ? "good puzzle" : "bad puzzle");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}