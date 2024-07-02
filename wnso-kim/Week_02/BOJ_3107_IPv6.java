import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split("::");

		if(input.length==2) {
			String[] left = input[0].split(":");
			String[] right = input[1].split(":");
			int sizeOfZero = 8-left.length-right.length;
			
			demux(left);
			for(int i=0; i<sizeOfZero;i++) result.append("0000:");
			demux(right);

		} else if(input.length==1) {
			String[] split = input[0].split(":");
			demux(split);
			
			if(split.length<8) {
				int sizeOfZero = 8 - split.length;
				for(int i=0; i<sizeOfZero;i++) result.append("0000:");
			}
		} else{
			for(int i=0; i<8;i++) result.append("0000:");
		}
		
		result.deleteCharAt(result.length()-1);
        System.out.println(result);
	}
	
	static void demux(String[] strs) {
		for(String str: strs) {
			int zero = 4-str.length();
			
			for(int i=0; i<zero; i++)
				str = "0"+str;
			
			result.append(str+":");
		}
	}
}