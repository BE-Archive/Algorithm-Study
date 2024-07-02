## 소스코드
[여기 클릭해 보세요!](https://github.com/BE-Archive/Algorithm-Study/blob/main/wnso-kim/Week_21/PGS_산모양타일링/PGS_산모양타일링.java)

## 소요시간
`1시간 10분`

## 알고리즘
`DP`

## 발상
삼각형 3개가 모인 산 모양 타일링을 채우는 것이 아닌,    
오른쪽과 같은 모양의 타일링을 채워나간다 생각하면 편하다.

![image](https://github.com/BE-Archive/Algorithm-Study/assets/109727039/740be52e-715f-4057-9517-72227922cae8)   
   

타일을 채울 수 있는 경우는 5가지이다.   

![image](https://github.com/BE-Archive/Algorithm-Study/assets/109727039/226ecd61-6e93-4f40-b2ab-05dbb87ce4a9)

## 풀이

#### [1] 첫 번째 타일 채우기
> 5개 모든 타일 뒤에 올 수 있다.

![image](https://github.com/BE-Archive/Algorithm-Study/assets/109727039/f5f44724-910d-458a-a1e8-ef3386769700)

#### [2] 두 번째 타일 채우기
> 3개의 타일 뒤에 올 수 있다.

![image](https://github.com/BE-Archive/Algorithm-Study/assets/109727039/a66103ec-23ad-420d-aeac-96e252ce5c98)

#### [3] 세 번째 타일 채우기
> 3개의 타일 뒤에 올 수 있다.   
**tops[i] 가 1 이어야 한다.** 즉, `꼭대기 타일`이 있어야한다.

![image](https://github.com/BE-Archive/Algorithm-Study/assets/109727039/6158e36e-aec7-4f2d-84b3-93ae6fece7d3)

#### [4] 네 번째 타일 채우기
>5개 모든 타일 뒤에 올 수 있다.   
밑변이 `2n+1`개를 만족해야 하므로 `마지막 타일`인 경우 올 수 없다.

![image](https://github.com/BE-Archive/Algorithm-Study/assets/109727039/777d94dc-0bdb-45a5-8b3d-693624218005)

#### [5] 다섯번 째 타일 채우기
> 3개의 타일 뒤에 올 수 있다.   
> **tops[i] 가 1 이어야 한다.** 즉, `꼭대기 타일`이 있어야한다.   
> 밑변이 `2n+1`개를 만족해야 하므로 `마지막 타일`인 경우 올 수 없다.

![image](https://github.com/BE-Archive/Algorithm-Study/assets/109727039/725c9911-6f48-46fa-aa08-7accfb342dbb)


#### 점화식
```java
dp[0][0] = dp[0][2] = 1;

for (int i = 1; i <= n; i++) {
    dp[i][0] = dp[i-1][0] + dp[i-1][1] + dp[i-1][2] + dp[i-1][3] + dp[i-1][4];
    dp[i][1] = dp[i-1][0] + dp[i-1][1] + dp[i-1][3];
    
    if (i != n) 
        dp[i][2] = dp[i-1][0] + dp[i-1][1] + dp[i-1][2] + dp[i-1][3] + dp[i-1][4];
    
    
    if (tops[i-1] == 1) {
        dp[i][3] = dp[i-1][0] + dp[i-1][1] + dp[i-1][3];
        
        if (i != n) {
            dp[i][4] = dp[i-1][0] + dp[i-1][1] + dp[i-1][3];
        }
    }
}
```