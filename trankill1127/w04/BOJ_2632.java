import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2632 {

    static int size;
    static int[] arrACnt;
    static int[] arrBCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        size = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int sizeA = Integer.parseInt(st.nextToken());
        int sizeB = Integer.parseInt(st.nextToken());
        int[] a = new int[sizeA];
        int[] b = new int[sizeB];

        int maxA=0;
        for (int i=0; i<sizeA; i++) {
            a[i]=Integer.parseInt(br.readLine());
            maxA+=a[i];
        }
        int maxB=0;
        for (int i=0; i<sizeB; i++) {
            b[i]=Integer.parseInt(br.readLine());
            maxB+=b[i];
        }

        arrACnt=new int[Math.max(size, maxA)+1]; arrACnt[0]=1; arrACnt[maxA]=1;
        arrBCnt=new int[Math.max(size, maxB)+1]; arrBCnt[0]=1; arrACnt[maxB]=1;

        cntPossible(a, arrACnt, size);
        cntPossible(b, arrBCnt, size);

        int cnt=0;
        for (int i=0; i<=size; i++){
            cnt+=arrACnt[i]*arrBCnt[size-i];
        }
        System.out.println(cnt);
    }

    public static void cntPossible(int[] arr, int[] memo, int target){
        int baseLen = arr.length;
        for (int i=0; i<baseLen; i++){ //시작 인덱스
            int sum=0;
            for (int j=1; j<baseLen; j++){ //선택하는 피자 조각
                int sum_j = arr[(i + j) % baseLen];
                if(sum + sum_j > size) break;
                sum += sum_j;
                memo[sum]++;
            }
        }
    }
}