# 소스코드

```Java
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
        //다음 moo문자열이 목표 길이보다 작으면 moo문자열을 더 만들어야 한다.
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
        //목표 문자열보다 다음 만들어질 moo문자열이 더 큰 경우
        else {
            // N = 8, [m o o] [m o o o] [m o o] 일때
            // 오른쪽에 존재함 목표 지점을 재조정
            // [m o o] [m o o o]이 부분을 없애고 다시 시작
            // [m o o]부터 다시 시작 (반쪼개서 오른쪽만 다시 본다)
            recur(n - (len + K + 3), 1, 3);
        }
    }
}
```

# 소요시간

1시간 30분

# 알고리즘

> 재귀

# 풀이

# BOJ 5904 Moo 게임

1. 잘 모르겠어서 풀이를 보았다 ㅠㅠ
2. Moo 수열은 s(k - 1) + 'm o o' + ('o' * k) + s(k - 1)로 구성된다.
3. 따라서, N번째 문자가 무엇인지 알기 위해 이를 나누어서 확인해보아야 한다.
4. 또한, Moo 수열자체를 만들면 안되고, 길이를 사용해서 해결해야 한다.
5. 왼쪽에 있는 경우     
   Moo문자열이 찾으려는 위치보다 작은 경우이므로 Moo문자열을 더 만들어야 한다.
6. 가운데에 있는 경우   
   Moo문자열의 가운데 m o o + (o * K) 이므로 맨 처음만 m이다. 이를 확인하고 출력한다.
7. 오른쪽에 있는 경우       
   Moo문자열의 왼쪽, 가운데를 제외한 오른쪽만을 이용해서 다시 Moo수열을 만든다.     
   대칭이기 때문에 반절을 제외하고 왼쪽부터 다시 확인해본다고 생각하면 됨!      
   왼 : [m o o], 중앙 : [m o o o], 오른쪽 [m o o] 이면 오른쪽은 왼쪽과 같음     
8. 따라서, 오른쪽일 때 n을 왼쪽 + 가운데 길이만큼 빼준 후 다시 수행해야 한다.
9. 또한, n <= 3이면, [m, o, o]이므로 n == 1이면 m아니면 o이다.

---
