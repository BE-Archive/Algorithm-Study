#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;

int player[11][11];
int position[11];
int ans, sum;

void bt(int i) {
    if(i==11) {
        ans=max(ans,sum);
        return;
    }
    for(int j=0; j<11; j++) {
        if(player[i][j] && !position[j]) {
            sum+=player[i][j];
            position[j]=1;
            bt(i+1);
            sum-=player[i][j];
            position[j]=0;
        }
    }
}

int main() {

    int tc; cin>>tc;
    for(int t=0; t<tc; t++) {
        for(int i=0; i<11; i++) {
            for(int j=0; j<11; j++) {
                cin>>player[i][j];
            }
        }
        for(int j=0; j<11; j++) position[j]=0;

        ans=0, sum=0;
        bt(0);
        cout<<ans<<'\n';
    }
    return 0;
}

