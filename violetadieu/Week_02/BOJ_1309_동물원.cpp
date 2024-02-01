#include<iostream>

using namespace std;
int main(){
    int n;
    int DP[100000]={0,};
    cin>>n;
    DP[0]=0%9901;
    DP[1]=3%9901;
    DP[2]=7%9901;

    for(int i=3;i<=n;i++){
        DP[i]=((2*DP[i-1])+DP[i-2])%9901;
    }
    cout<<DP[n];
    return 0;
}