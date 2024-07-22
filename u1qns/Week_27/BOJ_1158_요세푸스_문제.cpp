#include <iostream>
#include <queue>

int main() {

    int N, K;
    std::queue<int> q;

    std::cin >> N >> K;
    for(int i=0; i<N; ++i)
        q.push(i+1);

    std::cout << "<";
    int front;
    while(!q.empty()) {
        for(int i=0; i<K-1; ++i) {
            front = q.front();
            q.pop();
            q.push(front);
        }
        std::cout << q.front();
        if(q.size() != 1) std::cout << ", ";
        q.pop();
    }

    std::cout << ">";

    return 0;
}
