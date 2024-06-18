#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;
typedef pair<int,int> pii;

int n, m;
char MW[1001];

int par[1001], vis[1001];
vector<pair<int, pii>> road; //도로정보 {{가중치, {정점1, 정점2}}
vector<int> node[1001]; //dfs로 불가능 체크

int ans=0;

void input() {

    cin>>n>>m;

    for(int i=1; i<=n; i++) par[i]=i;

    for(int i=1; i<=n; i++) {
        cin>>MW[i];
    }
    for(int i=0; i<m; i++) {
        int u, v, d;
        cin>>u>>v>>d;
        if(MW[u]==MW[v]) continue; //남-남, 여-여 도로 제외

        road.push_back({d, {u,v}});
        node[u].push_back(v);
        node[v].push_back(u);
    }
}

void dfs(int x) {
    vis[x]=1;
    for(int i=0; i<node[x].size(); i++) {
        if(!vis[node[x][i]])
            dfs(node[x][i]);
    }
}
bool check_possible() { //모든 도시가 연결 될 수 있는지 체크
    dfs(1);
    for(int i=1; i<=n; i++) {
        if(!vis[i]) return false;
    }
    return true;
}

int find(int x) {
    if(x==par[x]) return x;
    return par[x] = find(par[x]);
}
void unite(int cost, int a, int b) {
    int pa = find(a), pb = find(b); //연결하려는 정점들의 루트찾기
    if(pa==pb) return; //이미 같은 집합이면 사이클 발생하므로 연결 불가
    par[pb]=pa;
    ans+=cost;
}
void mst() {
    sort(road.begin(), road.end()); //가중치 기준 오름차순 정렬
    for(auto r : road) {
        unite(r.first, r.second.first, r.second.second);
    }
}

int main() { // 남-남, 여-여 도로 제외하고 mst 구하기

    input();

    if(!check_possible()) {
        cout<< -1;
    }
    else {
        mst();
        cout<<ans;
    }

    return 0;
}
