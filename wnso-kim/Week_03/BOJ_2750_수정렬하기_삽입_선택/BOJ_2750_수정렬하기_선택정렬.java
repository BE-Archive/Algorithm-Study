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

        // sort| selection-sort
        for(int i=0; i<N-1; i++){
            int min = i;

            for(int j=i+1; j<N; j++){
                if(arr[min]<arr[j])
                    continue;

                min = j;
            }

            if(min!=i){
                int tmp = arr[min];
                arr[min] = arr[i];
                arr[i] = tmp;
            }
        }

        StringBuilder result = new StringBuilder();
        for(int num: arr)
            result.append(num+"\n");

        System.out.print(result);
        br.close();
    }
}
