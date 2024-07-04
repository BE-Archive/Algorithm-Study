# IPv6

주차: 2월 1주차
난이도: 골드4
링크: https://www.acmicpc.net/problem/3107
소요 시간: 1시간

# 문제 ❓

![Untitled](https://file.notion.so/f/f/998f5491-4f7c-4eaf-8f3f-20d7267150f3/25747408-685e-4d18-b5af-a330d38c6207/Untitled.png?id=d2af9b6e-fabd-4eb5-8dea-b8b5718647f1&table=block&spaceId=998f5491-4f7c-4eaf-8f3f-20d7267150f3&expirationTimestamp=1707091200000&signature=LHKcTkk7IJs53z0KUYCas7GHUS-V31M7FlMf2fQ5dwI&downloadName=Untitled.png)

# 입출력 ⌨️

![Untitled](https://file.notion.so/f/f/998f5491-4f7c-4eaf-8f3f-20d7267150f3/c6895ad4-853f-4823-b97c-340dce111755/Untitled.png?id=8083247e-6dcc-47f0-ba3c-e5f0bff91ba9&table=block&spaceId=998f5491-4f7c-4eaf-8f3f-20d7267150f3&expirationTimestamp=1707091200000&signature=C0Fspee_Nfknz_xe6Lp9jpyGxfLKpZyTboR2xmdFOXI&downloadName=Untitled.png)

# 풀이 📚

## 사고 과정 🤔

일단 길이가 정해진 문자열이 입력으로 들어오므로, **웬만해서는 시간초과나 메모리초과는 나지 않을 것**

1번 규칙과 2번 규칙으로 나뉘는데 

- 1번 규칙 : 0의 전체 또는 일부를 생략할 수 있다.

특정 그룹의 0의 일부를 생략할 시 

**`1111:0011:1111 → 1111:11:1111`**

특정 그룹의 0의 전부를 생략할 시

**`1111:0000:1111 → 1111::1111`**

- 2번 규칙 : 0으로만 이루어진 그룹이 연속적으로 나타날 경우 해당 연속 그룹을 원하는 개수만큼 골라 **“::”** 으로 생략할 수 있다.

`**::1 -> 0000:0000:0000:0000:0000:0000:0000:0001**`

이때 2번 규칙은 한번만 사용할 수 있다.

만약 1번 규칙만 사용했다면 → : 의 개수는 7개로 고정

2번 규칙을 혼용했다면 → **:의 개수가 바뀐다.**

따라서 문자열의 ‘:’ 개수에 따라 **2번 규칙이 몇개의 그룹을 생략하는데 쓰였는지 확인**하여 **알맞은 위치에 0000 을 채워주고**, 1번 규칙이 쓰인 곳에는 **4자리를 맞추어 0을 채워**주면 된다.

## 풀이 요약 👍

**‘0’ 이 담긴 char[8][4] 배열을 선언한다.**

**char[7][3] 위치에서부터 채워나가기** 시작한다.

문자열을 **뒤에서부터 검사**하여 

1. 문자가 나온다면 그대로 채운다
2. ‘:’ 이 나왔다면 앞으로 남은 ‘;’ 개수를 확인한다.
    1. 0개 남았다면 → 현재 위치부터 char[1][0] 까지 다 ‘0’으로 채우고 다음으로 넘어간다.
    2. 만약 바로 다음에도 ‘:’ 이 나온다면 2번이 규칙이 쓰였을 수 있으므로 도대체 몇개의 그룹이 생략되었는지 확인하여 해당 개수만큼 채운 후 넘어간다. 
    3. 그게 아니라면 다음 검사 위치로 넘어간다
        1. 특정 그룹의 다음 위치(이전 인덱스)로 넘어간다.
        2. 이때 현재 검색 인덱스가 특정 그룹의 0번 째 인덱스였다면 이전 그룹의 마지막 인덱스로 넘어간다.

## 구현 ✏️

```java
static void init() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                ip[i][j] = '0';
            }
        }
    }

    static void setColonCount() {
        for (int i = 0; i < from.length(); i++) {
            if (from.charAt(i) == ':') colonCount++;
        }
    }
```

초기값 설정하고, 전체 문자열의 콜론 개수 세주기

# 전체 코드 🖥️

```java
static void solution() {
        int groupIndex = 7;
        int partIndex = 3;
        for (int i = from.length() - 1; i >= 0; i--) {
            if (from.charAt(i) == ':') {
                colonCount--;
                if (colonCount == 0) {
                    groupIndex = 0;
                    partIndex = 3;
                    continue;
                }
                if (i != 0 && from.charAt(i - 1) == ':') {
                    groupIndex = colonCount - 1;
                    partIndex = 3;
                    continue;
                }
                if (partIndex != 3) groupIndex--;
                partIndex = 3;
                continue;
            }
            ip[groupIndex][partIndex] = from.charAt(i);
            partIndex = partIndex == 0 ? 3 : partIndex - 1;
            groupIndex = partIndex == 3 ? groupIndex - 1 : groupIndex;
        }
    }
```

문자열을 뒤에서부터 탐색하여 복원

```java
static void printAnswer() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                sb.append(ip[i][j]);
            }
            if (i != 7) sb.append(":");
        }
        System.out.println(sb);
    }
```

답을 출력할 때 중간에 : 을 삽입해가며 출력

## 내 코드 😏

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IPv6 {
    //https://www.acmicpc.net/problem/3107
    //IPv6
    static char[][] ip = new char[8][4];
    static String from;
    static int colonCount;

    static void init() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                ip[i][j] = '0';
            }
        }
    }

    static void setColonCount() {
        for (int i = 0; i < from.length(); i++) {
            if (from.charAt(i) == ':') colonCount++;
        }
    }

    static void solution() {
        int groupIndex = 7;
        int partIndex = 3;
        for (int i = from.length() - 1; i >= 0; i--) {
            if (from.charAt(i) == ':') {
                colonCount--;
                if (colonCount == 0) {
                    groupIndex = 0;
                    partIndex = 3;
                    continue;
                }
                if (i != 0 && from.charAt(i - 1) == ':') {
                    groupIndex = colonCount - 1;
                    partIndex = 3;
                    continue;
                }
                if (partIndex != 3) groupIndex--;
                partIndex = 3;
                continue;
            }
            ip[groupIndex][partIndex] = from.charAt(i);
            partIndex = partIndex == 0 ? 3 : partIndex - 1;
            groupIndex = partIndex == 3 ? groupIndex - 1 : groupIndex;
        }
    }

    static void printAnswer() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                sb.append(ip[i][j]);
            }
            if (i != 7) sb.append(":");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        from = bf.readLine();
        init();
        setColonCount();
        solution();
        printAnswer();
    }
}
```

## 보완할 수 있는 것들 💡

- 일단 솔루션 함수가 너무 더럽다;; 리팩토링 시급
- 이번에도 남의 코드를 훔쳐봐야겠다