#include <iostream>

using namespace std;

int main(){
    int k;
    int result=0;
    cin>>k;
    while(k%5!=0) {
        result++;
        k-=3;
        if(k<3&&k!=0){
            cout<<"-1";
            return 0;
        }
        else if(k==0){
            cout<<result;
            return 0;
        }
    }
    result+=k/5;
    cout<<result;
    return 0;
}