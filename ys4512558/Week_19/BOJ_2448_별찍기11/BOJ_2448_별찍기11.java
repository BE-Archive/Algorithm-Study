package com.javajava.week19;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ2448 {
    static char[][] map;
    static char[][] triangle = {
        {' ', ' ', '*', ' ', ' '},
        {' ', '*', ' ', '*', ' '},
        {'*', '*', '*', '*', '*'}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        //3 * 2^10 = 1024 * 3 = 3072 (행)
        //3 * 2^10 * 2 - 6144
        map = new char[3073][6144];
        int depth = N / 3;
        draw(depth, 0, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2 * N ; j++) {
                sb.append(map[i][j] == '*' ? '*' : ' ');
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


    //depth = 2^n 꼴 8 = 2^3 -> 3번씩 재귀 log2(n)깊이만큼 수행해야 한다.
    private static void draw(int depth, int x, int y) {
        if (depth == 1){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++) {
                    map[x + i][y + j] = triangle[i][j];
                }
            }
            return;
        }
        draw(depth / 2, x, y + 3 * depth / 2);
        draw(depth / 2, x + 3 * depth / 2, y);
        draw(depth / 2, x + 3 * depth / 2, y + 3 * depth);
    }
}