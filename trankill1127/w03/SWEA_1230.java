import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class SWEA_1230 {
    //노드 클래스
    static class Node<T> {
        public T value;
        public Node<T> next;

        public Node(T value, Node<T> next){
            this.value=value;
            this.next=next;
        }

        public String toString() {
            return "Node [value: "+value+", next: "+next+"]";
        }
    }

    //링크드리스트 클래스
    static class LinkedList_kjy<T>{
        public Node<T> head = null;
        public int totCnt=0;

        public void add(T value) {
            Node<T> newNode = new Node<T>(value, null);

            if (head==null){
                head = newNode;
            }
            else {
                Node<T> wishPos=head;
                while (wishPos.next!=null) wishPos=wishPos.next;
                wishPos.next=newNode;
            }

            totCnt++;


        }

        public void addAtIdx(int idx, T value) {
            Node<T> wishPre=head;
            Node<T> wishPos=head;
            while (idx>0) {
                wishPre=wishPos;
                wishPos=wishPos.next;
                idx--;
            }
            Node<T> newNode = new Node<T>(value, null);
            newNode.next=wishPos;
            wishPre.next=newNode;

            totCnt++;

        }

        public void addFromIdx(int idx, int len, T[] cs) {
            for (int i=0; i<len; i++) {
                addAtIdx(idx+i, cs[i]);
            }
        }

        public void addMulti(int len, T[] cs) {
            for (T c : cs) {
                add(c);
            }
        }


        public void delAtIdx(int idx) {
            Node<T> wishPre=head;
            Node<T> wishPos=head;
            while (idx>0) {
                wishPos=wishPre;
                wishPos=wishPos.next;
                idx--;
            }
            wishPre.next = wishPos.next;
            wishPos.next = null;
        }

        public void delFromIdx(int idx, int len) {
            for (int i=0; i<len; i++) {
                delAtIdx(idx+i);
            }
        }

        public void showAll() {
            ArrayList<T> list = new ArrayList<>();
            Node<T> wishPos=head;
            while (wishPos!=null) {
                list.add(wishPos.value);
                wishPos=wishPos.next;
            }

            for (T t : list){
                System.out.print(t+" ");
            }
        }

    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc=1; tc<=10; tc++) {

            int n = Integer.parseInt(br.readLine());
            LinkedList_kjy<Integer> secret = new LinkedList_kjy<>();
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i=0; i<n; i++) {
                secret.add(Integer.parseInt(st.nextToken()));
            }

            int commandN = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), " ");
            for (int i=0; i<commandN; i++) {
                char c = st.nextToken().charAt(0);
                int s;
                int cnt;
                Integer[] pw;

                switch (c) {
                    case 'I':
                        s = Integer.parseInt(st.nextToken());
                        cnt = Integer.parseInt(st.nextToken());
                        pw = new Integer[cnt];
                        for (int j=0; j<cnt; j++) {
                            pw[j] = Integer.parseInt(st.nextToken());
                        }
                        secret.addFromIdx(s, cnt, pw);
                        break;
                    case 'D':
                        s = Integer.parseInt(st.nextToken());
                        cnt = Integer.parseInt(st.nextToken());
                        secret.delFromIdx(s, cnt);
                        break;
                    case 'A':
                        cnt = Integer.parseInt(st.nextToken());
                        pw = new Integer[cnt];
                        for (int j=0; j<cnt; j++) {
                            pw[j] = Integer.parseInt(st.nextToken());
                        }
                        secret.addMulti(cnt, pw);
                        break;
                }
            }

            System.out.print("#"+tc+" ");
            secret.showAll();
            System.out.println();
        }
    }
}
