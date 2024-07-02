package seoyoung059.Week_03.BOJ_2751;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2751 {
    static BufferedReader br;
    static int n;

    /*
    static void quickSort(int[] arr, int left, int right){
        int pl = left;
        int pr = right;
        int x = arr[(left+right)/2];
        int tmp;
        while(pl <= pr) {
            while(arr[pl] < x) pl++;
            while(arr[pr] > x) pr--;
            if(pl<=pr) {
                tmp = arr[pl];
                arr[pl] = arr[pr];
                arr[pr] = tmp;
                pl++;
                pr--;
            }
        }
        if(left<pr) quickSort(arr, left, pr);
        if(pl<right) quickSort(arr, pl, right);
    }
    */

    static  void mergeSort(int[] arr){
        int[] buff = new int[n];
        _mergeSort(buff, arr, 0, n-1);
    }
    static void _mergeSort(int[] buff, int[] arr, int left, int right){
        if(left<right) {
            int mid = (left+right)/2;
            _mergeSort(buff, arr, left, mid);
            _mergeSort(buff, arr, mid+1, right);

            int p = left;
            int q = mid+1;
            int idx = p;

            while(p<=mid||q<=right) {
                if(q>right||(p<=mid && arr[p] <= arr[q])){
                    buff[idx++] = arr[p++];
                } else {
                    buff[idx++] = arr[q++];
                }
            }

            for (int i = left; i <= right; i++) {
                arr[i] = buff[i];
            }
        }
    }

    static void cntSort(int[] arr) {
        int[] cnt = new int[2_000_001];
        for (int i = 0; i < n; i++) {
            cnt[arr[i]+1_000_000]++;
        }

        int idx = 0;
        for (int i = 0; i < n; i++) {
            while(idx<2_000_001 && cnt[idx]==0){
                idx++;
            }
            if(idx >= 2_000_001) break;
            arr[i] = idx-1_000_000;
            idx++;
        }
    }
    static void shellSort(int[] arr) {
        int h = n/2;
        int j, tmp;
        while(h>0) {
            for (int i = h; i < n; i++) {
                j = i-h;
                tmp = arr[i];
                while(j>=0 && arr[j]>tmp){
                    arr[j+h] = arr[j];
                    j-=h;
                }
                arr[j+h] = tmp;
            }
            h /= 2;
        }
    }
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        switch ((int)(Math.random()*3)){
            case 0:
                mergeSort(arr);
                break;
            case 1:
                cntSort(arr);
                break;
            default:
                shellSort(arr);
        }
        for (int i = 0; i < n; i++) {
            sb.append(arr[i]).append("\n");
        }
        System.out.print(sb);
    }

}