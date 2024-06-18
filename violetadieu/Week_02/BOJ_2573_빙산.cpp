#include <iostream>
#include <vector>
#include <queue>
using namespace std;
 
int N,M;
int dy[4]={1,-1,0,0};
int dx[4]={0,0,1,-1};
 
vector<vector<int>> map; //빙산 개수
vector<vector<int>> around; //주위 4방향에 바다 몇 군데 인지
vector<vector<int>> visited; //방문했는지
 
void dfs(int y,int x){
    for(int i=0;i<4;i++){
        int ny=y+dy[i];
        int nx=x+dx[i];
        
        if(ny<0 || ny>=N || nx<0 || nx>=M)
            continue;
        
        if(map[ny][nx] && !visited[ny][nx]){
            visited[ny][nx]=1;
            dfs(ny,nx);
        }
    }
    return;
}
 
int main(){
    
    cin>>N>>M;
    
    map=vector<vector<int>> (N,vector<int>(M,0));
    around=vector<vector<int>> (N,vector<int>(M,0));
    visited=vector<vector<int>> (N,vector<int>(M,0));
    
    queue<pair<int,int>> q; //빙산 있는 지역 큐에 담는다
    
    for(int i=0;i<N;i++)
        for(int j=0;j<M;j++){
            scanf("%d",&map[i][j]);
            if(map[i][j])
                q.push(make_pair(i,j));
        }
    
    int ans=0; //시간
    bool check=false; //빙산 2개 이상 있는지 체크
    while(!q.empty()){
        
        ans++; //시간
        while(!q.empty()){
            int y=q.front().first;
            int x=q.front().second;
            q.pop();
            
            for(int i=0;i<4;i++){
                int ny=y+dy[i];
                int nx=x+dx[i];
                
                if(ny<0 || ny>=N ||nx<0 || nx>=M)
                    continue;
                
                //주위에 바다 있는지
                if(map[ny][nx]==0)
                    around[y][x]++;
            }
        }
        
        //빙산 높이 업데이트
        for(int i=0;i<N;i++)
            for(int j=0;j<M;j++){
                map[i][j]=(map[i][j]-around[i][j]>=0)? map[i][j]-around[i][j]:0;
                if(map[i][j])
                    q.push(make_pair(i,j));
            }
        
        //빙산 개수
        int cnt=0;
        for(int i=0;i<N;i++)
            for(int j=0;j<M;j++){
                if(map[i][j] && !visited[i][j]){
                    visited[i][j]=1;
                    cnt++;
                    dfs(i,j);
                }
            }
        
        
        //빙하 2개 이상 있을 때 나온다
        if(cnt>1){
            check=true;
            break;
        }
        
        visited=vector<vector<int>> (N,vector<int>(M,0));
        around=vector<vector<int>> (N,vector<int>(M,0));
        
    }
    
    if(check==false)
        ans=0;
    
    cout<<ans<<endl;
    return 0;
}