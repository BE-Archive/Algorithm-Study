import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2457 {

    public static class Project implements Comparable<Project> {
        int sm;
        int sd;
        int em;
        int ed;

        Project(int sm, int sd, int em, int ed) {
            this.ed = ed;
            this.em = em;
            this.sd = sd;
            this.sm = sm;
        }

        @Override
        public int compareTo(Project o) {
            if (this.sm == o.sm) {
                if (this.sd == o.sd) {
                    if (this.em == o.em) {
                        return o.ed - this.ed;
                    }
                    return o.em - this.em;
                } else return this.sd - o.sd;
            }
            return this.sm - o.sm;
        }

        public String toString() {
            return sm + " " + sd + " ~ " + em + " " + ed;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Project[] pjt = new Project[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int sm = Integer.parseInt(st.nextToken());
            int sd = Integer.parseInt(st.nextToken());
            int em = Integer.parseInt(st.nextToken());
            int ed = Integer.parseInt(st.nextToken());
            pjt[i] = new Project(sm, sd, em, ed);
        }

        Arrays.sort(pjt);

        int cnt = 0;

        int beforeM = 3;
        int beforeD = 1;
        int beforeIdx = 0;
        boolean firstCatch=true;
        while (isBefore(beforeM, beforeD, 12, 1)) {

            boolean isPossible = false;

            //System.out.println(beforeM + " " + beforeD);

            for (int i = beforeIdx; i < pjt.length; i++) {
                if (isBefore(beforeM, beforeD, pjt[i].sm, pjt[i].sd)) //바로 전의 꽃이 진 날보다 늦게 핀다면
                    break;

                //바로 전의 꽃이 진 날보다 일찍 피는 꽃이 여러 개일 수 있다.
                //그런 꽃들 중에서 지는 날이 가장 늦은 꽃을 찾아야 한다.

                if ( firstCatch && isBefore(0, 0, pjt[i].em, pjt[i].ed)
                        || isBefore(pjt[beforeIdx].em, pjt[beforeIdx].ed, pjt[i].em, pjt[i].ed)) {
                    firstCatch=false;
                    isPossible = true;
                    beforeIdx = i;
                }
            }

            if (isPossible) { //가능한 꽃을 찾았다면
                beforeM = pjt[beforeIdx].em;
                beforeD = pjt[beforeIdx].ed;
                cnt++;
            }
            else { //가능한 꽃을 찾지 못했다면
                break;
            }
        }

        //System.out.println(beforeM+" "+beforeD);

        if (beforeM == 12) {
            System.out.println(cnt);
        } else System.out.println(0);
    }

    public static boolean isBefore(int m1, int d1, int m2, int d2) {
        if (m1 < m2) return true;
        else if (m1 == m2) {
            return d1 < d2;
        } else {
            return false;
        }
    }
}
