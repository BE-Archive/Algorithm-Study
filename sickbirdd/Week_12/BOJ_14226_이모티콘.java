import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	final static int INF = (int) 2e9;
	
	static class State {
		int emoji, clip;
		State(int emoji, int clip) {
			this.emoji = emoji;
			this.clip = clip;
		}
	}
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int S = Integer.parseInt(br.readLine());
    	int[][] cnt = new int[S + 1][S + 1];
        for(int i = 0; i <= S; i++) {
        	Arrays.fill(cnt[i], INF);
        }
        
        Queue<State> queue = new ArrayDeque<>();
        cnt[1][0] = 0;
        queue.add(new State(1, 0));
        int answer = INF;
        while(!queue.isEmpty()) {
        	State curr = queue.poll();
        	if(cnt[curr.emoji][curr.clip] >= answer) continue; 
        	if(curr.emoji == S) {
        		answer = Math.min(answer, cnt[curr.emoji][curr.clip]);
        		continue;
        	}
        	
        	if(0 < curr.clip && curr.emoji + curr.clip <= S && cnt[curr.emoji][curr.clip] + 1 < cnt[curr.emoji + curr.clip][curr.clip]) {
        		cnt[curr.emoji + curr.clip][curr.clip] = cnt[curr.emoji][curr.clip] + 1;
        		queue.add(new State(curr.emoji + curr.clip, curr.clip));
        	}
        	
        	if(0 < curr.emoji && curr.emoji <= S && cnt[curr.emoji][curr.clip] + 1 < cnt[curr.emoji][curr.emoji]) {
        		cnt[curr.emoji][curr.emoji] = cnt[curr.emoji][curr.clip] + 1;
        		queue.add(new State(curr.emoji, curr.emoji));
        	}
        	
        	if(0 < curr.emoji && cnt[curr.emoji][curr.clip] + 1 < cnt[curr.emoji - 1][curr.clip]) {
        		cnt[curr.emoji - 1][curr.clip] = cnt[curr.emoji][curr.clip] + 1;
        		queue.add(new State(curr.emoji - 1, curr.clip));
        	}
        }
        
        System.out.println(answer);
        
    }
}