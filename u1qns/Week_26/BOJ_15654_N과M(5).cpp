#include <iostream>
#include <algorithm>
#include <vector>

#define MAX 10

int N, M;
bool isSelected[MAX] = {false, };
std::vector<int> inp, answer;

void solve(int depth) {

    if(depth == M) {
        for(int i=0;i<M;i++)
            std::cout << answer[i] << ' ';
        std::cout << "\n";
        return;
    }

    for(int i=0;i<N;i++) {
        if(isSelected[i]) continue;
        isSelected[i] = true;
        answer[depth] = inp[i];
        solve(depth+1);
        isSelected[i] = false;
    }
}

int main() {

    std::ios::sync_with_stdio(false);
    std::cin.tie(NULL);

    std::cin >> N >> M;

    inp.resize(N);
    answer.resize(N);

    for(int i=0; i<N; ++i)
        std::cin >> inp[i];

    std::sort(inp.begin(), inp.end());
    solve(0);

    return 0;
}
