package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class SWEA_1230 {

	//노드 클래스
	static class Node<T> {
		public T value;
		public Node<T> next;
		
		public Node(){}
		
		public Node(T value, Node<T> next){
			this.value=value;
			this.next=next;
		}
	}
	
	//링크드리스트 클래스
	static class MyLinkedList<T>{
		private Node<T> head = null;
		private Node<T> tail = null;
		
		public MyLinkedList(){
			head= new Node<T>();
		}
		
		public void add(T value) {
			Node<T> newNode = new Node<T>(value,null);
	
			if (head.next==null) { //첫번째 노드면
				head.next=newNode;
			}
			else { //첫번째 노드가 아니면
				tail.next=newNode;
			}
			
			tail=newNode;
		}

		public void insert(int idx, T value){
			Node<T> newNode = new Node<T>(value, null);
			Node<T> preNode=getNode(idx-1);
			
			newNode.next=preNode.next;
			preNode.next=newNode;
		}

		public void delete(int idx){
			Node<T> preNode = getNode(idx-1);
			Node<T> nowNode = preNode.next;
			preNode.next = nowNode.next;
			nowNode.next=null;
		}

		public Node<T> getNode(int idx){
			Node<T> nowNode=head;
			while (idx>=0) { 
				nowNode=nowNode.next;
				idx--;
			}
			return nowNode;
		}
		
		public String toString() {
			String str = "";
			Node<T> nowNode=head.next;
			
			for (int i=0; i<10; i++) {
				str+=(nowNode.value+" ");
				nowNode = nowNode.next;
				if (nowNode==null) break;
			}
		
			return str;
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for (int tc=1; tc<=10; tc++) {
			
			int n = Integer.parseInt(br.readLine());
			MyLinkedList<Integer> list = new MyLinkedList<>();
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i=0; i<n; i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			int commandCnt = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			for (int i=0; i<commandCnt; i++) {
				
				String command = st.nextToken();
				
				if (command.equals("I")) {
					int startIdx = Integer.parseInt(st.nextToken());
					int cnt = Integer.parseInt(st.nextToken());
					for (int j=0; j<cnt; j++){
						list.insert(startIdx+j, Integer.parseInt(st.nextToken()));
					}
				}
				else if (command.equals("D")) {
					int startIdx = Integer.parseInt(st.nextToken());
					int cnt = Integer.parseInt(st.nextToken());
					for (int j=0; j<cnt; j++){
						list.delete(startIdx);
					}
				}
				else if (command.equals("A")) {
					int cnt = Integer.parseInt(st.nextToken());
					for (int j=0; j<cnt; j++){
						list.add(Integer.parseInt(st.nextToken()));
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(list.toString()).append("\n");
		}
		
		System.out.println(sb.toString());
	}
}
