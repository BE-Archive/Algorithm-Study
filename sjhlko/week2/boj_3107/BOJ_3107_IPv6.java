package sjhlko.week2.boj_3107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_3107_IPv6 {
    //https://www.acmicpc.net/problem/3107
    //IPv6
    static char[][] ip = new char[8][4];
    static String from;
    static int colonCount;

    static void init() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                ip[i][j] = '0';
            }
        }
    }

    static void setColonCount() {
        for (int i = 0; i < from.length(); i++) {
            if (from.charAt(i) == ':') colonCount++;
        }
    }

    static void solution() {
        int groupIndex = 7;
        int partIndex = 3;
        for (int i = from.length() - 1; i >= 0; i--) {
            if (from.charAt(i) == ':') {
                colonCount--;
                if (colonCount == 0) {
                    groupIndex = 0;
                    partIndex = 3;
                    continue;
                }
                if (i != 0 && from.charAt(i - 1) == ':') {
                    groupIndex = colonCount - 1;
                    partIndex = 3;
                    continue;
                }
                if (partIndex != 3) groupIndex--;
                partIndex = 3;
                continue;
            }
            ip[groupIndex][partIndex] = from.charAt(i);
            partIndex = partIndex == 0 ? 3 : partIndex - 1;
            groupIndex = partIndex == 3 ? groupIndex - 1 : groupIndex;
        }
    }

    static void printAnswer() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                sb.append(ip[i][j]);
            }
            if (i != 7) sb.append(":");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        from = bf.readLine();
        init();
        setColonCount();
        solution();
        printAnswer();
    }
}
