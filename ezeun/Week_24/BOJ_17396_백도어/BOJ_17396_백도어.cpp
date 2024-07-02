#include <iostream>
#include <vector>
#include <climits>
#include <queue>
using namespace std;
typedef pair<int,int> pii;

int n,m;
int canSee[100001];
vector<pii> adj[100001];
long long dist[100001];
int vis[100001];
priority_queue<pii, vector<pii>, greater<>> pq; //min heap {dist, 정점}

int main() {

    //init

    cin>>n>>m;
    for(int i=0; i<n; i++) cin>>canSee[i];

    for(int i=0; i<m; i++) {
        int a,b,t; cin>>a>>b>>t;
        if(canSee[a] && a!=n-1) continue;
        if(canSee[b] && b!=n-1) continue;
        adj[a].push_back({b,t}); //{정점, dist}
        adj[b].push_back({a,t});
    }

    fill(dist, dist+100001, LONG_MAX);



    //dijkstra

    dist[0]=0;
    pq.push({0,0});
    while(!pq.empty()) {
        int cur=pq.top().second;
        pq.pop();

        if(vis[cur]) continue;
        vis[cur]=1;

        for(pii &k : adj[cur]) {
            int next = k.first;
            int cost = k.second;
            long long next_dist = dist[cur]+cost;
            if(dist[next]>next_dist) {
                dist[next]=next_dist;
                pq.push({dist[next], next});
            }
        }
    }

    if(dist[n-1]==LONG_MAX) cout<<-1;
    else cout<<dist[n-1];
    return 0;
}
