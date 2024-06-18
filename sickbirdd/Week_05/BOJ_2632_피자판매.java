import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		int[] AP = new int[1000001], BP = new int[1000001];
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int pizzaSize = Integer.parseInt(br.readLine()); 
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
		int[] pieceA = new int[n], pieceB = new int[m];
		int totalA = 0, totalB = 0;
		for(int i = 0; i < n; i++) {
			pieceA[i] = Integer.parseInt(br.readLine());
			totalA += pieceA[i];
		}
		
		for(int i = 0; i < m; i++) {
			pieceB[i] = Integer.parseInt(br.readLine());
			totalB += pieceB[i];
		}
		
		AP[totalA] = BP[totalB] = 1;
		
		for(int i = 0; i < n; i++) {
			int sum = 0;
			for(int j = i; j < i + n - 1; j++) {
				sum += pieceA[j % n];
				AP[sum]++;
			}
		}
		
		for(int i = 0; i < m; i++) {
			int sum = 0;
			for(int j = i; j < i + m - 1; j++) {
				sum += pieceB[j % m];
				BP[sum]++;
			}
		}
		
		int answer = 0;
		AP[0] = BP[0] = 1;
		for(int number = 0; number <= pizzaSize; number++) {
			answer += AP[number] * BP[pizzaSize - number]; 
		}
		System.out.println(answer);
	}
}