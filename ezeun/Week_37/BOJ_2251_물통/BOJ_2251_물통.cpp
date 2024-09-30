#include <iostream>
#include <queue>
#include <set>
using namespace std;

int vis[201][201][201]; //vis[i][j][k]: A,B,C에 각각 i,j,k리터 있는 경우를 체크했는지
struct Water {
    int a,b,c; //물통 A,B,C에 들어있는 물의 양
};
set<int> results;

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    int A,B,C; cin>>A>>B>>C; //물통의 부피

    queue<Water> q;
    Water w = {0,0,C};
    vis[0][0][C]=1;
    q.push(w);

    while(!q.empty()) {
        Water cur = q.front(); q.pop();
        Water next;

        if (cur.a == 0) {
            results.insert(cur.c);
        }

        //A->B
        if(B-cur.b >= cur.a) next = {0, cur.b+cur.a, cur.c};
        else next = {cur.a-(B-cur.b), B, cur.c};
        if(!vis[next.a][next.b][next.c]) {
            vis[next.a][next.b][next.c]=1;
            q.push(next);
        }
        //A->C
        if(C-cur.c >= cur.a) next = {0, cur.b, cur.c+cur.a};
        else next = {cur.a-(C-cur.c), cur.b, C};
        if(!vis[next.a][next.b][next.c]) {
            vis[next.a][next.b][next.c]=1;
            q.push(next);
        }
        //B->A
        if(A-cur.a >= cur.b) next = {cur.a+cur.b, 0, cur.c};
        else next = {A, cur.b-(A-cur.a), cur.c};
        if(!vis[next.a][next.b][next.c]) {
            vis[next.a][next.b][next.c]=1;
            q.push(next);
        }
        //B->C
        if(C-cur.c >= cur.b) next = {cur.a, 0, cur.c+cur.b};
        else next = {cur.a, cur.b-(C-cur.c), C};
        if(!vis[next.a][next.b][next.c]) {
            vis[next.a][next.b][next.c]=1;
            q.push(next);
        }
        //C->A
        if(A-cur.a >= cur.c) next = {cur.a+cur.c, cur.b, 0};
        else next = {A, cur.b, cur.c-(A-cur.a)};
        if(!vis[next.a][next.b][next.c]) {
            vis[next.a][next.b][next.c]=1;
            q.push(next);
        }
        //C->B
        if(B-cur.b >= cur.c) next = {cur.a, cur.b+cur.c, 0};
        else next = {cur.a, B, cur.c-(B-cur.b)};
        if(!vis[next.a][next.b][next.c]) {
            vis[next.a][next.b][next.c]=1;
            q.push(next);
        }
    }

    for (int result : results) {
        cout << result << " ";
    }
    return 0;
}