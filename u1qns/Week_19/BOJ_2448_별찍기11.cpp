#include <iostream>

int N;
char answer[3200][6300];

void draw(int x, int y) 
{
    answer[x][y] = '*';
    answer[x+1][y-1] = '*';
    answer[x+1][y+1] = '*';

    for (int i=0; i<5; ++i)
        answer[x+2][y-2+i] = '*';
}

void solve(const int n, const int x, const int y) 
{
    if (n==3)
    {
        draw(x, y);
        return;
    }
    
    solve(n/2, x, y);
    solve(n/2, x+n/2, y-n/2);
    solve(n/2, x+n/2, y+n/2);
}

int main() {

    std::ios::sync_with_stdio(NULL);
    std::cin.tie(NULL);

    std::cin >> N;

    for (int i=0; i<N; ++i)
        for (int j=0; j<2*N; ++j)
            answer[i][j] = ' ';

    solve(N, 0, N-1);
    
    for (int i=0; i<N; ++i)
    {
        for (int j=0; j<2*N-1; ++j)
            std::cout << answer[i][j];

        std::cout << "\n";
    }
} // main
