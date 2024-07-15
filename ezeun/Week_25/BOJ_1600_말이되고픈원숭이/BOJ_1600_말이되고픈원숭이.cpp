#include <algorithm>
#include <iostream>
#include <vector>
#include <queue>
using namespace std;
typedef pair<int,int> pii;
int k, w, h;
int mat[201][201]; // 세로h, 가로w
int dx[12]={-2,-1,1,2,2,1,-1,-2,  -1,0,1,0}; //말 8방향, 상좌하우
int dy[12]={-1,-2,-2,-1,1,2,2,1,  0,-1,0,1};
int vis[201][201][31]; //k값에 대한 방문배열

void bfs(int x, int y) {
    queue<pair<pii,int>> q; //{{x,y},k}
    q.push({{x,y},k});
    vis[x][y][k]=1;

    while(!q.empty()) {
        pair<pii,int> cur = q.front(); q.pop();

        for(int d=0; d<12; d++) {
            if(d<8 && cur.second==0) continue; //말처럼 이동할 수 없으면 패스
            int nx = cur.first.first+dx[d];
            int ny = cur.first.second+dy[d];
            int nk = cur.second;

            if(nx<0||nx>=h||ny<0||ny>=w) continue;
            if(mat[nx][ny]==1) continue;
            if(vis[nx][ny][nk] && vis[nx][ny][nk]<=vis[cur.first.first][cur.first.second][cur.second]+1) continue;

            vis[nx][ny][nk]=vis[cur.first.first][cur.first.second][cur.second]+1;
            if(d<8) { //말처럼 이동
                nk--;
                q.push({{nx,ny},nk});
            }
            else { //상하좌우 이동
                q.push({{nx,ny},nk});
            }
        }
    }
}


int main() {
    ios_base::sync_with_stdio(false); cin.tie(NULL);

    cin>>k >> w >> h;
    for(int i=0; i<h; i++) {
        for(int j=0; j<w; j++) {
            cin>>mat[i][j];
        }
    }

    bfs(0,0);


    int ans= 40001;
    for(int i=0; i<=k; i++) {
        if(vis[h-1][w-1][i]-1!=-1)
            ans=min(ans, vis[h-1][w-1][i]-1);
    }
    if(ans==40001) cout<<-1;
    else cout<<ans;
    return 0;
}
