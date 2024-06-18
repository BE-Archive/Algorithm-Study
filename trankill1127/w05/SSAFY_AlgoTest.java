import java.util.Arrays;

public class SSAFY_AlgoTest {

    public static void main(String[] args) {

        len=5;
        number = new int[len];
        for (int i=0; i<len; i++){
            number[i]=i+1;
        }
        visited = new int[len];

        targetLen=3;
        target = new int[targetLen];
        //permutation(0);
        //permutationDuplicate(0);
        //combination(0, 0);
        //powerSet(0);
        //powerSet_BinCnt();

//        do {
//            System.out.println(Arrays.toString(number));
//        } while (nextPermutation());

//        targetLen=3;
//        for (int i=targetLen; i<len; i++) {
//            visited[i]=1;
//        }
//        do {
//            for (int i=0; i<len; i++) {
//                if (visited[i]==0) System.out.print(number[i]+" ");
//            }
//            System.out.println();
//        } while (nextCombination());


        set(5);
        union(1,2);
        union(5,1);
        union(3,4);
        System.out.println(Arrays.toString(parent));

    }

    public static int len;
    public static int[] number;
    public static int targetLen;
    public static int[] target;
    public static int[] visited;

    public static void permutation(int idx){
        if (idx==targetLen){
            System.out.println(Arrays.toString(target));
            return;
        }

        for (int i=0; i<len; i++){
            if (visited[i]==0){
                target[idx]=number[i];
                visited[i]=1;
                permutation(idx+1);
                visited[i]=0;
            }
        }
    }

    public static boolean nextPermutation(){
        //위치를 찾는다.
        int i=len-1;
        while (number[i-1]>=number[i]) {
            i--;
            if (i==0) return false;
        }

        //위치의 오른쪽에서 제일 큰 애를 찾는다.
        int j=len-1;
        while (number[i-1]>=number[j]) j--;

        swap(i-1,j);

        Arrays.sort(number, i, len);
        return true;
    }

    public static void swap(int idx1, int idx2){
        int tmp = number[idx1];
        number[idx1]=number[idx2];
        number[idx2]=tmp;
    }

    public static boolean nextCombination(){
        //위치를 찾는다.
        int i=len-1;
        while (visited[i-1]>=visited[i]) {
            i--;
            if (i==0) return false;
        }

        //위치의 오른쪽에서 제일 큰 애를 찾는다.
        int j=len-1;
        while (visited[i-1]>=visited[j]) j--;

        visitedSwap(i-1,j);

        Arrays.sort(visited, i, len);
        return true;
    }

    public static void visitedSwap(int idx1, int idx2){
        int tmp = visited[idx1];
        visited[idx1]=visited[idx2];
        visited[idx2]=tmp;
    }

    public static void permutationDuplicate(int idx){
        if (idx==targetLen){
            System.out.println(Arrays.toString(target));
            return;
        }

        for (int i=0; i<len; i++) {
            target[idx] = number[i];
            permutationDuplicate(idx + 1);
        }
    }

    public static void combination(int idx, int start) {
        if (idx==targetLen){
            System.out.println(Arrays.toString(target));
            return;
        }

        for (int i=start; i<len; i++){
            target[idx]=number[i];
            combination(idx+1, i+1);
        }
    }

    public static void powerSet(int idx) {
        if (idx==len){
            for (int i=0; i<idx; i++){
                if (visited[i]==1) System.out.print(number[i]+" ");
            }
            System.out.println();
            return;
        }

        visited[idx]=1;
        powerSet(idx+1);
        visited[idx]=0;
        powerSet(idx+1);
    }

    public static void powerSet_BinCnt() {
        for (int i=0; i<(1<<len); i++){
            for (int j=0; j<len; j++){
                if ((i & 1<<j)==0) continue;
                System.out.print(number[j]+" ");
            }
            System.out.println();
        }
    }

    public static int[] parent;
    public static void set(int size){
        parent = new int[size+1];
        for (int i=1; i<=size; i++){
            parent[i]=i;
        }
    }
    public static void union(int v1, int v2){
        int p1 = find(v1);
        int p2 = find(v2);

        if (p1==p2) return;
        else if (p1<p2) parent[p2]=p1;
        else parent[p1]=p2;

    }
    public static int find(int x){
        if (parent[x]==x) return x;
        return parent[x] = find(parent[x]);
    }


}
