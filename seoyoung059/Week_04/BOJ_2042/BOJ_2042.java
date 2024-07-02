package seoyoung059.Week_04.BOJ_2042;

import sun.util.resources.cldr.ka.CalendarData_ka_GE;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2042 {
    static class SegmentTree {
        long[] tree;
        int height;
        int nodeNum;
        int arrSize;

        public SegmentTree(int n){
            this.height = (int) Math.ceil(Math.log(n)/Math.log(2))+1;
            this.nodeNum = (int)Math.round(Math.pow(2, this.height));
            this.tree = new long[this.nodeNum];
            this.arrSize = n;
        }

        private void _constructTree(long[] arr, int low, int high, int pos){
            if(low == high) {
                this.tree[pos] = arr[low];
                return;
            }
            int mid = (low+high)/2;
            _constructTree(arr, low, mid, pos*2+1);
            _constructTree(arr, mid+1, high, pos*2+2);
            this.tree[pos] = this.tree[pos*2+1]+this.tree[pos*2+2];
        }

        public void constructTree(long[] arr){
            _constructTree(arr, 0, this.arrSize-1, 0);
        }

        private long _getRangeQuery(int qlow, int qhigh, int low, int high, int pos){
            if(qlow<=low&&high<=qhigh){
                return this.tree[pos];
            }
            else if(qhigh<low || high < qlow){
                return 0;
            }
            else {
                int mid = (low+high)/2;
                return _getRangeQuery(qlow, qhigh, low, mid, 2*pos+1) + _getRangeQuery(qlow, qhigh, mid+1, high, 2*pos+2);
            }
        }
        public long getRangeQuery(int qlow, int qhigh){
            return _getRangeQuery(qlow, qhigh, 0, this.arrSize-1, 0);
        }

        private void _update(int idx, long newValue, int low, int high, int pos){
            if(low==high){
                this.tree[pos] = newValue;
                return;
            }
            int mid = (low+high)/2;
            if(idx <= mid) _update(idx, newValue, low, mid, pos*2+1);
            else _update(idx, newValue, mid+1, high, pos*2+2);
            this.tree[pos] = this.tree[pos*2+1]+this.tree[pos*2+2];
        }
        public void update(int idx, long newValue) {
            _update(idx, newValue, 0, this.arrSize-1, 0);
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[] arr = new long[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        SegmentTree segTree = new SegmentTree(n);
        segTree.constructTree(arr);

        for (int i = 0; i < m+k; i++) {
            st = new StringTokenizer(br.readLine());
            switch(Integer.parseInt(st.nextToken())){
                case 1:
                    segTree.update(Integer.parseInt(st.nextToken())-1, Long.parseLong(st.nextToken()));
                    break;
                case 2:
                    sb.append(segTree.getRangeQuery(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1)).append("\n");
                    break;
            }
        }
        System.out.print(sb);
    }
}
