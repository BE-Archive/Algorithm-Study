#include <iostream>
#include <vector>
#include <algorithm>
#include <cstring>
using namespace std;

int maximum=0;
vector<vector<pair<int,int>>>adj(10001);
bool visit[10001]={false,};

void DFS(int now,int total_cost){
    if(maximum<total_cost){
        maximum=total_cost;
    }
    //maximum=max(maximum,total_cost);
    for(auto p : adj[now]){
        if(visit[p.first]){
            continue;
        }
        total_cost+=p.second;
        visit[p.first]=true;
        DFS(p.first,total_cost);
        visit[p.first]= false;
        total_cost-=p.second;
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int n=0;
    cin>>n;
    for(int i=1;i<n;i++){
        int start,goal,cost;
        cin>>start>>goal>>cost;
        adj[start].emplace_back(goal,cost);
        adj[goal].emplace_back(start,cost);
    }

    for(int i=1;i<=n;i++){
        memset(visit,false,sizeof(bool)*10000);
        visit[i]=true;
        DFS(i,0);
        visit[i]=false;
    }

    cout<<maximum<<endl;
    return 0;
}
