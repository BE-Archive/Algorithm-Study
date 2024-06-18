import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2805 {

    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] tree = new int[n];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i=0; i<n; i++) tree[i]=Integer.parseInt(st.nextToken());
        Arrays.sort(tree);

        int middle=0;
        int start = 0; int end = tree[n-1];
        while (start<=end){
            middle = (start+end)/2;

            long cuttedTree = 0;
            for (int i=0; i<n; i++)
                if (tree[i]>middle)
                    cuttedTree+=(tree[i]-middle);

            if (cuttedTree<m){
                end=middle-1;
            }
            else {
                start=middle+1;
            }
        }

        System.out.println(middle);
    }
}
