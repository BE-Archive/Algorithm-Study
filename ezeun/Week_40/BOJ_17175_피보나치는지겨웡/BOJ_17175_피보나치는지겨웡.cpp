#include <iostream>
using namespace std;


int count[51];
int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n; cin>>n;
    count[0]=1;
    count[1]=1;
    for(int i=2; i<51; i++) {
        count[i] = count[i-2]%1000000007 + count[i-1]%1000000007 + 1;
    }
    cout<< count[n]%1000000007;
    return 0;
}