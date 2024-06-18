package Week_16.BOJ_15644;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_15644 {


    static char[][] arr;
    static ArrayDeque<int[]> q;
    static boolean[][][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new char[n][m];
        int[] curr = new int[5];

        String str;
        for (int i = 0; i < n; i++) {
            str = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = str.charAt(j);
                switch (arr[i][j]) {
                    case 'R':
                        curr[0] = i;
                        curr[1] = j;
                        arr[i][j] = '.';
                        break;
                    case 'B':
                        curr[2] = i;
                        curr[3] = j;
                        arr[i][j] = '.';
                        break;
                }
            }
        }

        q = new ArrayDeque<>();
        q.offer(curr);
        visited = new boolean[n][m][n][m];
        visited[curr[0]][curr[1]][curr[2]][curr[3]] = true;
        int qSize, ry, rx, by, bx, i, trace = 0;
        boolean found = false;
        loop:
        for (i = 0; i < 10; i++) {
            qSize = q.size();
            while (qSize-- > 0) {
                curr = q.pollFirst();
                ry = curr[0];
                rx = curr[1];
                by = curr[2];
                bx = curr[3];
                if (curr[0] > curr[2]) {
                    // 위로 확인할땐 blue 먼저
                    while (arr[by - 1][bx] == '.') by--;
                    while (arr[ry - 1][rx] == '.' && !(by == ry - 1 && bx == rx)) ry--;

                    if (arr[by - 1][bx] != 'O') {
                        if (arr[ry - 1][rx] == 'O') {
                            found = true;
                            trace = curr[4]<<2;
                            break loop;
                        }
                        if (!visited[ry][rx][by][bx]) {
                            visited[ry][rx][by][bx] = true;
                            q.offerLast(new int[]{ry, rx, by, bx, (curr[4] << 2)});
                        }
                    }

                    // 아래로 확인할 땐 red 먼저
                    ry = curr[0];
                    by = curr[2];
                    while (arr[ry + 1][rx] == '.') ry++;
                    while (arr[by + 1][bx] == '.' && !(ry == by + 1 && rx == bx)) by++;

                    if(arr[ry+1][rx]=='O' && !(ry==by+1 && rx==bx)){
                        found = true;
                        trace = (curr[4]<<2|1);
                        break loop;
                    }
                    if(arr[by+1][bx]!='O' && !visited[ry][rx][by][bx]) {
                        visited[ry][rx][by][bx] = true;
                        q.offerLast(new int[]{ry, rx, by, bx, (curr[4] << 2|1)});
                    }

                } else {
                    // 위로 확인할땐 red 먼저
                    while (arr[ry - 1][rx] == '.') ry--;
                    while (arr[by - 1][bx] == '.' && !(ry == by - 1 && bx == rx)) by--;
                    if(arr[ry-1][rx]=='O' && !(ry==by-1 && rx==bx)){
                        found = true;
                        trace = (curr[4]<<2);
                        break loop;
                    }
                    if(arr[by-1][bx]!='O' && !visited[ry][rx][by][bx]) {
                        visited[ry][rx][by][bx] = true;
                        q.offerLast(new int[]{ry, rx, by, bx, (curr[4] << 2)});
                    }


                    // 아래로 확인할 땐 blue 먼저
                    ry = curr[0];
                    by = curr[2];
                    while (arr[by + 1][bx] == '.') by++;
                    while (arr[ry + 1][rx] == '.' && !(by == ry + 1 && rx == bx)) ry++;
                    if (arr[by + 1][bx] != 'O') {
                        if (arr[ry + 1][rx] == 'O') {
                            found = true;
                            trace = (curr[4]<<2|1);
                            break loop;
                        }
                        if (!visited[ry][rx][by][bx]) {
                            visited[ry][rx][by][bx] = true;
                            q.offerLast(new int[]{ry, rx, by, bx, (curr[4] << 2|1)});
                        }
                    }


                }

                if (curr[1] > curr[3]) {
                    // 왼쪽으로 확인할 땐 blue 먼저
                    ry = curr[0];
                    by = curr[2];
                    while (arr[by][bx - 1] == '.') bx--;
                    while (arr[ry][rx - 1] == '.' && !(bx == rx - 1 && by == ry)) rx--;
                    if (arr[by][bx-1] != 'O') {
                        if (arr[ry][rx-1] == 'O') {
                            found = true;
                            trace = (curr[4]<<2|2);
                            break loop;
                        }
                        if (!visited[ry][rx][by][bx]) {
                            visited[ry][rx][by][bx] = true;
                            q.offerLast(new int[]{ry, rx, by, bx, (curr[4] << 2|2)});
                        }
                    }


                    // 오른쪽으로 확인할 땐 red 먼저
                    rx = curr[1];
                    bx = curr[3];
                    while (arr[ry][rx + 1] == '.') rx++;
                    while (arr[by][bx + 1] == '.' && !(rx == bx + 1 && ry == by)) bx++;
                    if(arr[ry][rx+1]=='O' && !(rx==bx+1 && ry==by)){
                        found = true;
                        trace = (curr[4]<<2|3);
                        break loop;
                    }
                    if(arr[by][bx+1]!='O' && !visited[ry][rx][by][bx]) {
                        visited[ry][rx][by][bx] = true;
                        q.offerLast(new int[]{ry, rx, by, bx, (curr[4] << 2|3)});
                    }


                } else {
                    ry = curr[0];
                    by = curr[2];
                    while (arr[ry][rx - 1] == '.') rx--;
                    while (arr[by][bx - 1] == '.' && !(rx == bx - 1 && by == ry)) bx--;
                    if(arr[ry][rx-1]=='O' && !(rx==bx-1 && ry==by)){
                        found = true;
                        trace = (curr[4]<<2|2);
                        break loop;
                    }
                    if(arr[by][bx-1]!='O' && !visited[ry][rx][by][bx]) {
                        visited[ry][rx][by][bx] = true;
                        q.offerLast(new int[]{ry, rx, by, bx, (curr[4] << 2|2)});
                    }


                    rx = curr[1];
                    bx = curr[3];
                    while (arr[by][bx + 1] == '.') bx++;
                    while (arr[ry][rx + 1] == '.' && !(bx == rx + 1 && ry == by)) rx++;
                    if (arr[by][bx+1] != 'O') {
                        if (arr[ry][rx+1] == 'O') {
                            found = true;
                            trace = (curr[4]<<2|3);
                            break loop;
                        }
                        if (!visited[ry][rx][by][bx]) {
                            visited[ry][rx][by][bx] = true;
                            q.offerLast(new int[]{ry, rx, by, bx, (curr[4] << 2|3)});
                        }
                    }


                }
            }
        }
        StringBuilder sb = new StringBuilder();
        if(!found){
            sb.append(-1);
        } else {
            for (int j = 0; j < i+1; j++) {
                switch (trace&3) {
                    case 0:
                        sb.insert(0,'U');
                        break;
                    case 1:
                        sb.insert(0,'D');
                        break;
                    case 2:
                        sb.insert(0,'L');
                        break;
                    case 3:
                        sb.insert(0,'R');
                        break;
                }
                trace>>=2;
            }
            sb.insert(0, "\n").insert(0, i+1);
        }
        System.out.println(sb);
    }


}
