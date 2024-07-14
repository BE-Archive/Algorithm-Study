#include <iostream>
#include <queue>

#define N_MAX 201
#define K_MAX 31

const int dx[4] = {-1, 1, 0, 0};
const int dy[4] = {0, 0, 1, -1};
const int hx[8] = {-2, -2, -1, -1, 1, 1, 2, 2};
const int hy[8] = {-1, 1, -2, 2, -2, 2, -1, 1};

int K, N, M, grid[N_MAX][N_MAX];

bool isValid(const int x, const int y)
{
    if(x<0 || y<0 || x>=N || y>=M || grid[x][y] == 1)
        return false;
    return true;
}

struct Node
{
    Node() = default;
    Node(int k, int x, int y)
        : k(k), x(x), y(y) {}
    
    int k, x, y;
};

int bfs()
{
    std::queue<Node> q;
    q.push({0, 0, 0});

    bool visited[K_MAX][N_MAX][N_MAX] = {false, };
    visited[0][0][0] = true;

    int x, y, k, answer = 0;
    Node front;

    while(!q.empty())
    {
        int qSize = q.size();
        while(qSize--)
        {
            front = q.front();
            k = front.k;
            q.pop();

            if(front.x == N-1 && front.y == M-1)
                return answer;

            for(int d=0; d<4; ++d)
            {
                x = front.x + dx[d];
                y = front.y + dy[d];

                if(!isValid(x, y) || visited[k][x][y]) continue;
                
                visited[k][x][y] = true;
                q.push({k, x, y});
            }

            if(k < K) // K 제한
            {
                for(int d=0; d<8; ++d)
                {
                    x = front.x + hx[d];
                    y = front.y + hy[d];

                    if(!isValid(x, y) || visited[k+1][x][y])
                        continue;

                    visited[k+1][x][y] = true;
                    q.push({k+1, x, y});
                }
            }
        }

        ++answer;
    }

    return -1;
}

int main()
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);
    
    std::cin >> K >> M>> N;

    for(int i=0; i<N; ++i)
        for(int j=0; j<M; ++j)
            std::cin >> grid[i][j];

    std::cout << bfs();

    return 0;
}
