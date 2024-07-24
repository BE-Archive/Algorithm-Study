## 소스코드
[여기 클릭해 보세요!](https://github.com/BE-Archive/Algorithm-Study/blob/main/wnso-kim/Week_27/BOJ_5904_Moo_게임/BOJ_5904_Moo_게임.java)

## 소요시간
`40분`

## 알고리즘
`그리디`, `반복 찾기?`

## 풀이
0, 1, 2, ... K의 증가에 따른 `S(K)`의 length를 기록한다.    
`N <= S(K).length`를 만족 할 때 까지 기록한다.

![image](https://github.com/user-attachments/assets/03cb8089-eb75-4cee-b888-ea40c59eeb24)


역 부터 시작해 패턴을 반복한다.
- N이 **S(K-1).length** 보다 크고, **S(K-1).length+(K+3)** 보다 작은 경우 `(pre < N < pre+(k+3))`   
중간에 위치한 `mo...o` 중 하나이다. => 바로 출력
![image](https://github.com/user-attachments/assets/6b86634c-4464-4034-bf06-8a6720689b09)
   

- N이 **S(K-1).length+(K+3)** 보다 큰 경우 `(pre+(k+3) < N)`   
오른쪽에 위치한 `S(K-1)` 번째 중 하나이다.   
오른쪽과 왼쪽 `S(K-1)` 은 같은 패턴이므로, `N -= (k+3) + pre`을 이용해 N을 교체한다.  
![image](https://github.com/user-attachments/assets/4e5e4271-82df-4f6e-a16b-cc8dd3541edf)

- N이 **S(K-1).length** 보다 작은 경우 `(pre < N)`   
왼쪽에 위치한 `S(K-1)` 번째 중 하나이다.  
![image](https://github.com/user-attachments/assets/d3a6883b-64b2-40e5-aa25-7a4547e51010)


- 두세번 째 패턴인 경우 K-1 한 뒤 다시 확인한다.( K != 0)까지