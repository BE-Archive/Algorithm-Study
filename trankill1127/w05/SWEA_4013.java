import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_4013 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        gears = new int[5][8];
        for (int i = 1; i <= 4; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                gears[i][j] = tmp[j]-'0';
            }
        }

        int k = Integer.parseInt(br.readLine());

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int target = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            turnDirection = new int[5];
            Arrays.fill(turnDirection, 0);
            checkDirection(target, direction);
            gearTurn();
        }

        int score = 0;
        for (int i = 1; i <= 4; i++) {
            if (gears[i][0] == 1) {
                if (i == 1) score += 1;
                else if (i == 2) score += 2;
                else if (i == 3) score += 4;
                else if (i == 4) score += 8;
            }
        }

        System.out.println(score);
    }

    public static int[][] gears;
    public static int[] turnDirection;

    public static void checkDirection(int t, int d) {
        turnDirection[t] = d;

        if (t-1>=1 && turnDirection[t-1] == 0){
            if (gears[t-1][2] != gears[t][6]) checkDirection(t-1, d * -1);
        }
        if (t+1<=4 && turnDirection[t+1] == 0){
            if (gears[t][2] != gears[t+1][6]) checkDirection(t+1, d * -1);
        }

        return;
    }

    public static void gearTurn() {
        for (int i = 1; i <= 4; i++) {

            if (turnDirection[i] == 1) {
                int tmp = gears[i][7];
                for (int j = 7; j >= 1; j--) {
                    gears[i][j] = gears[i][j - 1];
                }
                gears[i][0] = tmp;
            } else if (turnDirection[i] == -1) {
                int tmp = gears[i][0];
                for (int j = 0; j < 7; j++) {
                    gears[i][j] = gears[i][j + 1];
                }
                gears[i][7] = tmp;
            }
        }
    }

}

/*
4개의 자석
8개의 날
2개의 자성(S/N)

규칙
- 임의의 자석을 1칸 씩 k번 회전하면,
옆의 자석은 자성이 다를 경우 반대로 1칸 회전한다.

점수 규칙
- 1 : N이면 0, S면 1
- 2 : N이면 0, S면 2
- 3 : N이면 0, S면 4
- 4 : N이면 0, S면 8

k번(1~20)의 회전 후
획득할 수 있는 점수의 총합은?

시계방향 : 1
반시계방향 : -1

N: 0
S: 1

일 처리 순서...


톱날 정보는 빨간 화살표 부터 시계방향으로 제공된다.

1 > 2 > 3 > 4

2 > 1
  > 3 > 4

3 > 2 > 1
  > 4

4 > 3 > 2 > 1

1 1 입력)
1의 2와 2의 6을 확인한다. > 다름
2의 2와 3의 6을 확인한다. > 같음 > 더 이상 알아보지 X

1이 시계방향으로 돌아가고, 2는 반시계방향으로 돌아간다.

3 -1 입력)
3의 2와 4의 6을 확인한다. > 다름
3의 6과 2의 2을 확인한다. > 다름
2의 6과 1의 2를 확인한다. > 다름

3은 반시계로 돌아간다.
4는 시계로 돌아간다.
2는 반시계로 돌아간다.
1은 시계로 돌아간다.

두 톱니바퀴가 맞닿은 곳을 비교할 때 각 톱니바퀴의 회전 방향을 결정한다.
turnDirection[4]에 저장하는데 만약 안 돌아가면 확실히 0으로 바꿔줘야 함
아니면 아예 배열 초기화하고 특정 부분만 값 교체하던가ㅇㅇ

돌리는 거 전용 함수 만들어서
한번에 turnDirection 읽고 각각의 뭐시기를 돌린다.


Gear[4][8]
0 0 1 0 0 1 0 0
1 0 0 1 1 1 0 1
0 0 1 0 1 1 0 0
0 0 1 0 1 1 0 1

시계방향으로 돌리면
0 0 0 1 0 0 1 0
: 뒤에꺼 빼서 앞에 추가

반시계방향으로 돌리면
0 1 0 0 1 0 0 0
: 앞에써 빼서 뒤에 추가

에휴 걍 귀찮으니까 iterator 일일히 돌려야것다
 */
