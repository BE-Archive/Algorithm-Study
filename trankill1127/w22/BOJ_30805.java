import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_30805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        List<int[]> A = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            A.add(new int[]{i, Integer.parseInt(st.nextToken())});
        }
        int m = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());
        List<int[]> B = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            B.add(new int[]{i, Integer.parseInt(st.nextToken())});
        }

        Comparator<int[]> comp = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o2[1]==o1[1]){
                    return o1[0]-o2[0];
                }
                return o2[1]-o1[1];
            }
        };
        A.sort(comp);
        B.sort(comp);

        List<Integer> answer = new ArrayList<>();
        int idxA=0, idxB=0, realIdxA= -1, realIdxB=-1;
        while (idxA<n && idxB<m){
            if (A.get(idxA)[1]==B.get(idxB)[1]){
                if (realIdxA<A.get(idxA)[0] && realIdxB<B.get(idxB)[0]){
                    answer.add(A.get(idxA)[1]);
                    realIdxA=A.get(idxA)[0]; realIdxB=B.get(idxB)[0];
                    idxA++; idxB++;
                }
                else {
                    if (realIdxA>A.get(idxA)[0]) idxA++;
                    if (realIdxB>B.get(idxB)[0]) idxB++;
                }
            }
            else if (A.get(idxA)[1]<B.get(idxB)[1]) idxB++;
            else if (A.get(idxA)[1]>B.get(idxB)[1]) idxA++;
        }

        System.out.println(answer.size());
        for (Integer a: answer){
            System.out.print(a+" ");
        }
    }
}
