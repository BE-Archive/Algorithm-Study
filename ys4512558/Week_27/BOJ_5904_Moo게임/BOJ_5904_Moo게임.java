import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        recur(N, 1, 3);
    }

    public static void recur(int n, int K, int len) {
        if (n <= 3) {
            System.out.println(n == 1 ? 'm' : 'o');
            return;
        }
        int nextLen = len * 2 + K + 3;

        //moo Len = s(k - 1) + K + 3(Moo + K) + s(k - 1)
        //현재 moo문자열이 목표 길이보다 작으면 moo문자열을 더 만들어야 한다.
        if (nextLen < n) {
            recur(n, K + 1, nextLen);
        }
        //목표 문자열이 가운데 (Moo + K)에 있을 때
        else if (n > len && n <= len + K + 3) {
            if (n - len == 1) {
                System.out.println('m');
            } else {
                System.out.println('o');
            }
            return;

        }
        //목표 문자열보다 현재 만들어진 moo문자열이 더 큰 경우
        else {
            // N = 8, [m o o] [m o o o] [m o o] 일때
            // 오른쪽에 존재함 목표 지점을 재조정
            // [m o o] [m o o o]이 부분을 없애고 다시 시작
            // [m o o]부터 다시 시작 (반쪼개서 오른쪽만 다시 본다)
            recur(n - (len + K + 3), 1, 3);
        }
    }
}