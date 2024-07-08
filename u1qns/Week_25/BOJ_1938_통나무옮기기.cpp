#include <iostream>
#include <vector>
#include <queue>
#define MAX 51
#define DIR_SIZE 9
typedef std::pair<int, int> pii;

const int dx[DIR_SIZE] = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
const int dy[DIR_SIZE] = {-1, 0, 1, -1, 0, 1, -1, 0, 1};

int N;
bool grid[MAX][MAX] = {false, };

bool isValid(const int x, const int y)
{
    if(x < 0 || y < 0 || x >= N || y >= N || grid[x][y])
        return false;
    return true;
}

std::vector<pii> start, end;
struct Node
{
    Node() = default;
    Node(int d, int x, int y) : d(d), x(x), y(y) {}
    int d, x, y;
};

bool isDone(const Node& target, const Node& dest)
{
    return (dest.d == target.d
            && dest.x == target.x
            && dest.y == target.y);
}

int bfs()
{
    int answer = 0;
    Node dest = {(end[0].first == end[1].first), end[1].first, end[1].second};

    std::queue<Node> q;
    q.push({(start[0].first == start[1].first), start[1].first, start[1].second});

    bool visited[2][MAX][MAX] = {false, };
    visited[q.front().d][q.front().x][q.front().y] = true;

    while(!q.empty())
    {
        int qSize = q.size();
        ++answer;
        while(qSize--)
        {
            int mx = q.front().x; int my = q.front().y;
            int isHorizontal = q.front().d;

            q.pop();

            int fx = (isHorizontal ? mx : mx-1 ); int fy = (isHorizontal ? my-1 : my);
            int lx = (isHorizontal ? mx : mx+1 ); int ly = (isHorizontal ? my+1 : my);

            for(int d=1; d<DIR_SIZE; d+=2)
            {
                int x = mx + dx[d];
                int y = my + dy[d];

                if(!isValid(x, y) || visited[isHorizontal][x][y]
                    || !isValid(fx + dx[d], fy + dy[d]) 
                    || !isValid(lx + dx[d], ly + dy[d]))
                    continue;

                if(isDone({isHorizontal, x, y}, dest))
                    return answer;

                visited[isHorizontal][x][y] = true;
                q.push({isHorizontal, x, y});
            }

            // TURN
            if(visited[!isHorizontal][mx][my]) continue;

            bool flag = true;
            for (int d=0; d<DIR_SIZE; ++d)
            {
                if (!isValid(mx + dx[d], my + dy[d]))
                {
                    flag = false;
                    break;
                }
            }

            if (flag)
            {
                visited[!isHorizontal][mx][my] = true;
                q.push({!isHorizontal, mx, my});
            }
        }
    }

    return 0;
}


int main()
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);

    std::cin >> N;

    char ch;
    for(int i=0; i<N; ++i)
    {
        for(int j=0; j<N; ++j)
        {
            std::cin >> ch;
            grid[i][j] = (ch=='1');

            if(ch == 'B')
                start.push_back({i, j});
            else if(ch=='E')
                end.push_back({i, j});
        }
    }

    std::cout << bfs();

    return 0;
}
