#include <iostream>
#include <deque>
#include <map>
#include <vector>
using namespace std;


int count[51];
int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);


    int t; cin>>t;
    while(t--) {
        vector<int> idx[26];

        string s; cin>>s;
        for(int i=0; i<s.length(); i++) {
            idx[s[i]-'a'].push_back(i);
        }

        int k; cin>>k;
        int mn=10001, mx=-1;
        for(int i=0; i<26; i++) {
            if(idx[i].size()>=k) { // 0 1 2 3 4
                for(int j=k-1; j<idx[i].size(); j++) {
                    int gap = idx[i][j]-idx[i][j-k+1]+1;
                    if(gap<mn) mn=gap;
                    if(gap>mx) mx=gap;
                }
            }
        }
        if(mx==-1) cout<<-1<<'\n';
        else cout<< mn<<" "<<mx<<'\n';
    }
    return 0;
}