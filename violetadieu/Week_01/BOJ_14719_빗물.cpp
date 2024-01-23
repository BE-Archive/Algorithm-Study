#include <iostream>
#include <map>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cout.tie(NULL);

    int h,w;
    int answer=0;
    cin>>h>>w;

    int rain[501]={0,};

    for(int i=0;i<w;i++){
        cin>>rain[i];
    }

    int left=0;
    int right=0;

    for(int i=1;i<w-1;i++){
        left=0;
        right=0;
        for(int k=0;k<i;k++){
            left=max(left,rain[k]);
        }

        for(int p=w-1;p>i;p--){
            right=max(right,rain[p]);
        }
        answer+=max(0,min(left,right)-rain[i]);
    }

    cout<<answer<<endl;

    return 0;
}
