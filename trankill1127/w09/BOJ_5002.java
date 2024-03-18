import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ_5002 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int maxDiff = Integer.parseInt(br.readLine());
        char[] line = br.readLine().toCharArray();

        Map<Character, Integer> map = new HashMap<>();
        map.put('M', 0); map.put('W', 0);

        for (int i=0; i<line.length; i++){

            if (line[i]=='M'){
                if ( Math.abs(map.get('M')+1-map.get('W')) > maxDiff ){
                    if (i+1>=line.length) break;
                    if (line[i+1]==line[i]) break;

                    char tmp=line[i+1];
                    line[i+1]=line[i];
                    line[i]=tmp;
                }
            }
            else {
                if ( Math.abs(map.get('M')-map.get('W')-1) > maxDiff ){
                    if (i+1>=line.length) break;
                    if (line[i+1]==line[i]) break;

                    char tmp=line[i+1];
                    line[i+1]=line[i];
                    line[i]=tmp;
                }
            }

            map.put(line[i], map.get(line[i])+1);
        }

        System.out.println(map.get('M')+map.get('W'));
    }
}