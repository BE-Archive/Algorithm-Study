import java.util.*;
import java.io.*;

class B15927_회문은회문이아니야 
{
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Set<Character> set = new HashSet<>();
		char[] input = br.readLine().toCharArray();

		int answer = input.length-1;
		for(int i=0; i<input.length; ++i)
		{
			set.add(input[i]);
			if(input[i] != input[input.length-1-i])
			{
				++answer;
				break;
			}
		}
		
		if(answer == input.length-1 && set.size() == 1) answer = -1;
		
		System.out.print(answer);
	}
}
