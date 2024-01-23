package study.Week_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class BOJ_14719 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    private static int sumUp(int[] a, int[] b, int[] arr){
        return Math.min(a[0],b[0])*(b[1]-a[1]-1)-(arr[b[1]-1]-arr[a[1]]);
    }

  //TreeSet 이용 풀이
    private static void solution1(int w) throws IOException {
        TreeSet<int[]> ts = new TreeSet<>((o1,o2)->{
            if (o1[0]==o2[0]) return o1[1]-o2[1];
            return o2[0]-o1[0];
        });
        int[] arr = new int[w];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++) {
            int[] tmp = {Integer.parseInt(st.nextToken()), i};
            ts.add(tmp);
            if (i==0){
                arr[0] = tmp[0];
                continue;
            }
            arr[i] = arr[i-1]+tmp[0];
        }

        int[] a = ts.pollFirst();
        int[] s = a;
        int[] e = a;
        int sum=0;
        int[] tmp;
        while(s[1]!=0 || e[1]!=w-1){
            tmp = ts.pollFirst();
            if(tmp[1]<s[1]){
                sum+=sumUp(tmp,s,arr);
                s = tmp;
            }
            else if(e[1]<tmp[1]){
                sum+=sumUp(e,tmp,arr);
                e = tmp;
            }
        }
        System.out.println(sum);
    }

  // 투포인터? 이용 풀이
    private static void solution2(int w) throws IOException{
        int[][] arr = new int[w][2];

        int max;
        st = new StringTokenizer(br.readLine());
        arr[0][0] = Integer.parseInt(st.nextToken());
        arr[0][1] = 0;
        max = arr[0][0];
        for (int i = 1; i < w; i++) {
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = max;
            max = Math.max(max,arr[i][0]);
        }

        int sum = 0;
        max = arr[w-1][0];
        for (int i = w-2; i >= 0; i--) {
            sum+=Math.max(0, Math.min(arr[i][1],max)-arr[i][0]);
            max = Math.max(arr[i][0], max);
        }
        System.out.println(sum);
    }


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

//        solution1(w);
        solution2(w);
    }
}

