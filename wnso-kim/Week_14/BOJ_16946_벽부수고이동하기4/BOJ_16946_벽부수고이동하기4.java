import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16946_벽부수고이동하기4 {

    static int N,M;
    static int[][] MAP;
    static State[][] states;

    static class State{ // 좌표 상태 저장을 위한 클래스
        int r,c;
        int size;
        State parent;

        State(int r, int c){
            this.r = r;
            this.c = c;
            size = 0;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력| N,M
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stk.nextToken());
        M = Integer.parseInt(stk.nextToken());

        // 입력 및 초기화| MAP, states
        MAP = new int[N][M];
        states = new State[N][M];
        for(int i=0; i<N; i++){
            char[] input = br.readLine().toCharArray();
            
            for(int j=0; j<M; j++){
                MAP[i][j] = input[j]-'0';
                states[i][j] = new State(i, j);
            }
        }

        // 0으로 집합 만들기
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(MAP[i][j] == 1) continue;
                
                union(i,j);
            }
        }

        // 결과 만들기
        StringBuilder result = new StringBuilder();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(MAP[i][j] == 0) result.append(0);
                else{
                    List<State> list = new LinkedList<>();
                    int sum = 1;

                    for(int k=0; k<4; k++){
                        int r = i+dr[k];
                        int c = j+dc[k];
                        if(isOut(r, c)) continue;   // 방문 불가(장외)
                        if(MAP[r][c]==1) continue;  // 방문 불가(벽)
                                                    // 방문 불가(이전과 같은 그룹) 
                        if(!list.isEmpty() && list.indexOf(states[r][c].parent)>=0) continue;

                        sum = (sum + states[r][c].size) %10;
                        list.add(states[r][c].parent);
                    }

                    result.append(sum);
                }
            }
            result.append("\n");
        }

        //결과 출력
        System.out.println(result);
    }  

    static void union(int R, int C){
        if(states[R][C].parent != null) return; // 방문 불가(이전 탐색)

        List<State> list = new LinkedList<>();
        Queue<State> queue = new ArrayDeque<>();
        
        State root = states[R][C];

        list.add(root);
        queue.add(root);

        root.parent = root; // 자기 자신을 부모로 선정
        int size = 1;
        
        while(!queue.isEmpty()){
            State state = queue.poll();

            for(int i=0; i<4; i++){
                int r = state.r + dr[i];
                int c = state.c + dc[i];

                if(isOut(r, c)) continue;     // 방문 불가(장외)
                if(MAP[r][c] == 1) continue;  // 방문 불가(벽)
                if(states[r][c].parent != null) continue; // 방문 불가(이전 탐색)

                queue.add(states[r][c]);
                list.add(states[r][c]);

                states[r][c].parent = root;
                size++;
            }
        }// 0 방문 끝
        
        // 0 그룹 만들기
        for(State state: list){
            state.size = size;
        }
    }

    static int[] dr = {0,0,-1,1};
    static int[] dc = {1,-1,0,0};
    static boolean isOut(int r, int c){
        return r<0 || c<0 || r>=N || c>=M;
    }
}