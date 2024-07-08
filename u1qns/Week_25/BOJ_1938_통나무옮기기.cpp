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

    std::queue<Node> q; // 통나무의 가로 세로 여부와 통나무 중앙 위치를 중심으로 저장
    q.push({(start[0].first == start[1].first), start[1].first, start[1].second});

    bool visited[2][MAX][MAX] = {false, };
    visited[q.front().d][q.front().x][q.front().y] = true;

    while(!q.empty())
    {
        int qSize = q.size();
        while(qSize--)
        {
            const int d = q.front().d;
            const int x = q.front().x;
            const int y = q.front().y;

            if(isDone(q.front(), dest))
                return answer;

            q.pop();

            if (!d) // 통나무가 세워져있음
            {
                //U
                if (isValid(x-1, y) && isValid(x-2, y)
                    && !visited[d][x-1][y])
                {
                    visited[d][x-1][y] = true;
                    q.push({d, x-1, y});
                }

                //D
                if (isValid(x+1, y) && isValid(x+2, y)
                    && !visited[d][x+1][y])
                {
                    visited[d][x+1][y] = true;
                    q.push({d, x+1, y});
                }

                //L
                if (isValid(x, y-1) && isValid(x-1, y-1) && isValid(x+1, y-1)
                    && !visited[d][x][y-1])
                {
                    visited[d][x][y-1] = true;
                    q.push({d, x, y-1});
                }

                //R
                if (isValid(x, y+1) && isValid(x-1, y+1) && isValid(x+1, y+1)
                    && !visited[d][x][y+1])
                {
                    visited[d][x][y+1] = true;
                    q.push({d, x, y+1});
                }

                //T
                if(!visited[!d][x][y])
                {
                    bool flag = true;
                    for (int i = 0; i < DIR_SIZE; ++i)
                    {
                        if (!isValid(x + dx[i], y + dy[i])) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        visited[!d][x][y] = true;
                        q.push({!d, x, y});
                    }
                }

            }
            else // 통나무가 누워져있음
            {
                //U
                if (isValid(x-1, y-1) && isValid(x-1, y) && isValid(x-1, y+1)
                    && !visited[d][x-1][y])
                {
                    visited[d][x-1][y] = true;
                    q.push({d, x-1, y});
                }

                //D
                if (isValid(x+1, y-1) && isValid(x+1, y) && isValid(x+1, y+1)
                    && !visited[d][x+1][y])
                {
                    visited[d][x+1][y] = true;
                    q.push({d, x+1, y});
                }

                //L
                if (isValid(x, y-1) && isValid(x, y-2)
                    && !visited[d][x][y-1])
                {
                    visited[d][x][y-1] = true;
                    q.push({d, x, y-1});
                }

                //R
                if (isValid(x, y+1) && isValid(x, y+2)
                    && !visited[d][x][y+1])
                {
                    visited[d][x][y+1] = true;
                    q.push({d, x, y+1});
                }

                //T
                if(!visited[!d][x][y])
                {
                    bool flag = true;
                    for (int i = 0; i < DIR_SIZE; ++i)
                    {
                        if (!isValid(x + dx[i], y + dy[i])) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        visited[!d][x][y] = true;
                        q.push({!d, x, y});
                    }
                }
            }
        }
        ++answer;
    }

    return 0;
}


int main()
{
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
