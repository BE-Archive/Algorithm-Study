#include <iostream>
#include <queue>
#include <set>
using namespace std;

int dp[10001];
int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    for(int i=0; i<=10000; i++) {
        dp[i]=1;
    }
    for(int i=2; i<=10000; i++) {
        dp[i]+=dp[i-2];
    }
    for(int i=3; i<=10000; i++) {
        dp[i]+=dp[i-3];
    }

    int t; cin>>t;
    while(t--) {
        int n; cin>>n;
        cout<<dp[n]<<'\n';
    }
    
    return 0;
}