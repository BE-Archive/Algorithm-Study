#include <iostream>
#include <vector>
#include <climits>
#include <algorithm>
#include <queue>
#include <map>
#include <list>
using namespace std;
typedef pair<int,int> pii;
typedef long long ll;

/*
 * 친구관계 그래프
 * 한 사람이 다른 사람의 페이지를 방문했을 때, 친구 관계 그래프에서 두 사람 사이의 경로를 보여주는 기능
 * 경로가 없는 경우에는 보여주지 않음
 * -> 두 사람 사이의 경로가 존재하는지 안 하는지를 미리 구해보려고 한다
 *
 * m이 10^5이라서 다익스트라 불가능
 * n이 10^6이라서 플로이드와샬 불가능
 * -> 친구인지 아닌지만 알면되니까 분리집합을 이용!
 */
int T, n, k, m;
int par[1000001];

int find(int num) {
    if(num==par[num]) return num; //root
    return par[num] = find(par[num]);
}
void unite(int a, int b) {
    a=find(a), b=find(b);
    par[b]=a;
}

int main()
{
    ios::sync_with_stdio(false); cin.tie(NULL);

   cin>>T;
    for(int tc=1; tc<=T; tc++) {
        cout<< "Scenario " << tc<<":\n";

        cin>>n; //유저의 수
        cin>>k; //친구관계의 수

        for(int i=0; i<n; i++) par[i]=i;

        for(int i=0; i<k; i++) {
            int a,b; cin>>a>>b; //친구관계
            unite(a,b);
        }
        cin>>m; //미리 구할 쌍의 수
        for(int i=0; i<m; i++) {
            int u,v; cin>>u>>v;
            if(find(u)==find(v)) cout<<"1\n";
            else cout<<"0\n";
        }
        cout<<'\n';
    }


    return 0;
}