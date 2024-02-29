#include<iostream>

int main()
{
	int N;
    	std::cin >> N;
	
	int div = N/5;
	int mod = N%5;
	int answer = -1;
	
	if((N==4) || (N==7))
        	answer = -1;
	else
    	{
	        if((mod==2)||(mod==4)) answer = div+2;
	        else if((mod==1)||(mod==3)) answer= div+1;
	        else if(mod==0) answer = div;
	}
	
	std::cout << answer;

	return 0;		
}
