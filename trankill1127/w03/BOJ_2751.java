import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2751 {

    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n= Integer.parseInt(br.readLine());
        arr = new int[n];
        for (int i=0; i<n; i++){
            arr[i]=Integer.parseInt(br.readLine());
        }
        divide(0, n);
        for (int sn : arr){
            sb.append(sn).append("\n");
        }
        System.out.println(sb.toString());
    }

    //합병정렬
    public static void divide(int s, int e){
        if ((e-s)==1) return;

        int mid = (s+e)/2;
        divide(s, mid);
        divide(mid, e);
        merge(s, mid, e);
    }

    public static void merge(int s, int mid, int e) {
        int[] tmp = new int[e-s];
        int idx = 0;
        int ls=s, rs=mid;
        while (ls<mid && rs<e){
            if (arr[ls]<arr[rs]) {
                tmp[idx]=arr[ls];
                ls++;
            }
            else {
                tmp[idx]=arr[rs];
                rs++;
            }
            idx++;
        }

        for (int i=0; i<tmp.length; i++){
            arr[s+i]=tmp[i];
        }
    }
}