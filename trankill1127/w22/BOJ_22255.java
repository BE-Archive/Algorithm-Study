import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_22255 {

    public static int n;
    public static int m;
    public static int[][] direction = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    public static int[] start;
    public static int[] end;
    public static int[][] map;
    public static PriorityQueue<int[]> pq;
    public static int[][][] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        start = new int[]{Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1};
        end = new int[]{Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1};
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[3]-o2[3];
            }
        };
        pq = new PriorityQueue<>(comparator);
        pq.add(new int[]{start[0], start[1], 1, 0}); //x, y, n번째 이동, 충격량

        distance = new int[n][m][3];
        for (int i=0; i<n; i++){
            for (int j=0; j<m; j++){
                for (int k=0; k<3; k++){
                    distance[i][j][k]=Integer.MAX_VALUE;
                }
            }
        }
        distance[start[0]][start[1]][1]=0;

        int answer=-1;
        while (!pq.isEmpty()){
            int[] now = pq.poll();
            if (now[0]==end[0] && now[1]==end[1]) {
                answer=now[3];
                break;
            }

            if (now[2] % 3 == 0){
                for (int d=0; d<4; d++){
                    int[] nextPos = new int[]{now[0]+direction[d][0],now[1]+direction[d][1]};

                    if (nextPos[0]<0||nextPos[0]>=n||nextPos[1]<0||nextPos[1]>=m) continue;
                    if (map[nextPos[0]][nextPos[1]]==-1) continue;

                    if (distance[nextPos[0]][nextPos[1]][(now[2]+1)%3] > now[3]+map[nextPos[0]][nextPos[1]]){
                        distance[nextPos[0]][nextPos[1]][(now[2]+1)%3] = now[3]+map[nextPos[0]][nextPos[1]];
                        pq.add(new int[]{nextPos[0], nextPos[1], now[2]+1, now[3]+map[nextPos[0]][nextPos[1]]});
                    }
                }
            }
            else if (now[2] % 3 == 1){
                for (int d=0; d<2; d++){
                    int[] nextPos = new int[]{now[0]+direction[d][0],now[1]+direction[d][1]};

                    if (nextPos[0]<0||nextPos[0]>=n||nextPos[1]<0||nextPos[1]>=m) continue;
                    if (map[nextPos[0]][nextPos[1]]==-1) continue;

                    if (distance[nextPos[0]][nextPos[1]][(now[2]+1)%3] > now[3]+map[nextPos[0]][nextPos[1]]){
                        distance[nextPos[0]][nextPos[1]][(now[2]+1)%3] = now[3]+map[nextPos[0]][nextPos[1]];
                        pq.add(new int[]{nextPos[0], nextPos[1], now[2]+1, now[3]+map[nextPos[0]][nextPos[1]]});
                    }
                }
            }
            else {
                for (int d=2; d<4; d++){
                    int[] nextPos = new int[]{now[0]+direction[d][0],now[1]+direction[d][1]};

                    if (nextPos[0]<0||nextPos[0]>=n||nextPos[1]<0||nextPos[1]>=m) continue;
                    if (map[nextPos[0]][nextPos[1]]==-1) continue;

                    if (distance[nextPos[0]][nextPos[1]][(now[2]+1)%3] > now[3]+map[nextPos[0]][nextPos[1]]){
                        distance[nextPos[0]][nextPos[1]][(now[2]+1)%3] = now[3]+map[nextPos[0]][nextPos[1]];
                        pq.add(new int[]{nextPos[0], nextPos[1], now[2]+1, now[3]+map[nextPos[0]][nextPos[1]]});
                    }
                }
            }
        }

        System.out.println(answer);
    }

}