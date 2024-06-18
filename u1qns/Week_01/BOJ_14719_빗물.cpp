#include <iostream>

int main()
{    	
    int block[501] = {0, };
    int W, H, answer = 0;
    std::cin >> H >> W;

    // 제일 높은 블록을 가진 idx
    int max = 0; 
    int maxIdx = 0;
    for(int i=0; i<W; ++i)
    {
        std::cin >> block[i];
        
        if(max < block[i])
        {
            max = block[i];
            maxIdx = i;
        }
    }

    int pos = 0; // 기준이 되는 블록 idx
    for(int i=1; i<maxIdx; ++i)
    {
        if(block[i] < block[pos])
            answer += (block[pos]-block[i]);
        else 
            pos = i;
    }

    pos = W-1;
    for(int i=W-1; i>maxIdx; --i)
    {
        if(block[i] < block[pos])
        	answer += (block[pos]-block[i]);
        else
            pos = i;
    }

    std::cout << answer;
    return 0;
}
