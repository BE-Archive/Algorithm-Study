#include <algorithm>
#include <iostream>
#include <vector>
#include <climits>
using namespace std;

// N 개의 건물과 M 개의 도로
// 2개의 건물을 골라서 치킨집 개업 (건물번호 작을수록 우선순위 높음)
// 모든 건물에서 가장 가까운 치킨집까지 왕복하는 최단 시간의 총합
// -> 플로이드 와샬로 최단거리 구해놓고 100C2로 모든 조합 체크

int n, m;
int dist[101][101];

int main() {

    /////// 입력받고 플로이드 와샬

    cin >> n >> m;

    for(int i=1; i<=n; i++) {
        for(int j=1; j<=n; j++) {
            if(i==j) continue;
            dist[i][j]=INT_MAX;
        }
    }

    for(int i=0; i<m; i++) {
        int a,b; cin>>a>>b;
        dist[a][b]=1;
        dist[b][a]=1;
    }

    for(int k=1; k<=n; k++) {
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(dist[i][k]!=INT_MAX && dist[k][j]!=INT_MAX) {
                    dist[i][j]=min(dist[i][j], dist[i][k]+dist[k][j]);
                }
            }
        }
    }

    //////// 치킨집 조합하며 정답 구하기

    int building1, building2, ans=INT_MAX;

    for(int i=n; i>=1; i--) {
        for(int j=n; j>=1; j--) {
            if(i==j) continue;
            int sum=0;
            for(int k=1; k<=n; k++) {
                sum+=min(dist[k][i], dist[k][j]);
            }
            if(sum<=ans) {
                building1=i, building2=j;
                ans=sum;
            }
        }
    }

    cout<< building1<<" "<<building2<<" "<<ans*2;

    return 0;
}
