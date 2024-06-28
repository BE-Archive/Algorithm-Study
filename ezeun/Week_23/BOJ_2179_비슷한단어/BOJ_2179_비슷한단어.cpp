#include <algorithm>
#include <iostream>
#include <vector>
#include <map>
using namespace std;

vector<string> v;
map<string,int> m1;
map<int,string> m2;

bool cmp(pair<int,pair<int,int>> const &x, pair<int,pair<int,int>> const &y) {
    if(x.first!=y.first) return x.first>y.first;
    else if(x.second.first!=y.second.first) return x.second.first<y.second.first;
    else return x.second.second<y.second.second;
}

int main() {
    ios_base::sync_with_stdio(false); cin.tie(NULL);

    int n; cin>>n;
    for(int i=0; i<n; i++) {
        string s; cin>>s;
        v.push_back(s);
        m1.insert({s,i});
        m2.insert({i,s});
    }
    sort(v.begin(), v.end()); //정렬하면 비슷한 단어끼리 뭉침

// for(auto k : v) cout<<k<<" "; cout<<'=====\n';

    vector<pair<int,pair<int,int>>> answer;
    //앞에서부터 두단어씩 비교하면서 접두사길이, 입력순서min, 입력순서max를 저장
    string temp = ""; //이전 접두사
    for(int i=0; i<n-1; i++) {
        string a = v[i], b = v[i+1];
        int len = min(a.length(), b.length());

        int common=0; //접두사 길이
        string prefix = ""; //현재 접두사
        for(int j=0; j<len; j++) {
            if(a[j]!=b[j]) break;
            common++;
            prefix+=a[j];
        }

        int aNum = m1[a], bNum = m1[b];
        answer.push_back({common, {min(aNum,bNum), max(aNum,bNum)}});

        if(i!=0 && prefix==temp) { //반례 해결
            a = v[i-1], b = v[i+1];
            aNum = m1[a], bNum = m1[b];
            answer.push_back({common, {min(aNum,bNum), max(aNum,bNum)}});
        }

        temp = prefix;
    }
    sort(answer.begin(), answer.end(), cmp); //접두사 긴 순, 입력순서 min이 작은순, 입력순서 max가 작은순 정렬

    cout<<m2[answer[0].second.first]<<'\n'<<m2[answer[0].second.second];
    return 0;
}