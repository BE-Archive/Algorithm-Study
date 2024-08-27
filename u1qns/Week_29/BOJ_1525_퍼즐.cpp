#include <iostream>
#include <string>
#include <queue>
#include <set>

constexpr int dx[4] = {-1, 1, 0, 0};
constexpr int dy[4] = {0, 0, -1, 1};

bool isValid(const int r, const int c)
{
    if(r<0 || c<0 || r>=3 || c>=3 )
        return false;
    return true;
}

int main()
{
    int zero, cnt = 0;
    char ch;
    std::string inp;
    std::string answer = "123456780";
    std::set<std::string> visited;

    int dummy;
    for(int i=0; i<9; ++i)
    {
        std::cin >> dummy;
        inp += (dummy + '0');
        if(dummy== 0) zero = i;
    }
    
    if(answer == inp)
    {
        std::cout << 0;
        return 0;
    }

    std::queue<std::pair<std::string, int> > q;
    q.push({inp, zero});

    while(!q.empty())
    {
        int qSize = q.size();
        ++cnt;

        while(qSize--)
        {
            auto now = q.front().first;
            auto zero = q.front().second;
            q.pop();

            int zx = zero/3;
            int zy = zero%3;

            for(int d=0; d<4; ++d)
            {
                int x = zx + dx[d];
                int y = zy + dy[d];
                int next_zero = x*3 + y;

                if(!isValid(x, y)) continue;

                std::string tmp = now;
                tmp[zero] = tmp[x* 3 + y];
                tmp[x* 3 + y] = '0';

                if(visited.find(tmp) != visited.end()) continue;
                //std::cout << tmp << std::endl;
                if(answer == tmp)
                {
                    std::cout << cnt;
                    return 0;
                }
              
                q.push({tmp, next_zero});
                visited.insert(tmp);
            }

        }
    }
    std::cout << -1;
    return 0;
}
