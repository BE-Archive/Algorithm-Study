#include <bits/stdc++.h>
using namespace std;

int N, mat[1000][1000], dp[1000][1000][3];
int foo(int row, int col, int status) {
    if(row == N - 1 && col == N - 1) return mat[row][col] == status;
    if(dp[row][col][status] != -1) return dp[row][col][status];
    int next = (status + 1) % 3, add = mat[row][col] == status;
    dp[row][col][status] = 0;
    if(row < N - 1) {
        dp[row][col][status] = max(dp[row][col][status], foo(row + 1, col, status));
        if(add == 1) dp[row][col][status] = max(dp[row][col][status], foo(row + 1, col, next) + add);
    }
    if(col < N - 1) {
        dp[row][col][status] = max(dp[row][col][status], foo(row, col + 1, status));
        if(add == 1) dp[row][col][status] = max(dp[row][col][status], foo(row, col + 1, next) + add);
    }
    return dp[row][col][status];
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cin >> N;

    for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
            cin >> mat[i][j];
            dp[i][j][0] = dp[i][j][1] = dp[i][j][2] = -1;
        }
    }
    cout << foo(0, 0, 0);
}