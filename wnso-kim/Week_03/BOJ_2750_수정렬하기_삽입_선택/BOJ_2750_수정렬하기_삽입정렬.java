package BOJ_2750_수정렬하기_삽입_선택;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        // sort
        for(int i=1; i<N; i++){
            for(int j=i; j>0; j--){
                if(arr[j-1] <= arr[j])
                    continue;

                // SWAP
                int tmp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = tmp;
            }
        }

        StringBuilder result = new StringBuilder();
        for(int num: arr)
            result.append(num+"\n");

        System.out.print(result);
        br.close();
    }
}
