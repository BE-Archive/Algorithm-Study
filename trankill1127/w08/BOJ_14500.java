import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500 {
	
	static int[][] kind = {
			{0,0}, {0,1}, {0,2}, {0,3},
			{0,0}, {1,0}, {2,0}, {3,0},
			
			{0,0}, {1,0}, {0,1}, {1,1},

			{0,0}, {1,0}, {2,0}, {2,1},
			{0,0}, {1,0}, {2,0}, {2,-1},
			{0,0}, {1,0}, {2,0}, {0,1},
			{0,0}, {0,1}, {1,1}, {2,1},
			{0,0}, {0,1}, {0,2}, {-1,2},
			{0,0}, {0,1}, {0,2}, {1,2},
			{0,0}, {1,0}, {1,1}, {1,2},
			{0,0}, {0,1}, {0,2}, {1,0},

			{0,0}, {1,0}, {1,1}, {2,1},
			{0,0}, {1,0}, {0,1}, {-1,1},
			{0,0}, {0,1}, {-1,1}, {-1,2},
			{0,0}, {0,1}, {1,1}, {1,2},

			{0,0}, {0,1}, {0,2}, {1,1},
			{0,0}, {0,1}, {0,2}, {-1,1},
			{0,0}, {1,0}, {2,0}, {1,1},
			{0,0}, {1,0}, {2,0}, {1,-1}
		};


	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][m];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j=0; j<m; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		int maxTot = 0;
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				
				for (int k=0; k<kind.length; k+=4) {
					int tot=0;
					for (int b=0; b<4; b++){
						int nextX = i+kind[k+b][0];
						int nextY = j+kind[k+b][1];
						
						if (nextX<0||nextX>=n||nextY<0||nextY>=m) {
							tot=0;
							break;
						}
						tot+=map[nextX][nextY];
            
          }
					if (tot>maxTot) maxTot=tot;
          
				}
			}	
		}
		System.out.println(maxTot);
	}
}
