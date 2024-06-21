#include <bits/stdc++.h>
#define pii pair<int, int>
using namespace std;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    int N;
    cin >> N;

    pii lines[N];
    for(int i = 0; i < N; i++) {
        int x, y;
        cin >> x >> y;
        lines[i] = {x, y};
    }
    sort(lines, lines + N);

    int S = lines[0].first, E = lines[0].second, ans = 0;
    for(int i = 0; i < N; i++) {
        if(lines[i].first >= E) {
            ans += E - S;
            S = lines[i].first;
            E = lines[i].second;
            continue;
        }
        if(lines[i].first < S) {
            S = lines[i].first;
        }
        if(lines[i].second > E) {
            E = lines[i].second;
        }
    }
    cout << ans + E - S;
}