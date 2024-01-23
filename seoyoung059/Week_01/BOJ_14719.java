package seoyoung059.Week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class BOJ_14719 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;

    // solution1에 쓰이는 a와 b 사이 고이는 물의 양을 구하는 함수
    private static int sumUp(int[] a, int[] b, int[] arr){
            return Math.min(a[0],b[0])*(b[1]-a[1]-1)-(arr[b[1]-1]-arr[a[1]]);
        }

    //TreeSet 이용 풀이
    private static void solution1(int w) throws IOException {
	    // 높은 블록부터 확인할 수 있도록 TreeSet을 이용한다.
	    // 정렬은 블록의 높이를 내림차순으로, 블록의 위치(index)를 오름차순으로 정렬했다.
        TreeSet<int[]> ts = new TreeSet<>((o1,o2)->{
            if (o1[0]==o2[0]) return o1[1]-o2[1];
            return o2[0]-o1[0];
        });


		// sum을 쉽게 구하기 위해, TreeSet에 입력을 받으면서 누적합 배열 arr을 완성한다.
        int[] arr = new int[w];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++) {
            int[] tmp = {Integer.parseInt(st.nextToken()), i};
            ts.add(tmp);
            if (i==0){
                arr[0] = tmp[0];
                continue;
            }
            arr[i] = arr[i-1]+tmp[0];
        }

		// 가장 높은 블럭 a부터 시작한다.
		// sum은 s와 e 사이에 고인 빗물의 양이다.
        int[] a = ts.pollFirst();
        int[] s = a;
        int[] e = a;
        int sum=0;
        int[] tmp;
        // s가 0, e가 w-1이 되어 sum이 전체 구간의 빗물의 양이 되면 반복문이 종료된다.
        while(s[1]!=0 || e[1]!=w-1){
            tmp = ts.pollFirst();
            // tmp가 s보다 왼쪽에 있는 블럭의 정보면
            // 1. tmp와 s 사이에 고인 물의 양을 구해 sum에 더하고
            // 2. s를 업데이트한다.
            if(tmp[1]<s[1]){
                sum+=sumUp(tmp,s,arr);
                s = tmp;
            }
            // tmp가 e보다 오른쪽에 있는 블럭의 정보면
            // 1. e와 tmp 사이에 고인 물의 양을 구해 sum에 더하고
            // 2. e를 업데이트한다.
            else if(e[1]<tmp[1]){
                sum+=sumUp(e,tmp,arr);
                e = tmp;
            }
            // 그 외 s와 e 사이의 블럭이라면 이미 고인 빗물을 구한 구간이므로 무시한다.
        }
        System.out.println(sum);
    }

  // 투포인터? 이용 풀이
    private static void solution2(int w) throws IOException{
	    // 블럭의 높이와 왼쪽 블록 중 제일 높은 블럭의 높이를 저장할 2차원 배열
        int[][] arr = new int[w][2];
        int max;
    
	    // (좌->우 진행) 블럭 높이 입력 및 왼쪽 블럭 중 제일 높은 높이를 구함
        st = new StringTokenizer(br.readLine());
        arr[0][0] = Integer.parseInt(st.nextToken());
        arr[0][1] = 0;
        max = arr[0][0];
        for (int i = 1; i < w; i++) {
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = max;
            max = Math.max(max,arr[i][0]);
        }
	
		// (우->좌 진행) 오른쪽 블럭들 중 가장 높은 높이를 구하고, 각 블럭에 고이는 물의 양을 sum에 더해 답을 구함
        int sum = 0;
        max = arr[w-1][0];
        for (int i = w-2; i >= 0; i--) {
            sum+=Math.max(0, Math.min(arr[i][1],max)-arr[i][0]);
            max = Math.max(arr[i][0], max);
        }
        System.out.println(sum);
    }


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

//        solution1(w);
        solution2(w);
    }
}

