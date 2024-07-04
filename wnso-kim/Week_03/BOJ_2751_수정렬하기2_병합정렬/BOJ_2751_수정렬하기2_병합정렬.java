import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static void mergeSort(int[] arr){
        int[] tmp = new int[arr.length];
        mergeSort(arr, tmp, 0, arr.length-1);
    }

    static void mergeSort(int[] arr, int[] tmp, int start, int end){
        if(start>=end) return;

        int mid = (start+end)/2;
        mergeSort(arr, tmp, start, mid);
        mergeSort(arr, tmp, mid+1, end);
        merge(arr, tmp, start, mid, end);
    }

    static void merge(int[] arr, int[] tmp, int start, int mid, int end){
        // 임시 배열에 값 저장|
        for(int i=start; i<=end; i++){
            tmp[i] = arr[i];
        }

        int part1 = start;
        int part2 = mid+1;
        int index = start;
        while(part1<=mid && part2<=end){
            if(tmp[part1] <= tmp[part2])
                arr[index++] = tmp[part1++];
            else
                arr[index++] = tmp[part2++];
        }

        for(;part1<=mid;){
            arr[index++] = tmp[part1++];
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // input| N
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        // input| array
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        // sort| merge-sort
        mergeSort(arr);

        // print
        StringBuilder result = new StringBuilder();
        for(int num: arr)
            result.append(num+"\n");

        System.out.print(result);
        br.close();
    }
}
