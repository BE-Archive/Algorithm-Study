import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_12865 {

    public static class Item implements Comparable<Item>{
        int w;
        int v;

        Item(int w, int v){
            this.w=w;
            this.v=v;
        }

        @Override
        public int compareTo(Item o) {
            return this.w-o.w;
        }
    }

    public static int n;
    public static int k;
    public static ArrayList<Item> arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new ArrayList<>();
        int[] dp = new int[k+1];
        for (int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            arr.add(new Item(w,v));
            dp[w]=v;
        }

        Collections.sort(arr);

        for (int i = arr.get(0).w; i<=k; i++){ //무게
            for (int j=0; j<n; j++){ //
                if (i-arr.get(j).w < 0) break;

                if (dp[i-arr.get(j).w]>0 && dp[i]<dp[i-arr.get(j).w]+arr.get(j).v){
                    dp[i]=dp[i-arr.get(j).w]+arr.get(j).v;
                }
            }

            //System.out.println(Arrays.toString(dp));

        }

        int maxV = 0;
        for (int i=1; i<=k; i++){
            if (maxV<dp[i]) maxV=dp[i];
        }
        System.out.println(maxV);
    }

}

/*
2^100 = 1,267,650,600,228,229,401,496,703,205,376


2차원 dp (??진챠 내 인생에서 처음 봐)
행 : 가방의 max 무게
열 : 아이템



 */