import java.io.*;

public class Main {
	
	static int getAnswer(char[] line, final int X)
	{
		int answer = 0;
		int cnt[]  = new int[2];
		int type = 0, tmp = 0;
		
		for (int i = 0; i < line.length; ++i) {
			
			type = (line[i] == 'W' ? 1 : 0);
			tmp = (type == 1 ? 0 : 1);
			
			if(Math.abs(cnt[type] + 1 - cnt[tmp]) <= X)
			{
				++answer;
				++cnt[type];
			}
			else if (i+1 < line.length && line[i+1] != line[i])
			{
				// 다음 사람이라도 입장 시킬 수 있는지 확인하는 로직
				++answer;
				++cnt[tmp];
				line[i+1] = line[i];		
			} 
			else
				break;
		}

		return answer;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int X = Integer.parseInt(br.readLine());
		char[] line = br.readLine().toCharArray();
		
		System.out.print(getAnswer(line, X));
	}
}
