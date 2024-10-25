#include <iostream>
#include <vector>

using namespace std;
int main()
{
    int N;
    long long M, answer = 0;
    vector<vector<int> > patterns(5);

    patterns[2].push_back(1); patterns[2].push_back(3);
    patterns[3].push_back(2); patterns[3].push_back(2);
    patterns[4].push_back(3); patterns[4].push_back(1);


    cin >> N >> M;

    if(N == 1) answer = M*8;
    else if (N == 5) answer = M*8 + 4;
    else answer = M*4 + (M % 2 == 0 ? patterns[N][0] : patterns[N][1]);
    
    cout << answer;
    return 0;
}
