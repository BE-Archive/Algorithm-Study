import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;
 
class Solution {
    static final int MAX_PEOPLE = 10;
    static final int MAX_WAITING = 3;
    static final int MAX_STAIR = 2;
     
    static Pair[] stairs = new Pair[MAX_STAIR];
     
    static class Pair {
        int row, col, target, time;
 
        public Pair(int row, int col, int target, int time) {
            this.row = row;
            this.col = col;
            this.target = target;
            this.time = time;
        }
         
        boolean spendTime() {
            if(time == 0) return true;
            return --time == 0;
        }
    }
    public static int getMovingTime(int row, int col, int type) {
        return Math.abs(row - stairs[type].row) + Math.abs(col - stairs[type].col);
    }
    public static int binary_counting(ArrayList<Pair> people) {
        int L = people.size(), answer = Integer.MAX_VALUE;
        for(int i = 0; i < (1 << L); i++) {
            for(int j = 0; j < L; j++) {
                Pair person = people.get(j);
                if((i & (1 << j)) != 0) {
                    person.target = 1;
                    person.time = getMovingTime(person.row, person.col, 1);
                }
                else {
                    person.target = 0;
                    person.time = getMovingTime(person.row, person.col, 0);
                }
            }
            answer = Math.min(answer, simulation(people));
        }
        return answer;
    }
     
    public static int simulation(ArrayList<Pair> people) {
        Queue<Pair> queue = new ArrayDeque<>();
        ArrayList<Queue<Pair>> stairList = new ArrayList<>();
        for(int i = 0; i < MAX_STAIR; i++) {
            stairList.add(new ArrayDeque<Pair>(MAX_WAITING));
        }
        for(Pair person : people) {
            queue.add(person);
        }
        int count = 0, temp = 1;
        while(count < people.size()) {
            for(Queue waitingQueue : stairList) {
                int waitingLength = waitingQueue.size();
                while(--waitingLength >= 0) {
                    Pair front = (Pair) waitingQueue.poll();
                    if(!front.spendTime()) {
                        waitingQueue.add(front);
                    }
                    else count++;
                }
            }
            int size = queue.size();
            while(--size >= 0) {
                Pair front = queue.poll();
                int target = front.target;
                Queue waitingQueue = stairList.get(target);
                if(front.spendTime()) {
                    if(waitingQueue.size() < MAX_WAITING) {
                        front.time = stairs[target].time;
                        waitingQueue.add(front);
                    }
                    else queue.add(front);
                }
                else queue.add(front);
            }
            temp++;
        }
        return temp;
    }
     
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++) {
            sb.append('#').append(test_case).append(' ');
            int N = Integer.parseInt(br.readLine()), L = 0;
            int[][] mat = new int[N][N];
            ArrayList<Pair> p = new ArrayList<>(MAX_PEOPLE);
            int index = 0;
            for(int row = 0; row < N; row++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int col = 0; col < N; col++) {
                    mat[row][col] = Integer.parseInt(st.nextToken());
                    if(mat[row][col] == 1) {
                        p.add(new Pair(row, col, 0, 0));
                    }
                    else if(mat[row][col] > 1) {
                        stairs[index++] = new Pair(row, col, -1, mat[row][col]);
                    }
                }
            }
            sb.append(binary_counting(p)).append('\n');
        }
        System.out.println(sb);
    }
}