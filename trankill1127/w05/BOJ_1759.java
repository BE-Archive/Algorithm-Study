import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759 {

    public static int L;
    public static int C;
    public static char[] chars;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        chars = new char[C];
        for (int i = 0; i < C; i++) {
            chars[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(chars);
        used = new boolean[C];
        permStore = new char[L];

        permutation(0);
    }

    public static boolean[] used;
    public static char[] permStore;

    public static void permutation(int len) {
        if (len == L) {
            if (isRight()) {
                for (char ps : permStore) System.out.print(ps);
                System.out.println();
            }
            return;
        }

        for (int i=0; i<C; i++){
            if (used[i]) continue;
            if (len>0 && permStore[len-1]>chars[i]) continue;

//            System.out.println(Arrays.toString(permStore));
//            System.out.println("뭐가 문제임-----");

            permStore[len]=chars[i];
            used[i]=true;
            permutation(len+1);
            used[i]=false;
        }
    }

    public static boolean isRight() {
        int jaCnt = 0;
        int moCnt = 0;
        for (int i = 0; i < C; i++) {
            if (!used[i]) continue;
            if (chars[i] == 'a' || chars[i] == 'e' || chars[i] == 'i' || chars[i] == 'o' || chars[i] == 'u') {
                jaCnt++;
            } else moCnt++;
        }

        if (jaCnt >= 1 && moCnt >= 2) {
            return true;
        }
        return false;
    }
}
