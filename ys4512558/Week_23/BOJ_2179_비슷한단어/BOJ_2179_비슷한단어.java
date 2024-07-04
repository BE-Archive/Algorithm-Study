package com.javajava.week23;

import java.io.*;
import java.util.*;

public class BOJ2179 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        //사전 순 정렬해서 하나 이전의 값과 비교해가면 접두사가 제일 긴 게 얼마인지 찾을 수 있음
        //근데 이게 하나가 아닐 수 있음 여러개 일 수 있음
        //접두사 자체도 여러개 일 수 있고 같은 접두사를 가지는 문자열도 여러개일 수 있다는 것
        PriorityQueue<String> pq = new PriorityQueue<>();

        //문자열의 인덱스를 가지는 맵
        Map<String, Integer> map = new HashMap<>();
        String[] strs = new String[N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            strs[i] = input;
            pq.offer(input);
            //중복 있으면 그냥 빼버리기 (어차피 최대이면 앞에서부터니까 뒤에 같은 문자열은 사용될일 없음)
            if(map.getOrDefault(input, -1) != -1) continue;
            map.put(input, i);
        }

        //prefix 스트링, 거기에 해당되는 인덱스들
        Map<String, List<Integer>> prefixMap = new HashMap<>();
        int max = 0;
        String pre = pq.poll();
        while (!pq.isEmpty()) {
            String current = pq.poll();
            //접두사 길이를 구한다. (길이가 같다고 무조건 같은 접두사를 가진건 아님 (aa aac != ab abc)
            int len = getLength(pre, current);
            if(len > max) {
                max = len;
                prefixMap = new HashMap<>();
                String prefix = current.substring(0, max);
                List<Integer> list = new ArrayList<>();
                //리스트에 인덱스 넣고 맵에 넣기
                list.add(map.get(pre));
                list.add(map.get(current));
                prefixMap.put(prefix, list);
            } else if (len == max) { //접두사 길이가 같으면
                //같은 접두사가 이미 있는지 확인
                String prefix = current.substring(0, max);
                List<Integer> idxList = prefixMap.get(prefix);
                //새로운 접두사일때
                if (idxList == null) {
                    List<Integer> list = new ArrayList<>();
                    //리스트에 인덱스 넣고 맵에 넣기
                    list.add(map.get(pre));
                    list.add(map.get(current));
                    prefixMap.put(prefix, list);
                } else {
                    idxList.add(map.get(current));
                }
            }
            pre = current;
        }

        int minIdx1 = Integer.MAX_VALUE;
        int minIdx2 = Integer.MAX_VALUE;

        for (String key : prefixMap.keySet()) {
            List<Integer> list = prefixMap.get(key);
            Collections.sort(list);
            int idx1 = list.get(0);
            int idx2 = list.get(1);
            if(idx1 < minIdx1){
                minIdx1 = idx1;
                minIdx2 = idx2;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(strs[minIdx1]).append("\n").append(strs[minIdx2]);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int getLength(String first, String second) {
        int cnt = 0;
        int min = Math.min(first.length(), second.length());
        for (int i = 0; i < min; i++) {
            if (first.charAt(i) != second.charAt(i)) break;
            cnt++;
        }
        return cnt;
    }
}
