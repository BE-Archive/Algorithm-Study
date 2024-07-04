#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;
/*
 * 오븐의 지름이 위에서부터
 * 5 6 4 3 6 2 3 이라고 할 때,
 * 5 5 4 3 3 2 2 인 경우랑 같음.
 * 좁았다가 넓어져봤자 위쪽(좁은 쪽)에서 걸리니까 아래에서 넓어져도 소용이 없기때문.
 *
 * 따라서 5 6 4 3 6 2 3 -> 5 5 4 3 3 2 2 로 저장하고 이분탐색 하자.
 *
 * level |  1 2 3 4 5 6 7
 * pizza |  2 2 3 3 4 5 5 (오름차순 정렬되게 reverse 해주었음)
 * 피자의 지름이 3 2 5 로 주어지는 경우를 살펴보자.
 *
 * 1. 가장 위에있는 피자를 나타내는 top = 0 으로 초기화.
 * 2-1. (top < lower_bound 결과값) 이면, top은 lower_bound 결과값이 됨.
 * 2-2. (top >= lower_bound 결과값) 이면, top은 top+1 이 됨.
 *
 * 3인 피자는, level 3에 들어간다. (top은 0이고 lower_bound 결과는 3 -> top은 3이 됨)
 * 2인 피자는, level 4에 들어간다. (top은 3이고 lower_bound 결과는 1 -> top은 4가 됨)
 * 5인 피자는, level 6에 들어간다. (top은 4이고 lower_bound 결과는 6 -> top은 6이 됨)
 * 따라서 정답은 7(d) - 6(top) + 1 = 2
 */
int oven[300001];
int main() {

    int d,n; cin>>d>>n;
    for(int i=1; i<=d; i++) {
        cin>>oven[i]; //5 6 4 3 6 2 3
        if(i>1 && oven[i]>oven[i-1]) oven[i]=oven[i-1]; // 5 5 4 3 3 2 2
    }
    reverse(oven+1, oven+d+1); // 2 2 3 3 4 5 5

    int top=0;
    for(int i=0; i<n; i++) {
        int p; cin>>p;
        int pos = lower_bound(oven+1, oven+d+1, p)-oven;
        if(top<pos) {
            top=pos;
        }
        else {
            top++;
        }
    }

    if(top>d) cout<<0;
    else cout<<d-top+1;
    return 0;
}
