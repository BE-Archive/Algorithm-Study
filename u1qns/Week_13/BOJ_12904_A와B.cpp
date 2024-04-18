#include <iostream>
#include <string>

int getAnswer(const std::string sub, std::string total)
{
	std::string str;
	int idx = 0;

	while(!(sub == total))
	{
		if(sub.size() > total.size()) 
			return 0;
		
		idx = total.size() - 1;
		str = total.substr(0, idx);
		if(total[idx] == 'A')
		{
			total = str;
		}
		else if(total[idx] == 'B')
		{
			total = std::string(str.rbegin(), str.rend());
		}
		else return 0;
	}
	
	return 1;
}

int main() {
	std::string S, T;
	std::cin >> S >> T;
	std::cout << getAnswer(S, T);
	return 0;
}
