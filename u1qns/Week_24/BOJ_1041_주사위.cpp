#include <iostream>
#include <algorithm>
long long N;
long long dice[6] = {0, };

long long getAnswer(const long long min1) {
    long long answer = 0;

    if(N == 1)
    {
        long long mv = 0;
        for(int i = 0; i < 6; ++i)
        {
            answer += dice[i];
            mv = std::max(mv, dice[i]);
        }
        return answer - mv;
    }

    long long min2 = 100;
    long long min3 = 150;
    for(int i=0; i<6; ++i)
    {
        for(int j = i+1; j<6; ++j)
        {
            if(i+j == 5) continue;

            min2 = std::min(min2, dice[i]+dice[j]);
            for(int k=j+1; k<6; ++k)
            {
                if(j+k == 5 || i+k==5) continue;
                min3 = std::min(min3, dice[i] + dice[j] + dice[k]);
            }
        }
    }


    answer += 4 * min3; // 3면
    answer += (((N-2) * (N-2) * 5) + ((N-2) * 4)) * min1; // 2면
    answer += (N-2) * 8 * min2;
    answer += 4 * min2; // N-2이 2일 때를 위해 분기함. 최하단 모서리

    return answer;
}

int main()
{
    long long min1 = 50;
    std::cin >> N;
    for(int i = 0; i < 6; ++i)
    {
        std::cin >> dice[i];
        min1 = std::min(min1, dice[i]);
    }

    std::cout << getAnswer(min1);
}
