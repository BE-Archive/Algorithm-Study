package ing;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1423 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] power = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            power[i] = Integer.parseInt(st.nextToken());
        }
        power[n] = Integer.MIN_VALUE+power[n-1];

        System.out.println(Arrays.toString(power));


        TreeMap<Integer, Integer> ts = new TreeMap<>(Comparator.comparingDouble(o->-power[o+1]+power[o]+o/10.0));
        for (int i = 0; i < n; i++) {
            if(arr[i]>0)ts.put(i,arr[i]);
        }
        System.out.println(Arrays.toString(ts.keySet().toArray(new Integer[0])));
        int d = Integer.parseInt(br.readLine());
        int k, v;
        while(d>0) {
            k = ts.firstKey();
            v = ts.get(k);
            System.out.println(k+" "+v);
            System.out.println(Arrays.toString(arr));
            if (v > d){
               ts.replace(k, v-d);
               if(k==n-1) continue;
               arr[k] -= d;
               arr[k+1] += d;
               if(ts.containsKey(k+1)){
                   ts.replace(k+1, ts.get(k+1)+d);
               } else {
                   ts.put(k+1, d);
               }
               d -= d;
            } else {
                ts.remove(k);
                if(k==n-1) continue;
                arr[k] = 0;
                arr[k+1] += v;
                if(ts.get(k+1)!=null){
                    ts.replace(k+1, ts.get(k+1)+v);
                } else ts.put(k+1, v);
                d-=v;
            }
        }
        int answer =0;
        for (int i = 0; i < n; i++) {
            answer+=arr[i]*power[i];
        }
        System.out.println(answer);
    }
}
