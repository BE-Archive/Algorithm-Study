import java.io.*;
import java.util.*;
	
public class Main {
	
  //16712kb	136ms

	static int N = 10001;

	static class stack
	{
		int[] items = new int[N];
		int size = 0;
		public stack()
		{
			
		}
	    
	    void Push(int item)
	    {
	        if(!isFull())
	        {
	            items[size++] = item;
	        }
	    }
	    
	    int Pop()
	    {
	        if(isEmpty())
	        {
	            return -1;
	        }
	        return items[--size];
	    }
	    
	    int Top()
	    {
	        if(isEmpty())
	        {
	            return -1;
	        }
	        return items[size-1];
	    }
	    
	    int Size()
	    {
	        return size;
	    }
	   
	    boolean isFull()
	    {
	        return (size == N ? true : false);
	    }
	    
	    boolean isEmpty()
	    {
	        return size == 0 ? true : false;
	    }


	};
	
	public static void main(String[] args) throws IOException {


		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		stack s = new stack();
		String op;
		
		int r = Integer.parseInt(st.nextToken());
		
		
		
		int item = 0;
		
	    while(r-- > 0)
	    {
	    	
	    	st = new StringTokenizer(br.readLine());
	        op = st.nextToken();
	        
	        if(op.equals("push"))
	        {
	            item = Integer.parseInt(st.nextToken());
	           	s.Push(item);
	        }
	        else if(op.equals("pop")) sb.append(s.Pop()).append("\n");
	        else if(op.equals("top")) sb.append(s.Top()).append("\n");
	        else if(op.equals("size")) sb.append(s.Size()).append("\n");
	        else if(op .equals("empty")) sb.append(s.isEmpty() ? 1 : 0).append("\n");
	    }
	    
	    System.out.print(sb);
	}

}
