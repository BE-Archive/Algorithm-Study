import java.io.*;
import java.util.*;
	
public class Main {
	
//17108KB	136MS

	static int MAX_SIZE = 10001;

	static class queue
	{
		int[] items = new int[MAX_SIZE];
		int size = 0;
		int front=0, rear =0;
		public queue()
		{
			
		}
	    
	    void Push(int item)
	    {
	        if(!isFull())
	        {
	            items[rear] = item;
	            rear = (++rear == MAX_SIZE ? 0 : rear);
	            ++size;
	        }
	    }
	    
	    int Pop()
	    {
	        if(isEmpty())
	        {
	            return -1;
	        }
	        
	    	int result = items[front];
            front = (++front == MAX_SIZE ? 0 : front);
            --size;
	        return result;
	    }
	    
	    int Size()
	    {
	        return size;
	    }
	   
	    boolean isFull()
	    {
	        return (size == MAX_SIZE ? true : false);
	    }
	    
	    boolean isEmpty()
	    {
	        return size == 0 ? true : false;
	    }

		public int Front() {
			if(isEmpty())
				return -1;
			return items[front];
		}

		public int Back() {
			if(isEmpty())
				return -1;
			return items[rear-1];
		}


	};
	
	public static void main(String[] args) throws IOException {


		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		queue q = new queue();
		String op;
		
		int N = Integer.parseInt(st.nextToken());
		int item = 0;
		
	    while(N -- > 0)
	    {
	    	
	    	st = new StringTokenizer(br.readLine());
	        op = st.nextToken();
	        
	        if(op.equals("push"))
	        {
	            item = Integer.parseInt(st.nextToken());
	           	q.Push(item);
	        }
	        else if(op.equals("pop")) sb.append(q.Pop()).append("\n");
	        else if(op.equals("front")) sb.append(q.Front()).append("\n");
	        else if(op.equals("back")) sb.append(q.Back()).append("\n");
	        else if(op.equals("size")) sb.append(q.Size()).append("\n");
	        else if(op .equals("empty")) sb.append(q.isEmpty() ? 1 : 0).append("\n");
	    }
	    
	    System.out.print(sb);
	}

}
