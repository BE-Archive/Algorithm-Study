#include <bits/stdc++.h>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int N, d, k, c;
    cin >> N >> d >> k >> c;

    int sushi[N], freq[d + 1] = {}, count = 0;
    for(int i = 0; i < N; i++) {
        cin >> sushi[i];
        if(i < k && ++freq[sushi[i]] == 1) {
            count++;
        }
    }
    int ans = 0;
    for(int i = 0; i < N; i++) {
        ans = max(ans, count + (freq[c] == 0));
        if(freq[sushi[i]] > 0 && --freq[sushi[i]] == 0) {
            count--;
        }
        if(++freq[sushi[(i + k) % N]] == 1) {
            count++;
        }
    }
    cout << ans;
}