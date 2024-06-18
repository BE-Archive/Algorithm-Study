#include<iostream>
#include <vector>
using namespace std;

int main(){
    int n;
    vector<int>num;
    int DP[1001]={0,};
    cin>>n;
    for(int i=0;i<n;i++){
        int tmp;
        cin>>tmp;
        num.push_back(tmp);
        DP[i]=tmp;
    }
    int maxim=DP[0];

    for(int i=1;i<=n;i++){
        for(int j=0;j<i;j++){
            if(num[i]>num[j]&&DP[i]<DP[j]+num[i]){
                DP[i]=DP[j]+num[i];
                if(DP[i]>maxim)
                    maxim=DP[i];
            }
        }
    }
    cout<<maxim;
    return 0;
}
