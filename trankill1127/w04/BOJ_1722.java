import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1722 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken()); // 1, 2, 3, ... n
        int[] number = new int[n];
        for (int i=0; i<n; i++) {
            number[i]=i+1;
        }

        st = new StringTokenizer(br.readLine(), " ");
        int kind = Integer.parseInt(st.nextToken());
        if (kind==1) { //1 : 입력받은 숫자번째 순열 출력하기
            int target = Integer.parseInt(st.nextToken());
            do {
                target--;
                if (target==0) break;
            } while (nextPerm(number));
            for (int nn: number) {
                sb.append(nn).append(" ");
            }
        }
        else { //2 : 입력받은 순열이 몇번째 순열인지 출력하기
            //System.out.println(st.countTokens());
            int len = st.countTokens();
            int[] targetPerm = new int[len];
            for (int i=0; i<len; i++) targetPerm[i]=Integer.parseInt(st.nextToken());
            int trialCnt=0;
            do {
                trialCnt++;
                int i=0;
                while (i<n && number[i]==targetPerm[i]) i++;
                if (i==n) break;

            } while (nextPerm(number));
            System.out.println(trialCnt);
        }

        System.out.println(sb.toString());
    }

    public static boolean nextPerm(int[] target) {
        int len = target.length;

        int i=len-1;
        while (i>0 && target[i-1]>=target[i]) i--;
        if (i==0) return false;

        int j=len-1;
        while (target[i-1]>=target[j]) j--;

        swap(target, i-1, j);

        int k=len-1;
        while (i<k) swap(target, i++, k--);

        return true;
    }
    public static void swap(int[] target, int idx1, int idx2) {
        int tmp=target[idx1];
        target[idx1]=target[idx2];
        target[idx2]=tmp;
    }

}