import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static int[] A;

    public static boolean isGood(int index) {
        int target = A[index];
        int left = 0;
        int right = N - 1;

        while (left < right) {
            if (left == index) {
                left++;
                continue;
            }
            if (right == index) {
                right--;
                continue;
            }

            int sum = A[left] + A[right];
            if (sum == target) {
                return true;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {


            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            StringBuilder sb = new StringBuilder();

            N = Integer.parseInt(st.nextToken());
            A = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int n = 0; n < N; n++) {
                int Ai = Integer.parseInt(st.nextToken());
                A[n] = Ai;
            }

            Arrays.sort(A);
            //////////////////////////////////////////////////

            int answer = 0;

            for (int index = 0; index < N; index++) {
                if (isGood(index)) {
                    answer++;
                }
            }
            System.out.println(answer);
        }
    }

