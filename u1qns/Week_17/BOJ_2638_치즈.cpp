#include <iostream>
#include <queue>
#define MAX 101

typedef std::pair<int, int> pii;

const int dx[4] = {1, -1, 0, 0};
const int dy[4] = {0, 0, 1, -1};

int N, M, totalCheese = 0;

int arr[MAX][MAX], counter[MAX][MAX] = {0, };
bool visited[MAX][MAX] = {false, };

bool isValid(const int x, const int y)
{
    if(x<0 || x>=N || y<0 ||y>=M)
        return false;
    return true;
}

std::queue<pii> melt(std::queue<pii>& air)
{
    std::queue<pii> cheese;
    
    pii front;
    while(!air.empty())
    {
        front = air.front(); air.pop();
        for(int d=0; d<4; ++d)
        {
            int x = front.first + dx[d];
            int y = front.second + dy[d];

            if(!isValid(x, y) || visited[x][y]) continue;

            if(arr[x][y])
            {
                if(++counter[x][y] > 1)
                {
                    --totalCheese;
                    visited[x][y] = true;
                    cheese.push(std::make_pair(x, y));
                }
            }
            else
            {
                visited[x][y] = true;
                air.push(std::make_pair(x, y));
            }
        }
    }
    return cheese;
}

int solve()
{
    int answer = 0;

    std::queue<pii> air;
    air.push(std::make_pair(0, 0));

    while(totalCheese)
    {
        ++answer;
        air = melt(air);
        //show();
    }

    return answer;
}

int main()
{
    std::cin >> N >> M;

    for(int i=0; i<N; ++i)
    {
        for(int j=0; j<M; ++j)
        {
            std::cin >> arr[i][j];
            if(arr[i][j]) ++totalCheese;
        }
    }

    std::cout << solve();

    return 0;
}
