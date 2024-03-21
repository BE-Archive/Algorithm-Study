import java.io.*;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int X = Integer.parseInt(br.readLine());
        String input = br.readLine();

        ArrayDeque<Character> deque = new ArrayDeque<>();
        for (int i = 0; i < input.length(); i++) {
            deque.offerLast(input.charAt(i));
        }
        int man = 0;
        int woman = 0;
        while (!deque.isEmpty()) {
            char gender = deque.poll();
            if (gender == 'M') {
                man++;
                if (Math.abs(man - woman) > X && !deque.isEmpty()) {
                    char next = deque.peek();
                    man--;
                    if(next == 'W'){
                        deque.poll();
                        deque.offerFirst(gender);
                        woman++;
                    } else {
                        break;
                    }
                } else if (Math.abs(man - woman) > X && deque.isEmpty()) {
                    man--;
                    break;
                }
            } else {
                woman++;
                if (Math.abs(man - woman) > X && !deque.isEmpty()) {
                    char next = deque.peek();
                    woman--;
                    if(next == 'M'){
                        deque.poll();
                        deque.offerFirst(gender);
                        man++;
                    } else {
                        break;
                    }
                } else if (Math.abs(man - woman) > X && deque.isEmpty()) {
                    woman--;
                    break;
                }
            }

        }
        StringBuilder sb = new StringBuilder();
        sb.append(man + woman);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}