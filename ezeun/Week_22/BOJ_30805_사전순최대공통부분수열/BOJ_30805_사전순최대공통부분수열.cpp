#include <algorithm>
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

vector<pair<int,int>> A, B;
int n,m;
vector<int> answer;

bool cmp (const pair<int,int> &a, const pair<int,int> &b) {
    if(a.first!=b.first) return a.first>b.first;
    else return a.second<b.second;
}

int main() {

    //입력받기
    cin>>n;
    for(int i=0; i<n; i++) {
        int a; cin>>a;
        A.push_back({a,i});
    }
    cin>>m;
    for(int i=0; i<m; i++) {
        int b; cin>>b;
        B.push_back({b,i});
    }

    sort(A.begin(), A.end(), cmp);
    sort(B.begin(), B.end(), cmp);

    int a=0, b=0, Aidx=-1, Bidx=-1;
    while(a<n && b<m) {
        if(A[a].first==B[b].first) {
            if(A[a].second>Aidx && B[b].second>Bidx) {
                Aidx=A[a].second;
                Bidx=B[b].second;
                answer.push_back(A[a].first);
                a++, b++;
            }
            else {
                if(A[a].second<Aidx) a++;
                if(B[b].second<Bidx) b++;
            }
        }
        else if(A[a].first>B[b].first) a++;
        else if(A[a].first<B[b].first) b++;
    }

    cout<<answer.size()<<'\n';
    for(int k :answer) cout<<k<<" ";
    return 0;
}