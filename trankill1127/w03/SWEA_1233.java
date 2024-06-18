import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_1233 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int tc=1; tc<=10; tc++) {

            st = new StringTokenizer(br.readLine(), " ");
            Integer n = Integer.parseInt(st.nextToken());

            ArrayList<String> tree = new ArrayList<>();
            tree.add("*");

            int result=1;
            for (int i=1; i<=n; i++) {

                if (result == 0) {
                    br.readLine();
                    continue;
                }

                st = new StringTokenizer(br.readLine(), " ");
                i = Integer.parseInt(st.nextToken());
                tree.add(st.nextToken());
                while (true) {
                    try {
                        st.nextToken();
                    } catch (Exception e) {
                        break;
                    }
                }
                if (!(tree.get(i / 2).equals("+") || tree.get(i / 2).equals("-")
                        || tree.get(i / 2).equals("*") || tree.get(i / 2).equals("/"))) {
                    result = 0;
                }
            }

            System.out.println("#"+tc+" "+result);
        }
    }
}
