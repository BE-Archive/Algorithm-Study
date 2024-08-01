package trankill1127.w28;

import java.io.*;
import java.util.*;

public class BOJ_1254 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine().trim();

	}
}

/*
8:40~9:10

문자열 s가 있음 (~50)
여기에 0개 이상을 추가해서 팰린드롬 만들기!!!

가장 짧은 팰린드롬의 길이를 출력하라

가장 이상적인 경우는 0개 추가(이미 팰린드롬)
최악의 경우는 50개 추가(걍 다 다른 경우)

이미 있는 애들이 팰린드롬 쌉가능인지 아닌지 판단하려면 어떻게 해야할까?

s에서 먼저 대칭점을 찾아...
첫번째 기준 1(+1), 2(+1)
두번째 기준 1(+4), 3(), 5(+4)
세번째 기준 X > 6개니까 (6-1)*2+1
네번째 기준 9, l있는데... 흠냐
*/