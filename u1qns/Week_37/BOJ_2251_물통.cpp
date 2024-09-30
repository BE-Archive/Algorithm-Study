#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

const int MAX = 201;

int A, B, C;
bool visited[MAX][MAX][MAX] = {0, };
vector<int> answer;

void solve(const int a, const int b, const int c)
{
    visited[a][b][c] = true;
    if(a == 0) answer.push_back(c);
    
    int tmp = 0;       
    // a -> c
    tmp = (a >= C-c ? C-c : a);
    if(a-tmp >= 0 && c+tmp <=C && !visited[a-tmp][b][c+tmp]) 
        solve(a-tmp, b, c+tmp);
        
    // a -> b
    tmp = (a >= B-b ? B-b : a);
    if(a-tmp >= 0 && b+tmp <=B && !visited[a-tmp][b+tmp][c]) 
        solve(a-tmp, b+tmp, c);
        
    // b -> c
    tmp = (b >= C-c ? C-c : b);
    if(b-tmp >= 0 && c+tmp <=C && !visited[a][b-tmp][c+tmp]) 
        solve(tmp, b-tmp, c+tmp);
        
    // b -> a
    tmp = (b >= A-a ? A-a : b);
    if(b-tmp >= 0 && a+tmp <=A && !visited[a+tmp][b-tmp][c]) 
        solve(a+tmp, b-tmp, c);
    
    // c -> a
    tmp = (c >= A-a ? A-a : c);
    if(c-tmp >= 0 && a+tmp <=A &&!visited[a+tmp][b][c-tmp]) 
        solve(a+tmp, b, c-tmp);
    
    // c -> b
    tmp = (c >= B-b ? B-b : c);
    if(c-tmp >= 0 && b+tmp <=B &&!visited[a][b+tmp][c-tmp]) 
        solve(a, b+tmp, c-tmp);
}

int main() 
{
    cin >> A >> B >> C;
    
    solve(0, 0, C);
    sort(answer.begin(), answer.end());
    for(const auto ans : answer)
        cout << ans << " ";
    return 0;
}
