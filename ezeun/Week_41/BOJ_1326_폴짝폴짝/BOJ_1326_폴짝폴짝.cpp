#include <iostream>
#include <deque>
#include <map>
#include <queue>
#include <vector>
using namespace std;

int n,a,b;
int bridge[10001];
int vis[10001];

void bfs(int st) {
    queue<int> q; //1based 인덱스 넣는 곳
    q.push(st);
    vis[st]=1;

    while(!q.empty()) {
        int cur = q.front(); q.pop();

        //앞으로 이동
        for(int i=1; (cur+bridge[cur]*i)<=n; i++) {
            if((cur+bridge[cur]*i)<10001) {
                if(!vis[(cur+bridge[cur]*i)]) {
                    q.push((cur+bridge[cur]*i));
                    vis[(cur+bridge[cur]*i)] = vis[cur]+1;
                }
            }
        }
        //뒤로 이동
        for(int i=1; (cur-bridge[cur]*i)>=1; i++) {
            if((cur-bridge[cur]*i)>=1) {
                if(!vis[(cur-bridge[cur]*i)]) {
                    q.push((cur-bridge[cur]*i));
                    vis[(cur-bridge[cur]*i)] = vis[cur]+1;
                }
            }
        }
    }
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);


    cin>>n;
    for(int i=1; i<=n; i++) cin>>bridge[i];
    cin>>a>>b;

    bfs(a);

    cout<<vis[b]-1;
    return 0;
}
