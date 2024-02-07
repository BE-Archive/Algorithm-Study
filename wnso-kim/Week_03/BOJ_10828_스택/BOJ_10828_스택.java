import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static class Stack_{
        int[] area;
        int size;

        Stack_(){
            area = new int[10000];
            size = 0;
        }

        void push(int x){
            area[size++] = x;
        }

        int pop(){
            if(size == 0)
                return -1;

            return area[--size];
        }

        int size(){
            return size;
        }

        int empty(){
            if(size == 0)
                return 1;
            return 0;
        }

        int top(){
            if(size == 0)
                return -1;
            return area[size-1];
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        Stack_ stack = new Stack_();
        for(int i=0; i<N; i++){
            String[] cmdLine = br.readLine().split(" ");
            if(cmdLine.length==1){
                if(cmdLine[0].equals("pop"))
                    result.append(stack.pop() + "\n");
                else if(cmdLine[0].equals("size"))
                    result.append(stack.size() + "\n");
                else if(cmdLine[0].equals("empty"))
                    result.append(stack.empty() + "\n");
                else if(cmdLine[0].equals("top"))
                    result.append(stack.top() + "\n"); 
            } else{
                stack.push(Integer.parseInt(cmdLine[1]));
            }
        }

        System.out.println(result);
        br.close();
    }
}
