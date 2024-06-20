import java.util.Arrays;

public class ForA {

    public static void main(String[] args) {
        number = new int[]{0,1,2,3,4,5};
        numLen=number.length;
        Arrays.sort(number);

        parent = new int[]{0,1,2,3,4,5};
        union(1,4);
        union(3,5);
        union(1,2);

        System.out.println(Arrays.toString(parent));
        if (find(5)==find(4)) System.out.println("in the same group");
        else System.out.println("in different groups");
    }

    public static int[] number;
    public static int[] parent;
    public static int numLen;

    public static void union(int x1, int x2){
        int p1=find(x1);
        int p2=find(x2);

        if (p1==p2) return;
        else if (p1<p2) parent[p2]=p1;
        else parent[p1]=p2;
    }
    public static int find(int x){
        if (parent[x]==x) return x;
        else return parent[x]=find(parent[x]);
    }
}

/*
    public static int[] number;
    public static int numLen;
    public static int[] visited;
    public static int[] want;
    public static int wantLen;
    public static List<String> result;

    public static void main(String[] args) {
        number = new int[]{1,2,3,4,5};
        numLen=number.length;

        visited = new int[numLen];

        wantLen = 3;
        want = new int[wantLen];

        result= new ArrayList<>();
        permutation(0);
        for (String s : result) System.out.println(s);
    }

    public static void permutation(int idx){
        if (idx==wantLen){
            result.add(Arrays.toString(want));
            return;
        }

        for (int i=0; i<numLen; i++){
            if (visited[i]==1) continue;
            visited[i]=1;
            want[idx]=number[i];
            permutation(idx+1);
            visited[i]=0;
        }
    }
 */

/*
    public static int[] number;
    public static int numLen;
    public static int[] want;
    public static int wantLen;
    public static List<String> result;

    public static void main(String[] args) {
        number = new int[]{1,2,3,4,5};
        numLen=number.length;

        wantLen = 3;
        want = new int[wantLen];

        result= new ArrayList<>();
        combination(0, 0);
        for (String s : result) System.out.println(s);
    }

    public static void combination(int idx, int s){
        if (idx==wantLen){
            result.add(Arrays.toString(want));
            return;
        }

        for (int i=s; i<numLen; i++){
            want[idx]=number[i];
            combination(idx+1,i);
        }
    }
 */

/*
    public static int[] number;
    public static int numLen;
    public static List<String> result;

    public static void main(String[] args) {
        number = new int[]{1,2,3,4,5};
        numLen=number.length;

        result= new ArrayList<>();
        powerSet();
        for (String s : result) System.out.println(s);
    }

    public static void powerSet(){
        int totCase=1<<numLen;
        for (int i=0; i<totCase ; i++){
            StringBuilder s= new StringBuilder();
            for (int j=0; j<numLen; j++){
                if (  (i & (1<<j)) != 0 ){
                    s.append(number[j]).append(" ");
                }
            }
            result.add(s.toString());
        }
    }
 */

/*
    public static int[] number;
    public static int numLen;

    public static void main(String[] args) {
        number = new int[]{3,2,1,4,5};
        numLen=number.length;
        Arrays.sort(number);

        do {
            System.out.println(Arrays.toString(number));
        } while (nextPermutation());
    }

    public static boolean nextPermutation(){
        int i=numLen-1;
        while (number[i-1]>=number[i]) {
            i--;
            if (i==0) return false;
        }

        int j=numLen-1;
        while (number[i-1]>=number[j]) j--;

        swap(i-1, j);

        Arrays.sort(number, i, numLen);
        return true;
    }

    public static void swap(int idx1, int idx2){
        int tmp=number[idx1];
        number[idx1]=number[idx2];
        number[idx2]=tmp;
    }
 */