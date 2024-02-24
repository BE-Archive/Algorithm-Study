#include <iostream>
#include <queue>
#include <memory.h>
#include <algorithm>
#include <vector>

using namespace std;

typedef std::pair<int, int> pii;

const int MAX = 20 + 1;
const int dx[4] = {0, 1, -1, 0};
const int dy[4] = {-1, 0, 0, 1};

int N, M, F;
int map[MAX][MAX];
bool visited[MAX][MAX];

bool isValid(const int x, const int y)
{
    if(x<0 || y<0 || x>=N || y>=N)
        return false;
    return true;
}

struct Passenger
{
    public:
        pii from, to;
        int distance = 0;
};

std::vector<Passenger> p;
pii taxi;

enum 
{
    EMPTY = 0,
    WALL = 1,
    //승객 2 ~ M+2
};


bool pick(pii& nextPos)
{
    // 내가 손님있는 곳에서 시작할 수도 있음 
    if(map[taxi.first][taxi.second] > 1)
    {
        int target = map[taxi.first][taxi.second] - 2;
        map[taxi.first][taxi.second] = 0;
        nextPos = p[target].to;
        return true;
    }

    queue<pii> q;
    q.push(taxi);

    memset(visited, false, sizeof(visited));
    visited[taxi.first][taxi.second] = true;

    int distance = 0;
    std::vector<pii> tmp;

    while(!q.empty())
    {

        if(++distance > F) return false;

        int qSize = q.size();
        while(qSize--)
        {
            int nowX = q.front().first;
            int nowY = q.front().second;
            q.pop();

            for(int d=0; d<4; ++d)
            {
                int x = nowX + dx[d];
                int y = nowY + dy[d];

                if(!isValid(x, y) || visited[x][y] || map[x][y] == WALL) 
                    continue;
                
                visited[x][y] = true;
                q.push({x, y});

                if(map[x][y] > 1) // 승객이다.
                {
                    tmp.push_back({x, y});
                }
            }

        }
        
        if(!tmp.empty())
        {
            std::sort(tmp.begin(), tmp.end());

            int target = map[tmp[0].first][tmp[0].second] - 2;
            F = F - distance;
            map[tmp[0].first][tmp[0].second] = 0;

            taxi = tmp[0];
            nextPos = p[target].to;
            return true;
        }
    }

    return false;
}
    
bool go(const pii& dest)
{
    if(taxi.first == dest.first && taxi.second == dest.second)
    { 
        return true;
    }

    queue<pii> q;
    q.push(taxi);

    memset(visited, false, sizeof(visited));
    visited[taxi.first][taxi.second] = true;

    int distance = 0;

    while(!q.empty())
    {
        if(++distance > F) return false;

        int qSize = q.size();
        while(qSize--)
        {
            int nowX = q.front().first;
            int nowY = q.front().second;
            q.pop();

            for(int d=0; d<4; ++d)
            {
                int x = nowX + dx[d];
                int y = nowY + dy[d];

                if(!isValid(x, y) || map[x][y] == WALL || visited[x][y])
                    continue;

                if(dest.first == x && dest.second == y)
                {
                    F =  F + distance;
                    taxi = dest;
                    return true;
                }
                visited[x][y] = true;
                q.push({x, y});
            }
        }
    }

    return false;
}

bool solve()
{
    bool result = true;
    for(int i=0; i<M; ++i)
    {
        pii nextPos;

        // 택시는 가장 가까운 손님을 찾으면 성공하면 nextPos에 목적지를 담는다.
        result = pick(nextPos);
        if(!result || F < 0)
            return false;
        
        // nextPos 목적지로 택시가 이동한다.
        result = go(nextPos);
        if(!result || F < 0)
            return false;

    }
    return true;
}
int main()
{
    
    cin >> N >> M >> F;

    p.resize(M);
    for (int i = 0; i < N; ++i)
    {
        for (int j = 0; j < N; ++j)
            cin >> map[i][j];
    }

    cin >> taxi.first >> taxi.second;
    taxi.first--;
    taxi.second--;


    for (int i = 0; i < M; ++i)
    {
        cin >> p[i].from.first>> p[i].from.second >> p[i].to.first >> p[i].to.second;

        p[i].from.first--;
        p[i].from.second--;
        p[i].to.first--;
        p[i].to.second--;

        // 승객 출발지 맵에 저장
        map[p[i].from.first][p[i].from.second] = i + 2;
    }

    bool answer = solve();

    cout << (answer ? F : -1);
    return 0;
}
