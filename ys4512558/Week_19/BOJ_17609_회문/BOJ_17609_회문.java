import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String str = br.readLine();

            int l = 0;
            int r = str.length() - 1;
            boolean possible = true; //가능 여부
            boolean removePossible = false;
            while (l < r) {
                //같으면 스킵
                if (str.charAt(l) == str.charAt(r)) {
                    l++; r--;
                    continue;
                }
                possible = false;

                //왼쪽 삭제 || 오른쪽 삭제
                if (check(str, l, r, 1, 0) || check(str, l, r, 0, 1)) {
                    removePossible = true;
                }
                break;
            }
            sb.append(possible ? 0 : (removePossible ? 1 : 2)).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static boolean check(String str, int l, int r, int lidx, int ridx) {
        if (str.charAt(l + lidx) == str.charAt(r - ridx)) {
            int tempL = l + lidx;
            int tempR = r - ridx;
            while (tempL < tempR) {
                if(str.charAt(tempL++) == str.charAt(tempR--)) continue;
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
}