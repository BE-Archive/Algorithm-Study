#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int MF[4] = {0,0,-1,1};
int MS[4]={-1,1,0,0};


int plant[1005][1005];
bool visit[1005][1005]={false,};

int main() {

    cin.tie(NULL);
    cout.tie(NULL);
    ios::sync_with_stdio(false);

    int n,m;
    cin>>n>>m;
    priority_queue<pair<int,pair<int,int>>,vector<pair<int,pair<int,int>>>>pq;

    for(int i=0;i<n;i++){
        for(int k=0;k<m;k++){
            cin>>plant[i][k];
            //시작할 수 있는 위치(테두리 저장)
            if(i==0||k==0||i==n-1||k==m-1){
                pair<int,pair<int,int>>now;
                now.second=make_pair(i,k);
                now.first=plant[i][k];
                pq.push(now);
                visit[i][k]= true;
            }
        }
    }
    int k;
    cin>>k;
    vector<pair<int,int>>result;
    while(k--){
        if(pq.empty())
            break;
        pair<int,pair<int,int>> now=pq.top();
        pq.pop();
        result.emplace_back(now.second.first+1,now.second.second+1);
        for(int i=0;i<4;i++){
            int newF=now.second.first+MF[i];
            int newS=now.second.second+MS[i];

            if(0<=newF&&newF<n&&0<=newS&&newS<m&&!visit[newF][newS]){
                pq.emplace(plant[newF][newS],make_pair(newF,newS));
                visit[newF][newS]= true;
            }

        }
    }
    for(auto p : result){
        cout<<p.first<<" "<<p.second<<endl;
    }
    return 0;
}