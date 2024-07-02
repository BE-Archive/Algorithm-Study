#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;
std::vector<pii> arr;

int N, P;

vector<vector<int> > info(4);
double solve(const int a, const int b, const int c)
{
    double result = 0;
    double per = P;

    for(int i=0; i<a; ++i) {
        per = per * 0.99f;
        result += info[1][i];
    }
    for(int i=0; i<b; ++i) {
        per = per * 0.98f;
        result += info[2][i];
    }
    for(int i=0; i<c; ++i){
        per = per * 0.97f;
        result += info[3][i];
    }

    return result + per;
}

int main()
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);
    std::cout.tie(NULL);

    std::cin >> N >> P;

    int per, price;
    for(int i=0; i<N; ++i)
    {
        cin >> price >> per;
        info[per].push_back(price);
    }

    for(int i=1; i<=3; ++i)
        sort(info[i].begin(), info[i].end());

    double answer = 1e9;
    for(int i=0; i<=info[1].size(); ++i)
        for(int j=0; j<=info[2].size(); ++j)
            for(int k=0; k<=info[3].size(); ++k)
                answer = std::min(answer, solve(i, j, k));


    printf("%lf", answer);
    return 0;
}
