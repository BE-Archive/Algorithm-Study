import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2750 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i=0; i<n; i++){
            arr[i]=Integer.parseInt(br.readLine());
        }
        //selectSort(arr);
        insertSort(arr);
        for (int sn : arr){
            System.out.println(sn);
        }
    }

    //선택정렬
    //12660KB	148ms
    public static void selectSort(int[] arr){
        int len = arr.length;
        for (int i=0; i<len-1; i++){
            int minIdx=i;
            for (int j=i+1; j<len; j++){
                if (arr[minIdx]>arr[j]){
                    minIdx=j;
                }
            }
            int tmp=arr[i];
            arr[i]=arr[minIdx];
            arr[minIdx]=tmp;
        }
    }

    //삽입정렬
    //12544KB   144ms
    public static void insertSort(int[] arr){
        int len = arr.length;
        for (int i=1; i<len; i++){
            for (int j=0; j<i; j++){
                if (arr[j]>arr[i]){
                    int tmp = arr[i];
                    arr[i]=arr[j];
                    arr[j]=tmp;
                }
            }
        }
    }

}
