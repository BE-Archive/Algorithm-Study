package sjhlko.week6.SWEA_2383;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_2383_점심식사시간 {
    // https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5-BEE6AK0DFAVl
    // 점심 식사시간
    static int ans;
    static int N;
    static int[][] map;
    static PriorityQueue<Info> pqFir, pqSec;
    static List<int[]> stairs, students;
    static List<Info> infos;

    static Comparator<Info> compareByFirAsc = new Comparator<Info>() {
        @Override
        public int compare(Info o1, Info o2) {
            return o1.distanceToFir - o2.distanceToFir;
        }
    };

    static Comparator<Info> compareBySecAsc = new Comparator<Info>() {
        @Override
        public int compare(Info o1, Info o2) {
            return o1.distanceToSec - o2.distanceToSec;
        }
    };

    static class Info {
        int i, j;
        int distanceToFir, distanceToSec;

        public Info(int i, int j, int distanceToFir, int distanceToSec) {

            this.i = i;
            this.j = j;
            this.distanceToFir = distanceToFir;
            this.distanceToSec = distanceToSec;
        }
    }

    static int calcDistance(int fromI, int fromJ, int toI, int toJ) {
        return Math.abs(fromI - toI) + Math.abs(fromJ - toJ);
    }

    static void initInfos() {
        int[] firStair = stairs.get(0);
        int[] secStair = stairs.get(1);
        for (int i = 0; i < students.size(); i++) {
            int nowI = students.get(i)[0];
            int nowJ = students.get(i)[1];
            infos.add(new Info(nowI, nowJ, calcDistance(nowI, nowJ, firStair[0], firStair[1]) + 1,
                    calcDistance(nowI, nowJ, secStair[0], secStair[1]) + 1));
        }
    }

    static void setAns(int[] toGo) {
        int firStairLength = map[stairs.get(0)[0]][stairs.get(0)[1]];
        int secStairLength = map[stairs.get(1)[0]][stairs.get(1)[1]];
        for (int i = 0; i < toGo.length; i++) {
            if (toGo[i] == 0)
                pqFir.add(infos.get(i));
            else
                pqSec.add(infos.get(i));
        }
        int ansFir = calcAns(pqFir, !pqFir.isEmpty() ? pqFir.peek().distanceToFir : 0, firStairLength, 0);
        int ansSec = calcAns(pqSec, !pqSec.isEmpty() ? pqSec.peek().distanceToSec : 0, secStairLength, 1);
        ans = Math.min(Math.max(ansFir, ansSec), ans);

    }

    static int calcAns(PriorityQueue<Info> pq, int start, int time, int stairIndex) {
        Queue<Integer> outTime = new ArrayDeque<>();
        for (int i = start; ; i++) {
            while (!outTime.isEmpty() && outTime.peek() <= i)
                outTime.poll();
            while (!pq.isEmpty() && (stairIndex == 0 ? pq.peek().distanceToFir : pq.peek().distanceToSec) <= i
                    && outTime.size() < 3) {
                pq.poll();
                outTime.offer(i + time);
            }
            if (pq.isEmpty() && outTime.isEmpty())
                return i;
        }
    }

    static void solution(int count, int[] toGo) {
        if (count == infos.size()) {
            setAns(toGo);
            return;
        }
        toGo[count] = 0;
        solution(count + 1, toGo);
        toGo[count] = 1;
        solution(count + 1, toGo);

    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        int tmpT = T;
        while (T-- > 0) {
            ans = Integer.MAX_VALUE;
            N = Integer.parseInt(bf.readLine());
            map = new int[N][N];
            pqFir = new PriorityQueue<>(compareByFirAsc);
            pqSec = new PriorityQueue<>(compareBySecAsc);
            stairs = new ArrayList<>();
            students = new ArrayList<>();
            infos = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < N; j++) {
                    int a = Integer.parseInt(st.nextToken());
                    map[i][j] = a;
                    if (a >= 2)
                        stairs.add(new int[]{i, j});
                    if (a == 1)
                        students.add(new int[]{i, j});
                }
            }
            initInfos();
            solution(0, new int[students.size()]);
            System.out.println("#" + (tmpT - T) + " " + ans);
        }
    }
}
