#include <iostream>
#include <string>
#include <map>
#include <set>
#include <vector>
#include <algorithm>

int main()
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(0);
    
    std::string T;
    std::vector<std::string> input;
    std::set<std::string> check;
    std::map<std::string, std::vector<int>> map;

    int max_length = 0;
    int max_prefix_order = 1e9;
    std::string max_prefix;
    
    int N;
    std::cin >> N;
    input.resize(N);
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

            if(map[tmp].size() > 1 && max_length < tmp.length())
            {
                max_length = tmp.length();
            }
        }
    }

    for(const auto& [key, value] : map)
    {
        if(value.size() >=2 && key.length() == max_length)
        {
            if(value[0] < max_prefix_order)
            {
                max_prefix_order = value[0];
                max_prefix = key;
            }
        }
    }

    std::cout << input[map[max_prefix][0]] << "\n" << input[map[max_prefix][1]];
    return 0;
}
