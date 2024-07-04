package com.javajava.week05;

import java.io.*;
import java.util.*;

public class BOJ2632 {
    static int N, count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[] pizza1 = new int[A];
        int[] pizza2 = new int[B];
        int sum = 0;
        for (int i = 0; i < A; i++) {
            pizza1[i] = Integer.parseInt(br.readLine());
            sum += pizza1[i];
        }
        list1.add(sum);

        sum = 0;
        for (int i = 0; i < B; i++) {
            pizza2[i] = Integer.parseInt(br.readLine());
            sum += pizza2[i];
        }
        list2.add(sum);

        list1.add(0);
        list2.add(0);

        addList(pizza1, A, list1);
        addList(pizza2, B, list2);

        Collections.sort(list1);
        Collections.sort(list2);

        for (int i = 0; i < list1.size(); i++) {
            int first = list1.get(i);
            if(first == N){
                count++;
                continue;
            }
            if(first > N) break;
            if(first <= N){
                int idx = lowerBound(0, list2.size(), N - first, list2);
                for (int j = idx; j < list2.size(); j++) {
                    int second = list2.get(j);
                    if (first + second == N) {
                        count++;
                    }
                    if(first + second > N) break;
                }
            }
        }
        
    }

    private static int lowerBound(int start, int end, int value, ArrayList<Integer> list) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (list.get(mid) < value) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return end;
    }
    private static void addList(int[] pizza, int len, List<Integer> list){
        for (int i = 0; i < len; i++) {
            list.add(pizza[i]);
            int sum = pizza[i];
            for (int j = i + 1; j < len + i - 1; j++) {
                sum += pizza[j % len];
                list.add(sum);
            }
        }
    }
}
