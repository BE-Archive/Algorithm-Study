#include <algorithm>
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

// 8방향을 봐서 모래성이 쌓여있지 않은 부분의 개수가 자기 모래성의 튼튼함보다
// 많거나 같은 경우 파도에 의해서 무너질 수 있음

int h,w;
int mat[1001][1001];
queue<pair<int,int>> q;
int dx[8] = {-1, -1, -1, 0, 0, 1, 1, 1};
int dy[8] = {-1, 0 ,1, -1, 1, -1, 0, 1};
int wave[1001][1001];
int ans;

void init() {
    cin>>h>>w;
    for(int i=0; i<h; i++) {
        string s; cin>>s;
        for(int j=0; j<w; j++) {
            if(s[j]=='.') {
                mat[i][j]=0;
                q.push({i,j});
            }
            else mat[i][j]=s[j]-'0';
        }
    }
}

void bfs() {
    while(!q.empty()) {
        pair<int,int> pii = q.front();
        q.pop();

        for(int d=0; d<8; d++) {
            int nx=pii.first+dx[d];
            int ny=pii.second+dy[d];
            if(nx<0||nx>=h||ny<0||ny>=w) continue;
            if(mat[nx][ny]) {
                mat[nx][ny]--;
                if(!mat[nx][ny]) {
                    q.push({nx,ny});
                    wave[nx][ny] = wave[pii.first][pii.second]+1;
                    ans=max(ans,wave[nx][ny]);
                }
            }
        }
    }
}

int main() {

    init();

    bfs();

    cout<<ans;
    return 0;
}

/*

초반에 0들을 큐에 담아준다.

0 0 0 0 0 0    queue: (0,0), (0,1), (0,2), (0,3), (0,4), (0,5),
0 9 3 9 0 0           (1,0), (1,4), (1,5), (2,0), (2,5), (3,0), (3,5),
0 3 4 2 8 0           (4,0), (4,1), (4,2), (4,3), (4,4), (4,5)
0 9 3 9 3 0
0 0 0 0 0 0

이때부터 큐에 push되는 횟수가 파도에 휩쓸리는 횟수와 같음.

0 0 0 0 0 0    queue: (1,2), (2,1), (3,2), (3,4)
0 4 0 5 0 0
0 0 4 1 4 0
0 4 0 6 0 0
0 0 0 0 0 0

0 0 0 0 0 0    queue: (2,3)
0 2 0 4 0 0
0 0 1 0 3 0
0 2 0 4 0 0
0 0 0 0 0 0

0 0 0 0 0 0    queue: (2,2)
0 2 0 3 0 0
0 0 0 0 2 0
0 2 0 3 0 0
0 0 0 0 0 0

0 0 0 0 0 0 queue:
0 1 0 2 0 0
0 0 0 0 2 0
0 1 0 2 0 0
0 0 0 0 0 0

큐에 총 세차례 push되었으므로 답은 3이다.

 */