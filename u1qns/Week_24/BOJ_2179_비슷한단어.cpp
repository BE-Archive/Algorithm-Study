#include <iostream>
#include <string>
#include <map>
#include <queue>
#include <set>

int main()
{
    int N;
    std::string T;
    std::vector<std::string> input;
    std::set<std::string> check;

    //std::priority_queue<std::pair<std::string, int>, std::vector<std::pair<std::string, int>>, std::greater<>> pq;
    std::map<std::string, std::vector<int>> map;
    std::cin >> N;
    input.resize(N);

    int mv = 0;
    std::string answer;

    for(int i=0; i<N; ++i)
    {
        std::cin >>  T;
        input[i] = T;

        if(check.find(T) != check.end()) continue;

        check.insert(T);
        std::string tmp="";
        for(int s=0; s<T.size(); ++s)
        {
            tmp += T[s];
            map[tmp].push_back(i);
            if(map[tmp].size() >=2 && tmp.size() > mv)
            {
                answer = tmp;
                mv = tmp.size();
            }
            else if(map[tmp].size() >=2 && tmp.size() == mv)
            {
                if(mv!=0 && answer > tmp)
                {
                    answer = tmp;
                    mv = tmp.size();
                }
            }
        }
    }

    std::cout << input[map[answer][0]] << "\n";
    std::cout << input[map[answer][1]] << "\n";

    return 0;
}
