#include <iostream>
#include <deque>
#include <queue>
#include <algorithm>
#include <vector>
using namespace std;


int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int n; cin>>n;
    vector<int> crain;
    vector<int> box;

    for(int i=0; i<n; i++) {
        int x; cin>>x;
        crain.push_back(x);
    }

    int m; cin>>m;
    for(int i=0; i<m; i++) {
        int x; cin>>x;
        box.push_back(x);
    }

    sort(crain.rbegin(), crain.rend());
    sort(box.rbegin(), box.rend());

    int ans=0;
    if(crain[0]<box[0]) {
        ans=-1;
    }
    else {
        while(!box.empty()) {
            ans++;
            for(int i=0; i<n; i++) {
                for(int j=0; j<box.size(); j++) {
                    if(crain[i]>=box[j]) {
                        box.erase(box.begin()+j);
                        break;
                    }
                }
            }
        }
    }

    cout<<ans;
    return 0;
}
