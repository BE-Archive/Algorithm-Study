import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_5653 {

    public static class Cell implements Comparable<Cell>{

        int life;
        int status;
        int x;
        int y;

        Cell(int x, int y, int life) {
            this.x = x;
            this.y = y;
            this.life = life;
            this.status = life;
        }

        @Override
        public int compareTo(Cell o) {
            return o.life-this.life;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;

        int[][] direction = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            PriorityQueue<Cell> totPQ = new PriorityQueue<>();

            boolean[][] lab = new boolean[650][650]; //공간 사용 여부 저장
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < m; j++) {
                    int input = Integer.parseInt(st.nextToken());
                    if (input > 0) {
                        lab[i + 300][j + 300] = true;
                        totPQ.add(new Cell(i + 300, j + 300, input));
                    }
                }
            }

            while (k > 0) {
                Queue<Cell> tmpQ = new LinkedList<>();

                while (!totPQ.isEmpty()) {
                    Cell now = totPQ.poll(); //줄기세포 위치

                    now.status--;

                    //활성화된 첫 1시간이라면
                    if (now.status==-1){
                        for (int i = 0; i < 4; i++) { //상하좌우 검사
                            if ( !lab[now.x+direction[i][0]][now.y+direction[i][1]] ){ //비어있으면
                                lab[now.x+direction[i][0]][now.y+direction[i][1]]=true;
                                tmpQ.add(new Cell(now.x+direction[i][0], now.y+direction[i][1], now.life));
                            }
                        }
                    }

                    if (now.status+now.life == 0) continue;
                    tmpQ.add(now);
                }

                while(!tmpQ.isEmpty()) totPQ.add(tmpQ.poll());
                k--;
            }

            sb.append("#").append(tc).append(" ").append(totPQ.size()).append("\n");

        }

        System.out.println(sb.toString());

    }

}

/*

생명력(1~10)
x시간 동안 비활성
x시간 동안 활성
- 첫 1시간은 상하좌우 동시 번식
- 이미 세포가 있으면 번식 X
- 동시 번식 시, 생명력 수치 높은 애가 이김
사망(차 있는 상태지만 시간에 따라 변하지 X)

줄기세포의 초기상태와 배양시간 k시간(1~300)
-살아있는 줄기 세포의 총 개수를 구하라!(활성, 비활성, 사망, 공백 중 활성이랑 비활성만 계산하면 됨)

Cell 클래스
생명력 수치 : n
현재 상태 : die(-100), active(-1~-n), xActive(1~n)
- 시간 지날 때마다 현재 상태 --
- 현재 상태가 -1일 때, 상하좌우를 내 생명력 수치로 채운다.
- 현재 상태가 -1*생명력 수치 인 경우 -100으로 업데이트

k가 0되면
life가 0이 아니면서
status가 -100이 아닌 곳 개수를 세면 된다.

300+50+300 * 300+50+300 * 4 = 1690000 = 1.69MB
650*650*300 = 126 750 000 = 1.2초
 */