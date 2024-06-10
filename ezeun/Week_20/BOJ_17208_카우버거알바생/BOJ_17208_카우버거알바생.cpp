#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;

int dp[301][301][101]; // i개의 버거, j개의 감튀로 k번째 주문까지 최대 몇개의 주문 처리 가능한지
int burger[101], potato[101];
int main() {

    int N, M, K; cin >> N>> M>> K;
    for(int i=1; i<=N; i++) {
        cin>> burger[i] >> potato[i];
    }

    for(int k=1; k<=N; k++) { //주문
        for(int i=1; i<=M; i++) { //버거
            for(int j=1; j<=K; j++) { //감튀
                if(i-burger[k]>=0 && j-potato[k]>=0)
                    dp[i][j][k] = max(dp[i][j][k-1], dp[i-burger[k]][j-potato[k]][k-1]+1);
                else
                    dp[i][j][k] = dp[i][j][k-1];
            }
        }
    }

    cout<<dp[M][K][N];
    return 0;
}

/*
 * 예제 1 에서
 * dp[3][4][4] = 버거 3개, 감튀 4개로 4번째 주문까지 최대 2개의 주문 처리 가능
 *             = max(dp[3][4][3], dp[3-2][4-1][4-1]+1)
 *             => 4번째 주문을 처리하지 않느냐, 처리하느냐
 * -> 4번째 주문을 처리하지 않는다면
 *    dp[3][4][3] = 버거 3개, 감튀 4개로 3번째 주문까지 최대 2개의 주문 처리 가능
 * -> 4번째 주문을 처리한다면
 *    dp[3-2][4-1][4-1] = 버거 1개, 김튀 3개로 3번째 주문까지 최대 1개의 주문 처리 가능
 *    에다가 처리 횟수 1 증가
 */
