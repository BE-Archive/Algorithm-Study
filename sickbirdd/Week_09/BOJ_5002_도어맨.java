import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int X = Integer.parseInt(br.readLine()), difference = 0, index;
		String s = br.readLine();
		for(index = 0; index < s.length(); index++) {
			char a = s.charAt(index), b = (index < s.length() - 1) ? s.charAt(index + 1) : a;
			if(a != b) {
				index++;
				continue;
			}
			difference += ((a == 'M') ? 1 : -1);
			if(Math.abs(difference) > X) {
				break;
			}
		}
		System.out.println(index);
	}
}