import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static class Node {
		Node next;
		int data;
		
		Node(Node next, int data) {
			this.next = next;
			this.data = data;
		}
		
		Node(int data) {
			this.data = data;
		}
	}
	public static class Queue {
		int size;
		Node front, back;
		void push(int data) {
			size++;
			if(size == 1) {
				front = new Node(null, data);
				back = front;
				return;
			}
			back.next = new Node(data);
			back = back.next;
		}
		int pop() {
			if(empty() == 1) return -1; 
			size--;
			int data = front.data;
			front = front.next;
			return data;
		}
		int size() {
			return size;
		}
		int empty() {
			return size == 0 ? 1 : 0;
		}
		int front() {
			return empty() == 1 ? -1 : front.data;
		}
		int back() {
			return empty() == 1 ? -1 : back.data;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Queue queue = new Queue();
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			if(order.equals("push")) {
				int X = Integer.parseInt(st.nextToken());
				queue.push(X);
			}
			else if(order.equals("pop")) {
				sb.append(queue.pop()).append('\n');
			}
			else if(order.equals("size")) {
				sb.append(queue.size()).append('\n');
			}
			else if(order.equals("empty")) {
				sb.append(queue.empty()).append('\n');
			}
			else if(order.equals("front")){
				sb.append(queue.front()).append('\n');
			}
			else {
				sb.append(queue.back()).append('\n');
			}	
		}
		System.out.println(sb);
	}
}