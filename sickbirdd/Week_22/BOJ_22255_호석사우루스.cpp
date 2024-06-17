#include <bits/stdc++.h>
#define pii pair<int, int>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int N, M, S[2], E[2], INF = 1e9, dir[4][2] ={{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    
    cin >> N >> M;
    cin >> S[0] >> S[1] >> E[0] >> E[1];

    int mat[N + 1][M + 1], dist[N + 1][M + 1][3];
    for(int i = 1; i <= N; i++) {
        for(int j = 1; j <= M; j++) {
            cin >> mat[i][j];
            dist[i][j][0] = dist[i][j][1] = dist[i][j][2] = INF; 
        }
    }

    priority_queue<pair<pii, pii>, vector<pair<pii, pii> >, greater<pair<pii, pii> > > pq;
    int ans = INF;
    dist[S[0]][S[1]][1] = 0;
    pq.push({{0, 1}, {S[0], S[1]}});
    while(!pq.empty()) {
        int qsize = pq.size();
        while(--qsize >= 0) {
            pair<pii, pii> now = pq.top();
            int curr = now.first.first, turn = now.first.second, next = (turn + 1) % 3;
            int x = now.second.first, y = now.second.second;
            pq.pop();
            if(x == E[0] && y == E[1]) {
                ans = min(ans, curr);
                continue;
            }
            if(dist[x][y][turn] < curr) continue;
            for(int i = 0; i < 4; i++) {
                if((turn == 1 && i % 2 == 0) || (turn == 2 && i % 2 != 0)) continue;
                int nx = x + dir[i][0], ny = y + dir[i][1];
                if(nx < 1 || ny < 1 || nx > N || ny > M || mat[nx][ny] == -1 || dist[nx][ny][next] <= dist[x][y][turn] + mat[nx][ny])  continue;
                dist[nx][ny][next] = dist[x][y][turn] + mat[nx][ny];
                pq.push({{dist[nx][ny][next], next}, {nx, ny}});
            }
        }
    }
    if(ans == INF) cout << -1;
    else cout << ans;
}