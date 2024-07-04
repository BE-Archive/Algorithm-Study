#include <bits/stdc++.h>
#define MAX_DICE 6
using namespace std;
int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    long long N, max_val = 0L, sum = 0L, dice[6], min_val[3] = {50L, 100L, 150L};
    cin >> N;
    
    for(int i = 0; i < MAX_DICE; i++) {
        cin >> dice[i];
        min_val[0] = min(dice[i], min_val[0]);
        max_val = max(dice[i], max_val);
        sum += dice[i];
    }
    
    for(int i = 0; i < MAX_DICE; i++) {
        for(int j = i + 1; j < MAX_DICE; j++) {
            if(i + j == MAX_DICE - 1) continue;
            min_val[1] = min(dice[i] + dice[j], min_val[1]);
            for(int k = j + 1; k < MAX_DICE; k++) {
                if(j + k == MAX_DICE - 1 || i + k == MAX_DICE - 1) continue;
                min_val[2] = min(dice[i] + dice[j] + dice[k], min_val[2]);
            }
        }
    }

    if(N == 1) cout << sum - max_val;
    else if(N == 2) cout << (min_val[1] + min_val[2]) * 4;
    else cout << 5 * min_val[0] * (N - 2) * (N - 2) + 4 * (min_val[1] + min_val[2]) + 8 * (N - 2) * min_val[1] + 4 * (N - 2) * min_val[0];
}