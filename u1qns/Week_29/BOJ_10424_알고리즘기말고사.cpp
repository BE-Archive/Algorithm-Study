#include <iostream>
#include <vector>

int main() {
    int N, tmp;

    std::cin >> N;
    std::vector<int> answer(N);
    
    for(int i=0; i<N; ++i) {
        std::cin >> tmp;
        answer[tmp-1] = tmp - (i+1);
    }
    
    for(const auto& ans : answer)
        std::cout << ans << "\n";
        
    return 0;
}
