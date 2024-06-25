#include <iostream>
#include <vector>
#include <queue>
#define INF 1e9

using namespace std;

typedef pair<int, int> pii;

const int dx[4] = {-1, 1, 0, 0};
const int dy[4] = {0, 0, -1, 1};

int N, M;
int startX, startY, endX, endY;

vector<vector<int>> grid;
vector<vector<vector<int> > > dp;

struct info
{
    info(int x, int y, int cost, int k) : x(x), y(y), cost(cost), k(k) {}

    int x, y, cost, k;

    bool operator>(const info& o) const
    {
        return cost > o.cost;
    }
};


bool setRange(const int k, pii& output)
{
    if(k == 0)
        output = make_pair(0, 4);
    else if (k == 1) 
        output = make_pair(0, 2);
    else if (k == 2) 
        output = make_pair(2, 4);
    else
        return false;
    
    return true;
}

int getAnswer()
{
    priority_queue<info, vector<info>, greater<info>> pq;
    pq.push({startX-1, startY-1, 0, 1});

    pii range;
    while(!pq.empty())
    {
        info top = pq.top(); pq.pop();
        
        int mod = top.k % 3;
        setRange(mod, range);

        for(int d=range.first; d<range.second; ++d)
        {
            int x = top.x + dx[d];
            int y = top.y + dy[d];

            if (x < 0 || y < 0 || x >= N || y >= M) continue;
            if (grid[x][y] == -1) continue;
            
            if (x==endX-1 && y==endY-1) 
                return top.cost; 

            int next_cost = top.cost + grid[x][y];
            if (next_cost < dp[mod][x][y])
            {
                dp[mod][x][y] = next_cost;
                pq.push({x, y, dp[mod][x][y], top.k+1});
            }
        }
    }
    
    return -1;
}

int main()
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(0);
    
    cin >> N >> M;
    cin >> startX >> startY >> endX >> endY;

    grid.resize(N, vector<int>(M));
    dp.resize(3, vector<vector<int> >(N, vector<int>(M, INF)));

    for(int i=0; i<N; ++i)
        for(int j=0; j<M; ++j)
            cin >> grid[i][j];
    
    cout << getAnswer();

    return 0;
}
