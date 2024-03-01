import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        House house = new House();
        house.input();
        house.twoThousandYearLater();
        house.answer();
    }

    static class House{
        class Tomato implements Comparable<Tomato>{
            int day;
            int x;
            int y;

            Tomato(int day, int y, int x){
                this.day = day;
                this.y = y;
                this.x = x;
            }

            @Override
            public int compareTo(Tomato o) {
                return this.day - o.day;
            }

            
        }

        int N,M,answer;
        Tomato[][] tomatos;
        List<Tomato> ripenTomatos;
        int[][] add = {{0,-1}, {-1,0}, {1,0}, {0,1}};
        
        House(){
            ripenTomatos = new ArrayList<>();
        }

        void input()throws IOException{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer stk = new StringTokenizer(br.readLine());
             
            M = Integer.parseInt(stk.nextToken());
            N = Integer.parseInt(stk.nextToken());
            tomatos = new Tomato[N+2][M+2];

            for(int i=1; i<=N; i++){
                stk = new StringTokenizer(br.readLine());
                for(int j=1; j<=M; j++){
                    Tomato tomato = new Tomato(Integer.parseInt(stk.nextToken()), i, j);
                    tomatos[i][j] = tomato;

                    if(tomato.day == 1){
                        ripenTomatos.add(tomato);
                    }
                }
            }
        }

        void twoThousandYearLater(){
            Queue<Tomato> pq = new PriorityQueue<>();
            ripenTomatos.forEach(t->pq.add(t));

            while(!pq.isEmpty()){
                Tomato tomato = pq.poll();
                int x = tomato.x;
                int y = tomato.y;
                int day = tomato.day;

                for(int i=0; i<4; i++){
                    Tomato nextTomato = tomatos[y+add[i][0]][x+add[i][1]];
                    if(nextTomato==null)
                        continue;

                    if(nextTomato.day>day){
                        nextTomato.day = day+1;
                    }
                    else if(nextTomato.day==0){
                        nextTomato.day = day+1;
                        pq.add(nextTomato);
                    }
                }
            }
        }
    
        void answer(){
            for(int i=1; i<=N; i++){
                for(int j=1; j<=M; j++){
                    int day = tomatos[i][j].day;

                    if(day==0){
                        System.out.println(-1);
                        return;
                    }
                    
                    answer = Math.max(answer, day);
                }
            }

            System.out.println(answer-1);
        }
    }
}

