import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static class Node{
        int data;
        Node next;
        Node(int data){
            this.data = data;
            next = null;
        }
    }

    static class Queue_{
        Node front;
        Node rear;
        int size;

        Queue_(){
            front = null;
            rear = null;
            size = 0;
        }

        void push(int x){
            if(size==0){
                front = new Node(x);
                rear = front;
            } else{
                Node node = new Node(x);
                rear.next = node;
                rear = node;
            }
            size++;
        }

        int pop(){
            if(size == 0)
                return -1;

            Node node = front;
            front = node.next;
            size--;

            return node.data;
        }

        int size(){
            return size;
        }

        int empty(){
            if(size == 0)
                return 1;
            return 0;
        }

        int front(){
            if(size == 0)
                return -1;
            return front.data;
        }

        int back(){
            if(size == 0)
                return -1;
            return rear.data;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        Queue_ stack = new Queue_();
        for(int i=0; i<N; i++){
            String[] cmdLine = br.readLine().split(" ");
            if(cmdLine.length==1){
                if(cmdLine[0].equals("pop"))
                    result.append(stack.pop() + "\n");
                else if(cmdLine[0].equals("size"))
                    result.append(stack.size() + "\n");
                else if(cmdLine[0].equals("empty"))
                    result.append(stack.empty() + "\n");
                else if(cmdLine[0].equals("front"))
                    result.append(stack.front() + "\n"); 
                else if(cmdLine[0].equals("back"))
                    result.append(stack.back() + "\n"); 
            } else{
                stack.push(Integer.parseInt(cmdLine[1]));
            }
        }

        System.out.println(result);
        br.close();
    }
}
