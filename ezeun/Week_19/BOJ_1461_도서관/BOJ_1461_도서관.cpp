#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;

// 그리디
/* 예제 1번 기준 설명
 * 음수, 양수 나눠서 내림차순 정렬
 * 음수 : 39 37 29 28 6
 * 양수 : 11 2
 *
 * 2개씩 묶으면,
 * 음수 : (39 37) (29 28) (6)
 * 양수 : (11 2)
 * -> (각 묶음의 맨 앞에 있는 수 중 max값) + ((나머지 묶음들의 맨 앞 숫자) * 2의 합)
 */
int main() {

    int n, m; cin >> n >> m;
    vector<int> negative, positive;

    for(int i=0; i<n; i++) {
        int x; cin>>x;
        if(x<0) negative.push_back(x*(-1));
        else positive.push_back(x);
    }
    sort(negative.rbegin(), negative.rend());
    sort(positive.rbegin(), positive.rend());


    int ans=0;

    if(negative.empty()) ans-=positive[0];
    else if(positive.empty()) ans-=negative[0];
    else ans-=max(negative[0], positive[0]);

    for(int i=0; i<negative.size(); i+=m) {
        ans+=negative[i]*2;
    }
    for(int i=0; i<positive.size(); i+=m) {
        ans+=positive[i]*2;
    }
    cout<<ans;
    return 0;
}