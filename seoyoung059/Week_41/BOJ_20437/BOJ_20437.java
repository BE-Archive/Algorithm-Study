package seoyoung059.Week_41.BOJ_20437;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_20437 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        String w; int k;
        ArrayList<Integer>[] arr = new ArrayList[26];
        int min, max;
        StringBuilder sb = new StringBuilder();

        while(T-->0){
            w = br.readLine();
            k = Integer.parseInt(br.readLine());
            min = Integer.MAX_VALUE; max = Integer.MIN_VALUE;
            for(int i = 0; i < 26; i++){
                arr[i] = new ArrayList<>();
            }

            for(int i=0; i<w.length(); i++){
                arr[w.charAt(i)-'a'].add(i);
            }

            for(int i=0; i<26; i++){
                if(arr[i].size()<k) continue;
                for(int j=0; j<arr[i].size() - k+1; j++){
                    min = Math.min(min, arr[i].get(j+k-1)-arr[i].get(j)+1);
                    max = Math.max(max, arr[i].get(j+k-1)-arr[i].get(j)+1);
                }
            }

            if(min == Integer.MAX_VALUE){
                sb.append(-1);
            } else sb.append(min).append(' ').append(max);

            sb.append('\n');
        }
        System.out.print(sb);
    }
}