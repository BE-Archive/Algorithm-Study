#include <iostream>
#include <vector>
#include <algorithm>

int main()
{
    int N, M, num, maxValue, answer = 0;
    std::vector<int> positive;
    std::vector<int> negative;

    std::cin >> N >> M;
    for(int i=0; i<N; ++i)
    {
        std::cin >> num;
        if(num > 0) positive.push_back(num);
        else negative.push_back(num*-1);
    }
    
    std::sort(positive.rbegin(), positive.rend());
    std::sort(negative.rbegin(), negative.rend());
        
    for(int i=0; i< positive.size(); i+=M)
        answer += positive[i];

    for(int i=0; i<negative.size(); i+=M)
        answer += negative[i];

    if(negative.empty())
        maxValue = 0;
    else
        maxValue = *negative.begin();
    
    if(!positive.empty())
        maxValue = std::max(maxValue, *positive.begin());
        
    answer *= 2;
    std::cout << answer - maxValue;
    return 0;
}
