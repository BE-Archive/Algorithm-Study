package Week_38.BOJ_1063;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_1063 {

    static boolean isValid(int y, int x) {
        return 0 <= y && y < 8 && 0 <= x && x < 8;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int ky, kx, sy, sx;

        String tmp = st.nextToken();
        ky = 7 - (tmp.charAt(1) - '1');
        kx = tmp.charAt(0) - 'A';

        tmp = st.nextToken();
        sy = 7 - (tmp.charAt(1) - '1');
        sx = tmp.charAt(0) - 'A';

        int n = Integer.parseInt(st.nextToken());
        Map<String, int[]> movement = new HashMap<>();
        movement.put("R", new int[]{0, 1});
        movement.put("L", new int[]{0, -1});
        movement.put("B", new int[]{1, 0});
        movement.put("T", new int[]{-1, 0});
        movement.put("RT", new int[]{-1, 1});
        movement.put("LT", new int[]{-1, -1});
        movement.put("RB", new int[]{1, 1});
        movement.put("LB", new int[]{1, -1});
        int[] nextMove;
        int nky, nkx, nsy, nsx;
        for (int i = 0; i < n; i++) {
            nextMove = movement.get(br.readLine());
            nky = ky + nextMove[0];
            nkx = kx + nextMove[1];
            nsy = sy + nextMove[0];
            nsx = sx + nextMove[1];

            if (!isValid(nky, nkx)) {
                continue;
            }

            if (nky == sy && nkx == sx) {
                if (!isValid(nsy, nsx)) {
                    continue;
                }
                sy = nsy;
                sx = nsx;
            }

            ky = nky;
            kx = nkx;

        }

        StringBuilder sb = new StringBuilder();
        sb.append((char) (kx + 'A')).append(8 - ky).append('\n');
        sb.append((char) (sx + 'A')).append(8 - sy).append('\n');

        System.out.println(sb);
    }
}

