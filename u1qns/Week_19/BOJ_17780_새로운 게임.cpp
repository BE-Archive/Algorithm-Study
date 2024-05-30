#include <iostream>
#include <queue>
#include <vector>

typedef std::pair<int, int> pii;

const int MAX_N = 13;

const int dx[4] = {0, 0, -1, 1};
const int dy[4] = {1, -1, 0, 0};

struct Info
{
    Info(int x, int y, int d) : x(x), y(y), d(d) {}
    int x, y, d;
};

int N, K;
int board[MAX_N][MAX_N];
std::deque<pii> state[MAX_N][MAX_N]; // 번호랑 방향
std::vector<Info> pieces;

enum
{
    WHITE = 0,
    RED = 1,
    BLUE = 2
};

bool isValid(const int x, const int y)
{
    if(x<0 || y<0 || x>=N || y>=N)
        return false;
    return true;
}

int getAnswer()
{
    int answer = 0;
    while(1)
    {
        //K개의 말을 순서대로 이동시킨다.
        for(int i=0; i<K; ++i)
        {
            Info p = pieces[i];

            // 현재 말이 위치한 곳에서 맨 밑인가?
            if(state[p.x][p.y].front().first != i) continue;

            //printf("%d번째 말 이동 (%d, %d)", i, p.x, p.y);

            // 현재 위치에서 이동할 다음 위치
            int d = p.d;
            int nextX = p.x + dx[d];
            int nextY = p.y + dy[d];

            if(!isValid(nextX, nextY) || board[nextX][nextY] == BLUE)
            {
                // 방향을 바꿔서
                if(d==0) d=1;
                else if(d==1) d=0;
                else if(d==2) d=3;
                else if(d==3) d=2;

                // 방향을 바꾼 다음 위치
                nextX = p.x + dx[d];
                nextY = p.y + dy[d];
                pieces[i].d = d;

                //그래도 못 가는 구역이라면 방향만 바꿔주고 가만히 있는다.
                if(!isValid(nextX, nextY) || board[nextX][nextY] == BLUE)
                {
                    continue;
                }
            }

            if(state[nextX][nextY].size() + state[p.x][p.y].size() >= 4)
                return answer+1;

            if(board[nextX][nextY] == WHITE)
            {
                while(!state[p.x][p.y].empty())
                {
                    pii tmp = state[p.x][p.y].front();
                    state[p.x][p.y].pop_front();

                    int idx = tmp.first;
                    pieces[idx].x = nextX;
                    pieces[idx].y = nextY;

                    state[nextX][nextY].push_back(tmp);
                }

            }
            else if(board[nextX][nextY] == RED)
            {
                while(!state[p.x][p.y].empty())
                {
                    pii tmp = state[p.x][p.y].back();
                    state[p.x][p.y].pop_back();

                    int idx = tmp.first;
                    pieces[idx].x = nextX;
                    pieces[idx].y = nextY;

                    state[nextX][nextY].push_back(tmp);
                }
            }
        }

        if(answer++ == 1000)
            return -1;
    }
    return answer;
}

int main(){

    std::cin >> N >> K;
    for(int i=0; i<N; ++i)
        for(int j=0; j<N; ++j)
            std::cin >> board[i][j];

    int x, y, d;
    for(int i=0; i<K; ++i)
    {
        std::cin >> x >> y >> d;
        pieces.push_back({x-1, y-1, d-1});
        state[x-1][y-1].push_back({i, d-1});
    }

    std::cout << getAnswer();
}
