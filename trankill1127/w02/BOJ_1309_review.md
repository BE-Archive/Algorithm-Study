## 소요시간
25분

</br>

## 1차 시도

```java
System.out.println(cnt%9901);
```

100000 입력 시 음수가 출력되는 것을 확인했다. getCaseCnt에서 cases에 값을 집어넣는 과정에서 오버플로우가 발생했음을 파악했다.

</br>

## 2차 시도

```java
			cases[i][0] = (cases[i-1][1]+cases[i-1][2])%9901;
			cases[i][1] = (cases[i-1][0]+cases[i-1][2])%9901;
			cases[i][2] = (cases[i-1][0]+cases[i-1][1]+cases[i-1][2])%9901;
```

애초에 오버플로우가 발생하지 않게 해주겠다는 의도로 cases에 값을 집어넣는 과정에서 나머지 연산 처리를 해줬다. 하지만 여전히 틀렸습니다!로 결과가 나왔다.

</br>

## 3차 시도

```java
public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); //100_000
        int[][] cases = new int[n][3];
        cases[0][0] = 1;//왼쪽 칸에 넣음
        cases[0][1] = 1;//오른쪽 칸에 넣음
        cases[0][2] = 1;//어느 칸에도 넣지 않음
        for (int i = 1; i < n; i++) {
            cases[i][0] = (cases[i - 1][1] + cases[i - 1][2]) % 9901;
            cases[i][1] = (cases[i - 1][0] + cases[i - 1][2]) % 9901;
            cases[i][2] = (cases[i - 1][0] + cases[i - 1][1] + cases[i - 1][2]) % 9901;
        }
        int cnt = cases[n - 1][0] + cases[n - 1][1] + cases[n - 1][2];
        System.out.println(cnt % 9901);
    }
```

계산 중간에 나머지 처리를 해줬다고 끝이 아니다! 결과를 낼 때 한번 더 나머지 처리를 해줘야 한다.