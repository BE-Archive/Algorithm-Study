import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int[] midtermGrades = new int[N+1]; // 인덱스 : 기말고사 성적, 값 : 중간고사 성적
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            midtermGrades[Integer.parseInt(st.nextToken())] = i;
        }
        for (int j = 1; j <= N; j++) {
            sb.append( j- midtermGrades[j]).append("\n");
        }

        System.out.println(sb);
    }
}