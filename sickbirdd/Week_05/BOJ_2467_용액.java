import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int N = Integer.parseInt(br.readLine());
	    int[] acid = new int[N], alkali = new int[N];
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    int acidLen = 0, alkaliLen = 0;
	    for(int i = 0; i < N; i++) {
	        int solution = Integer.parseInt(st.nextToken());
	        if(solution < 0) alkali[alkaliLen++] = solution;
	        else acid[acidLen++] = solution;
	    }
	    
	    int minValue = (int) 2e9;
	    int[] answer = new int[2];
	    
	    if(acidLen > 1) {
	        int acidValue = acid[0] + acid[1];
	        if(minValue > acidValue) {
	            minValue = acidValue;
	            answer[0] = acid[0];
	            answer[1] = acid[1];
	        }
	    }
	    
	    else if(alkaliLen > 1) {
	        int alkaliValue = Math.abs(alkali[alkaliLen - 1] + alkali[alkaliLen - 2]);
	        if(minValue > alkaliValue) {
	            minValue = alkaliValue;
	            answer[0] = alkali[alkaliLen - 2];
	            answer[1] = alkali[alkaliLen - 1];
	        }
	    }
	    
	    int acidPointer = 0, alkaliPointer = alkaliLen - 1;
	    while(acidPointer < acidLen && alkaliPointer >= 0) {
	    	int acidValue = acid[acidPointer], alkaliValue = alkali[alkaliPointer];
	    	
	    	if(minValue > Math.abs(acidValue + alkaliValue)) {
	    		minValue = Math.abs(acidValue + alkaliValue);
	    		answer[0] = alkaliValue;
	    		answer[1] = acidValue;
	    	}
	    	
	    	if(acidValue + alkaliValue < 0) {
	    		acidPointer++;
	    	}
	    		
	    	else if(acidValue + alkaliValue > 0) {
	    		alkaliPointer--;
	    	}
	    	
	    	else break;
	    }
	    
	    StringBuilder sb = new StringBuilder();
	    sb.append(answer[0]).append(' ').append(answer[1]);
	    System.out.println(sb);
	}
}