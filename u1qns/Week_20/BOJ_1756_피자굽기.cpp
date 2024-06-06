#include <iostream>
#include <vector>
#include <algorithm>
int N, M;

int getAnswer(const std::vector<int>& oven, const std::vector<int>& dough)
{
    int depth = oven.size();
    int cnt = 0;
    bool isStopped = false;
    for (const int& pizza : dough) 
    {
        while (--depth >= 0 && pizza > oven[depth]) {
            ++cnt;
            if (depth < 0) {
                isStopped = true;
                break;
            }
        }
        if (isStopped) {
            break;
        }
    }

    return cnt == M ? depth + 1 : 0;
}

int solve(const std::vector<int>& oven)
{
    int answer = 0;
    int pizza;
    int depth = N;
    std::vector<int> oven2(oven.rbegin(), oven.rend());
    for(int i=0; i<M; ++i)
    {
        std::cin >> pizza;
        if(pizza > oven[0]) return 0;
        // 앞에서부터 쭉 봣을때 가장 핏한 위치가 나옴
        int tmp = std::lower_bound(oven2.begin(), oven2.end(), pizza) - oven2.begin();
        answer = (answer < tmp ? tmp : answer+1);
    }

  return depth - answer;
}

int main()
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(0);

    std::cin >> N >> M;


    int input, r = 300001;
    std::vector<int> oven(N, 0);
    for(int i=0; i<N; ++i)
    {
        std::cin >> input;
        if(r > input)  r = input;
        oven[i] = r;
    }

    std::cout << solve(oven);
    //std::cout << getAnswer(oven, dough);

    return 0;
}
