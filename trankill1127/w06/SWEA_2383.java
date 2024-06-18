import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SWEA_2383 {

    public static class Point {
        int x, y;
        Point(int x, int y){
            this.x=x;
            this.y=y;
        }
    }

    public static class Person implements Comparable<Person>{
        int work;
        Point pos;

        Person(int x, int y){
            pos = new Point(x,y);
            work=0;
        }

        @Override
        public int compareTo(Person o) {
            return this.work-o.work;
        }
    }

    public static class Stair {
        Point pos;
        int pplCnt;
        int len;

        Stair(int x, int y, int p, int l){
            pos = new Point(x,y);
            pplCnt=p;
            len=l;
        }

        public String toString() {
            return pos.x+" "+pos.y+" : pplCnt="+pplCnt+" len="+len;
        }
    }

    public static List<Person> people;
    public static List<Point> stair;
    public static int[][] area;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t =Integer.parseInt(br.readLine());
        for (int tc=1; tc<=t; tc++) {
            int n =Integer.parseInt(br.readLine());
            people = new ArrayList<>(); //인간 좌표
            stair = new ArrayList<>(); //계단 좌표

            area = new int[n][n];
            for (int i=0; i<n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for (int j=0; j<n; j++) {
                    area[i][j]=Integer.parseInt(st.nextToken());
                    if (area[i][j]==1) people.add(new Person(i,j));
                    if (area[i][j]>1) stair.add(new Point(i,j));
                }
            }
            minTime=Integer.MAX_VALUE;
            whichStair = new int[people.size()];
            solution(0);
            sb.append("#"+tc+" ").append(minTime).append("\n");

        }

        System.out.println(sb.toString());
    }

    public static int[] whichStair;
    public static int minTime;

    public static void solution(int idx){
        if (idx==people.size()){
            int time = getTime();
            minTime = Math.min(time, minTime);
            return;
        }

        whichStair[idx]=0;
        solution(idx+1);
        whichStair[idx]=1;
        solution(idx+1);
    }

    public static int getTime(){
        Queue<Integer> stair1_to = new PriorityQueue<>();
        Queue<Integer> stair2_to = new PriorityQueue<>();

        for (int i=0; i< whichStair.length; i++){
            int d;
            if (whichStair[i]==0){
                d = getDistance(stair.get(0).x,stair.get(0).y,people.get(i).pos.x,people.get(i).pos.y);
                stair1_to.add(d+1);
            }
            else {
                d = getDistance(stair.get(1).x,stair.get(1).y,people.get(i).pos.x,people.get(i).pos.y);
                stair2_to.add(d+1);
            }
        }

        int result = Math.max(timeFlies(stair1_to, area[stair.get(0).x][stair.get(0).y]), timeFlies(stair2_to, area[stair.get(1).x][stair.get(1).y]));
        //System.out.println(result);
        return result;
    }

    public static int timeFlies(Queue<Integer> people, int stairLen){

        if (people.isEmpty()) return 0;

        Queue<Integer> onStair = new PriorityQueue<>();
        int curTime = people.peek();
        while (true) {
            //System.out.println("현재시각: "+curTime);

            while (!onStair.isEmpty() && onStair.peek()<=curTime){
                //System.out.println("아래층 도착: "+onStair.peek());
                onStair.poll();

            }

            while (!people.isEmpty() && people.peek()<=curTime && onStair.size()<3){
                //System.out.println("계단 도착: "+people.peek());
                people.poll();
                onStair.add(curTime+stairLen);
            }

            if (people.isEmpty() && onStair.isEmpty()) break;
            curTime++;
        }

        return curTime;
    }

    public static int getDistance(int x1, int y1, int x2, int y2){
        return Math.abs(x1-x2)+Math.abs(y1-y2);
    }
}