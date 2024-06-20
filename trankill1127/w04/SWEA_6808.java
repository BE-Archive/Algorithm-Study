import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_6808 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int tc=1; tc<=t; tc++){
            st =new StringTokenizer(br.readLine(), " ");

            int[] arrGyu = new int[9];
            for (int i=0; i<9; i++){
                arrGyu[i]=Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arrGyu);

            int[] arrIn = new int[9];
            int gyuIdx=0; int inIdx=0;
            for (int i=1; i<=18; i++){
                if (gyuIdx<9 && arrGyu[gyuIdx]==i){
                    gyuIdx++;
                    continue;
                }
                arrIn[inIdx]=i;
                inIdx++;
            }

            int gyuWinCnt=0;
            int gyuloseCnt=0;
            int noneCnt=0;

            do {
                int scoreIn=0;
                int scoreGyu=0;

                for (int i=0; i<9; i++){
                    if (arrIn[i]<arrGyu[i]) scoreGyu+=(arrIn[i]+arrGyu[i]);
                    else scoreIn+=(arrIn[i]+arrGyu[i]);
                }

                if (scoreIn<scoreGyu) gyuWinCnt++;
                else if (scoreIn>scoreGyu) gyuloseCnt++;
                else noneCnt++;
            } while (nextPerm(arrIn));

            sb.append("#").append(tc).append(" ").append(gyuWinCnt).append(" ").append(gyuloseCnt).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static boolean nextPerm(int[] arr){
        int len = arr.length;
        int i=len-1;
        while (i>0 && arr[i-1]>=arr[i]) i--;
        if (i==0) return false;

        int j=len-1;
        while (arr[i-1]>=arr[j]) j--;

        swap(arr, i-1, j);

        int k=len-1;
        while (i<k) {
            swap(arr, i, k);
            i++;
            k--;
        }

        return true;
    }

    public static void swap(int[] arr, int idx1, int idx2){
        int tmp=arr[idx1];
        arr[idx1]=arr[idx2];
        arr[idx2]=tmp;
    }
}
