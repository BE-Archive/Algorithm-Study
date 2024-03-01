package seoyoung059.Week_05.BOJ_17281;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BOJ_17281 {
    static void swap(int[] p, int i, int j){
        int tmp = p[i];
        p[i]=p[j];
        p[j] = tmp;
    }
    static boolean perm (int[] p){
        int i = 7;
        while(i>0 && p[i-1]>=p[i]) i--;
        if(i==0) return false;

        int j = 7;
        while(j>i && p[i-1]>=p[j]) j--;

        swap(p, i-1, j);

        int k = 7;
        while(i<k) swap(p, i++, k--);
        return true;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] arr  = new int[n][9];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] p = new int[8];
        for (int i = 0; i < 8; i++) {
            p[i] = i+1;
        }

        ArrayDeque<Integer> ground = new ArrayDeque<>();
        int idx, out, tmp, curr, inning, g, score;
        int answer = 0;
        do {
            idx = 0; score = 0; ground.clear();
            for (int i = 0; i < n; i++) {
                out = 0; inning = 0;
                ground.clear();
                while(out<3){
                    if (idx > 3) {
                        curr = arr[i][p[idx-1]];
                    }
                    else if(idx<3) {
                        curr = arr[i][p[idx]];
                    }
                    else {
                        curr = arr[i][0];
                    }
                    if(curr==0) out++;
                    else if(curr==4) {
                        inning+=1+ground.size();
                        ground.clear();
                    }
                    else {
                        g = ground.size();
                        while(g-->0){
                            tmp = ground.pollFirst();
                            if(tmp+curr>=4) inning++;
                            else {
                                ground.offerLast(tmp+curr);
                            }
                        }
                        ground.offerLast(curr);
                    }
                    idx = (idx+1)%9;
                }
                score+=inning;
                ground.clear();
            }
            answer = Math.max(score, answer);
        }while(perm(p));
        System.out.println(answer);
    }
}