package trankill1127.w37;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2533 {

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<List<Integer>> graph = new LinkedList<>();
        for (int i=0; i<=n; i++){
            graph.add(new LinkedList<>());
        }

        StringTokenizer st = null;
        int v1;
        int v2;
        for (int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            v1=Integer.parseInt(st.nextToken());
            v2=Integer.parseInt(st.nextToken());
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }



    }

}