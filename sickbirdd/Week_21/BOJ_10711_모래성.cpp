#include <bits/stdc++.h>
using namespace std;
int dir[8][2] = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int H, W;
    cin >> H >> W;
    char castle[H][W];

    for(int i = 0; i < H; i++) {
        for(int j = 0; j < W; j++) {
            cin >> castle[i][j];
        }
    }   
    
    queue<pair<int, int> > q;

    for(int i = 0; i < H; i++) {
        for(int j = 0; j < W; j++) {
            if(castle[i][j] == '.') continue;
            int limit = castle[i][j] - '0';
            for(int k = 0; k < 8; k++) {
                int ny = i + dir[k][0], nx = j + dir[k][1];
                if(castle[ny][nx] != '.') continue;
                limit--;
            }
            if(limit <= 0) {
                castle[i][j] = '0';
                q.push(make_pair(i, j));
            }
            else castle[i][j] = limit + '0';
        }
    }

    int ans = 0;
    while(!q.empty()) {
        int size = q.size();
        while(--size >= 0) {
            pair<int, int> now = q.front();
            q.pop();
            for(int i = 0; i < 8; i++) {
                int ny = now.first + dir[i][0], nx = now.second + dir[i][1];
                if(castle[ny][nx] == '.' || castle[ny][nx] == '0') continue;
                if(--castle[ny][nx] == '0') {
                    q.push(make_pair(ny, nx));
                }
            }
        }
        ans++;
    }

    cout << ans;
}