import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_2448_별_찍기 {

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        ArrayList<StringBuilder> graph = new ArrayList<>();
        graph.add(new StringBuilder("  *  "));
        graph.add(new StringBuilder(" * * "));
        graph.add(new StringBuilder("*****"));
        int bottom = 5;

        while (N > 3) {
            int indentNum = (bottom >> 1) + 1;
            StringBuilder indent = new StringBuilder();
            while (indentNum-- > 0) {
                indent.append(' ');
            }

            ArrayList<StringBuilder> graphNew = new ArrayList<>();

            for (StringBuilder row : graph) {
                graphNew.add(new StringBuilder(indent).append(row).append(indent));
            }

            for (StringBuilder row : graph) {
                graphNew.add(new StringBuilder(row).append(' ').append(row));
            }

            graph = graphNew;
            bottom = (bottom << 1) + 1;
            N >>= 1;
        }

        StringBuilder total = new StringBuilder();
        for (StringBuilder row : graph) {
            total.append(row).append('\n');
        }

        System.out.println(total);
    }
}
