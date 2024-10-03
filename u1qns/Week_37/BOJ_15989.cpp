#include <iostream>
#include <vector>
using namespace std;

const int MAX = 10001;
vector<vector<int>> DP;

void solve()
{
    DP.resize(MAX, vector<int>(4, 0));
    DP[0][1] = DP[1][1] = DP[2][1] = DP[2][2] = 1;

    for(int i=3; i<MAX; ++i)
    {
  		DP[i][1] = DP[i - 1][1];
  		DP[i][2] = DP[i - 2][1] + DP[i - 2][2];
  		DP[i][3] = DP[i - 3][1] + DP[i - 3][2] + DP[i - 3][3];
    }
}

int main() 
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    
    int T, n;
    cin >> T;
    
    solve();
    
    while(T--)
    {
        cin >> n;
        cout << DP[n][1] + DP[n][2] + DP[n][3]  << "\n";
    }
    
    return 0;
}
