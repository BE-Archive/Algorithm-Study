import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static class Node {
		Node prev;
		int data;
		Node(Node prev, int data) {
			this.prev = prev;
			this.data = data;
		}
	}
	public static class Stack {
		int size;
		Node top;
		void push(int data) {
			size++;
			if(empty() == 1) {
				top = new Node(null, data);
				return;
			}
			Node addNode = new Node(top, data);
			top = addNode;
		}
		int pop() {
			if(empty() == 1) return -1; 
			size--;
			int data = top.data;
			top = top.prev;
			return data;
		}
		int size() {
			return size;
		}
		int empty() {
			return size == 0 ? 1 : 0;
		}
		int top() {
			return empty() == 1 ? -1 : top.data;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Stack stack = new Stack();
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String order = st.nextToken();
			if(order.equals("push")) {
				int X = Integer.parseInt(st.nextToken());
				stack.push(X);
			}
			else if(order.equals("pop")) {
				sb.append(stack.pop()).append('\n');
			}
			else if(order.equals("size")) {
				sb.append(stack.size()).append('\n');
			}
			else if(order.equals("empty")) {
				sb.append(stack.empty()).append('\n');
			}
			else {
				sb.append(stack.top()).append('\n');
			}				
		}
		System.out.println(sb);
	}
}