package sjhlko.week3.BOJ_2750;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_2750_수정렬하기_퀵정렬 {
    static int N;
    static List<Integer> list;
    static List<Integer> answer = new ArrayList<>();

    static List<Integer> quickSort(List<Integer> unSorted) {
        if (unSorted.size() <= 1) return unSorted;
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int pivot = unSorted.get(0);
        for (int i = 1; i< unSorted.size(); i++) {
            if (unSorted.get(i) <= pivot) left.add(unSorted.get(i));
            else right.add(unSorted.get(i));
        }
        List<Integer> ret = new ArrayList<>(quickSort(left));
        ret.add(pivot);
        ret.addAll(quickSort(right));
        return ret;
    }

    static void printAns() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(answer.get(i)).append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(bf.readLine()));
        }
        answer = quickSort(list);
        printAns();
    }
}
