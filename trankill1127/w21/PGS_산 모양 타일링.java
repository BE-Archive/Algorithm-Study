class Solution {
    public int solution(int n, int[] tops) {
        int MOD = 10007;
        int[] notRight = new int[n+1];
        int[] right = new int[n+1];
        
        notRight[1] = (tops[0]==1) ? 3 : 2; 
        right[1] = 1;
        for (int i=2; i<n+1; i++){
            right[i]=(notRight[i-1]+right[i-1])%MOD;
            
            if  (tops[i-1]==0){
                notRight[i]=(notRight[i-1]*2+right[i-1])%MOD;   
            }
            else {
                notRight[i]=(notRight[i-1]*3+right[i-1]*2)%MOD;
            }
        }
        
        return (notRight[n]+right[n])%MOD;
    }
}
