#include<iostream>

using namespace std;
int main(){

    cin.tie(NULL);
    cout.tie(NULL);

    int n;
    int Dp[100000]={0,};

    cin>>n;
//초깃값

    Dp[1]=3%9901;
    Dp[2]=7%9901;
//3부터 n까지

    for(int i=3;i<=n;i++){
        Dp[i]=((2*Dp[i-1])+Dp[i-2])%9901;
    }

    cout<<Dp[n]<<endl;

    return 0;
}