#include <iostream>
#include <vector>
#include <climits>
#include <algorithm>
#include <queue>
using namespace std;
typedef pair<int,int> pii;
typedef long long ll;

// 한 기업에 나누어 투자 불가
int n,m; //투자금액, 기업개수
int money[301][21]; // 투자액수, 기업 -> 이익
int dp[301][21]; //투자금액 i원, 기업의 수 j일때의 최대이익
int trace[21];
int main() {

    //init
    cin >> n >> m;
    for(int i=1; i<=n; i++) {
        int x; cin>>x;
        for(int j=1; j<=m; j++) {
            cin>> money[i][j];
        }
    }

    //m=1
    for(int i=1; i<=n; i++) {
        dp[i][1]=money[i][1];
    }

    //m>=2
    for(int i=1; i<=n; i++) {
        for(int j=2; j<=m; j++) { //투자금액 i원, 기업 j개
            for(int k=0; k<=i; k++) {
                if(dp[i][j]<dp[k][j-1]+money[i-k][j]) {
                    dp[i][j]=dp[k][j-1]+money[i-k][j];
                    if(j==2) trace[j-1]=k;
                    trace[j]= i-k;
                }
                // dp[i][j] = max(dp[i][j], dp[k][j-1]+money[i-k][j]);
                // cout<< i<<" "<<j<<" "<<k<<" "<<dp[i][j]<<'\n';
            }
        }
    }
    cout<< dp[n][m]<<'\n';
    for(int j=1; j<=m; j++) cout<<trace[j]<<" ";
    return 0;
}
