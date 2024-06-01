#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;

int main() {

    int T; cin>>T;
    for(int i=0; i<T; i++) {
        string s; cin>>s;
        int ans1=0, ans2=0; //0:회문, 1:유사회문, 2:둘다아님

        int l=0, r=s.length()-1;
        while(l<r) {
            if(s[l]!=s[r]) { //데칼코마니 위치의 문자가 서로 다를 때
                if(ans1==1) { //이미 하나 달랐었으면
                    ans1=2; //회문도 유사회문도 아님
                    break;
                }
                else { //지금까지 회문조건 만족했었다면, 유사회문인지 체크
                    if(s[l+1]==s[r]) { //이 조건과
                        ans1=1;
                        l++;
                    }
                    else if(s[l]==s[r-1]) { //이 조건중 어떤걸 먼저 체크하는지가 결과에 영향을 미침
                        ans1=1;
                        r--;
                    }
                    else {
                        ans1=2;
                        break;
                    }
                }
            }
            l++, r--;
        }

        //따라서 조건 순서를 바꿔서 한 번 더 체크해줌
        l=0, r=s.length()-1;
        while(l<r) {
            if(s[l]!=s[r]) {
                if(ans2==1) {
                    ans2=2;
                    break;
                }
                else {
                    if(s[l]==s[r-1]) {
                        ans2=1;
                        r--;
                    }
                    else if(s[l+1]==s[r]) {
                        ans2=1;
                        l++;
                    }
                    else {
                        ans2=2;
                        break;
                    }
                }
            }
            l++, r--;
        }

        cout << min(ans1,ans2)<<'\n';
    }

    return 0;
}
