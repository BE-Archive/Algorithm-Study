#include <algorithm>
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

vector<pair<int,int>> line;
int main() {
    ios_base::sync_with_stdio(false); cin.tie(NULL);

    int n; cin>>n;

    for(int i=0; i<n; i++) {
        int x,y; cin>>x>>y;
        line.push_back({x,y});
    }
    sort(line.begin(), line.end());

    int end = line[0].second;
    int ans = line[0].second - line[0].first;

    for(int i=1; i<n; i++) {

        if(line[i].first <= end) {
            if(line[i].second <= end) continue;
            ans += line[i].second - end;
            end = line[i].second;
        }
        else {
            ans += line[i].second - line[i].first;
            end = line[i].second;
        }
    }

    cout<<ans;
    return 0;
}
