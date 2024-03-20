import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N,L,R;
    static int[][] map;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력| N L R
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        L = Integer.parseInt(stk.nextToken());
        R = Integer.parseInt(stk.nextToken());

        // 입력| map
        map = new int[N][N];
        for(int i=0; i<N; i++){
            stk = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(stk.nextToken());
            }
        }

        // 찾기
        int result = twoThousandYearsLater();

        // 출력
        System.out.println(result);
    }

    // 최대 2000일 반복
    static int twoThousandYearsLater(){
        for(int i=0; i<2000; i++){
            if(oneDay())
                return i;
        }

        return 2000;
    }

    // 하루동안 처리할 일
    static boolean[][] visit;
    static List<List<int[]>> borderList; //열린 국경 집합들의 목록
    static boolean oneDay(){
        visit = new boolean[N][N];
        borderList = new ArrayList<>();

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(!visit[i][j])
                    bfs(i,j);
            }
        }
        // 열린 국경이 없으면 이제 끝!
        if(borderList.size() == 0) return true;

        // 열린 국경이 있으므로 국경마다 평균 구하기
        for(List<int[]> list: borderList){
            int sum = 0;
            for(int[] arr: list){
                sum += map[arr[0]][arr[1]];
            }

            int avg = sum / list.size();
            for(int[] arr: list){
                map[arr[0]][arr[1]] = avg;
            }
        }

        // 내일 또 국경 열어야지..
        return false;
    }

    // 한 나라에서 갈 수 있는 나라들 담기
    static void bfs(int r, int c){
        List<int[]> countries = new ArrayList<>();
        Queue<int[]> queue = new ArrayDeque<>();
        
        visit[r][c] = true;
        countries.add(new int[]{r,c});
        queue.offer(new int[]{r,c});

        while(!queue.isEmpty()){
            int[] state = queue.poll();
            r = state[0];
            c = state[1];
            for(int i=0; i<4; i++){
                int newR = r+dr[i];
                int newC = c+dc[i];

                if(isOut(newR, newC)) continue;// 범위 확인
                if(visit[newR][newC]) continue;// 방문 확인

                int sub = Math.abs(map[r][c] - map[newR][newC]);
                if(sub<L || sub>R) continue;// L R 범위 확인

                visit[newR][newC] = true;
                countries.add(new int[]{newR, newC});
                queue.offer(new int[]{newR,newC});
            }
        }

        if(countries.size()!=1) borderList.add(countries);
    }

    static int[] dr = {0,0,1,-1};
    static int[] dc = {1,-1,0,0};
    static boolean isOut(int r, int c){
        return r<0 || c<0 || r>=N || c>=N;
    }
}