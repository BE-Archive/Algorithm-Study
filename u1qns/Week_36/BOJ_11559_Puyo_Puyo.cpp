#include <iostream>
#include <queue>
#include <memory.h>

using namespace std;
typedef pair<int, int> pii;
typedef vector<vector<char>> MapType;

constexpr int dx[4] = {0, 0, 1, -1};
constexpr int dy[4] = {1, -1, 0, 0};

int answer = 0;
MapType grid;
queue<pii> Red, Yellow, Green;
bool visited[12][6] = {false, };
queue<pii> target;

bool isValid(const int x, const int y)
{
    if(x>=12 || y>=6 || x<0 || y<0)
        return false;
    return true;
};

void init()
{
    grid.resize(12, vector<char>(6));
    ios_base::sync_with_stdio(false);
    cin.tie(0);
    cout.tie(0);
}

MapType gravity()
{
    MapType arr;
    arr.resize(12, vector<char>(6, '.'));

    // 세로로 한 줄씩 본다.
    for(int j=0; j<6; ++j)
    {
        int pos = 11;
        for(int i=11; i>=0; --i)
        {
            if(grid[i][j] == '.') continue;
            arr[pos--][j] = grid[i][j];
        }
    }

    return arr;
}

void BFS(const int _x, const int _y)
{
    int x, y;
    char color = grid[_x][_y];
    visited[_x][_y] = true;

    queue<pii> q;
    q.push({_x, _y});

    queue<pii> tmp;
    tmp.push({_x, _y});

    while(!q.empty())
    {
        pii front = q.front(); q.pop();
        for(int d=0; d<4; ++d)
        {
            x = front.first + dx[d];
            y = front.second + dy[d];

            if(visited[x][y] || !isValid(x, y) || grid[x][y] != color)
                continue;

            visited[x][y] = true;
            q.push({x, y});
            tmp.push({x, y});
        }
    }

    if(tmp.size() < 4)
        return;

    while(!tmp.empty())
    {
        x = tmp.front().first;
        y = tmp.front().second;
        tmp.pop();

        target.push({x, y});
    }
}

void find_target()
{
    memset(visited, false, sizeof(visited));

    for(int i=0; i<12; ++i)
    {
        for(int j=0; j<6; ++j)
        {
            if(visited[i][j] || grid[i][j] == '.') continue;
            BFS(i, j);
        }
    }
}

void pop_target()
{
    int x, y;
    while(!target.empty())
    {
        x = target.front().first;
        y = target.front().second;
        target.pop();
        grid[x][y] = '.';
    }
}

int getAnswer()
{
    int answer = 0;
    while(true)
    {
        find_target();
        if(target.empty())
            break;

        pop_target();
        grid = gravity();
        ++answer;
    }
    return answer;
}

int main() {

    init();
    for(int i=0; i<12; ++i)
        for(int j=0; j<6; ++j)
            cin >> grid[i][j];

    cout << getAnswer();
    return 0;
}
