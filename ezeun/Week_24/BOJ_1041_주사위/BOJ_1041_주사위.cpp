#include <iostream>
#include <vector>
#include <climits>
#include <algorithm>
#include <queue>
using namespace std;
typedef pair<int,int> pii;
typedef long long ll;

int main() {

    ll N; cin>>N;
    vector<ll> v;
    ll minOne = 51, minTwo, minThree;
    ll ans =0;

    for(int i=0; i<6; i++) {
        ll x; cin>>x;
        v.push_back(x);
        minOne = min(minOne,x);
    }

    if(N==1) {
        sort(v.begin(), v.end());
        for(int i=0; i<5; i++) ans+=v[i];
        cout<<ans;
        return 0;
    }

    vector<ll> findMin;

    findMin.push_back(v[0]+v[3]+v[4]);
    findMin.push_back(v[3]+v[4]+v[5]);
    findMin.push_back(v[0]+v[1]+v[3]);
    findMin.push_back(v[1]+v[3]+v[5]);
    findMin.push_back(v[0]+v[2]+v[4]);
    findMin.push_back(v[2]+v[4]+v[5]);
    findMin.push_back(v[0]+v[1]+v[2]);
    findMin.push_back(v[1]+v[2]+v[5]);
    sort(findMin.begin(), findMin.end());
    minThree = findMin[0];

    findMin.clear();
    findMin.push_back(v[0]+v[1]);
    findMin.push_back(v[0]+v[2]);
    findMin.push_back(v[0]+v[3]);
    findMin.push_back(v[0]+v[4]);
    findMin.push_back(v[1]+v[2]);
    findMin.push_back(v[1]+v[3]);
    findMin.push_back(v[1]+v[5]);
    findMin.push_back(v[2]+v[4]);
    findMin.push_back(v[2]+v[5]);
    findMin.push_back(v[3]+v[4]);
    findMin.push_back(v[3]+v[5]);
    findMin.push_back(v[4]+v[5]);
    sort(findMin.begin(), findMin.end());
    minTwo = findMin[0];

    ans += 4 * minThree;
    ans+= (5 * (N-2)*(N-2) + 4 * (N-2)) * minOne;
    ans += (((4*N-4)*N + (N-2)*(N-2)) - (5 * (N-2)*(N-2) + 4 * (N-2)) - 4) * minTwo;

    // cout<<(5 * (N-2)*(N-2) + 4 * (N-2))<<" "<<minOne<<"\n";
    // cout<<(((4*N-4)*N + (N-2)*(N-2)) - (5 * (N-2)*(N-2) + 4 * (N-2)) - 4)<<" "<<minTwo<<"\n";
    // cout<<4<<" "<<minThree<<"\n";
    cout<<ans;
    return 0;
}
