#include <iostream>
#include <vector>
#include <climits>
#include <algorithm>
#include <queue>
using namespace std;
typedef pair<int,int> pii;
typedef long long ll;

int n;
string A; //1이면 실내(검정)
vector<int> adj[200001];
int vis[200001];
ll ans = 0;

ll dfs(int i) { //실내 나올때까지 탐색

    ll inside = 0;
    vis[i] = 1;

    for(auto k : adj[i]) {
        if(A[k-1]=='1') inside++; //실내이면 갯수 증가
        else if(A[k-1]=='0' && !vis[k]) inside+=dfs(k); //실외이면 탐색

    }

    return inside;
}

int main() {

    cin >> n >> A;
    for(int i=0; i<n-1; i++) {
        int a,b; cin>>a>>b;
        adj[a].push_back(b);
        adj[b].push_back(a);
        if(A[a-1]=='1' && A[b-1]=='1') ans+=2;
    }

    for(int i=1; i<=n; i++) {
        if(A[i-1]=='0' && !vis[i]) { //방문하지 않은 실외일때만 탐색
            ll cnt = dfs(i); //실내 노드 개수 반환
            ans += cnt*(cnt-1);
        }
    }
    cout<<ans;
    return 0;
}
