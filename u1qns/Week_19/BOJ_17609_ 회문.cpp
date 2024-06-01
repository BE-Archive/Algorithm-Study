#include <iostream>
#include <string>

bool solve(const std::string& input, int start, int end)
{
    while(start < end)
    {
        if (input[start++] != input[end--])
            return false;
    }
    return true;
}

int main()
{
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(0);
    
    int T; std::cin >> T;
    while(T--)
    {
        int answer = 0;
        std::string input;
        std::cin >> input;

        int start = 0;
        int end = input.size()-1;

        while(start < end)
        {
            if (input[start] != input[end])
            {
                answer = 2;

                if(solve(input, start+1, end) || solve(input, start, end-1))
                {
                    answer = 1;
                }
                break;
            }
            ++start; --end;
        }

        std::cout << answer << "\n" ;
    }
    return 0;
}
