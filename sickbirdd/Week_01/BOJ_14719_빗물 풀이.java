import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    int H = Integer.parseInt(st.nextToken());
	    int W = Integer.parseInt(st.nextToken());
	    
	    st = new StringTokenizer(br.readLine());
	    boolean[][] mat = new boolean[H][W];
	    for(int width = 0; width < W; width++) {
	    	int blockHeight = Integer.parseInt(st.nextToken());
	    	for(int height = H - 1; height > H - 1 - blockHeight; height--) {
	    		mat[height][width] = true;
	    	}
	    }
	    int totalRainCount = 0;
	    for(int height = H - 1; height >= 0; height--) {
	    	int startIndex = 0, rainCount = 0;
	    	while(startIndex < W && mat[height][startIndex] == false) {
	    		startIndex++;
	    	}
	    	for(int width = startIndex; width < W; width++) {
	    		if(mat[height][width] == false) rainCount++;
	    		else {
	    			totalRainCount += rainCount;
	    			rainCount = 0;
	    		}
	    	}
	    }
	    System.out.println(totalRainCount);
	}
}