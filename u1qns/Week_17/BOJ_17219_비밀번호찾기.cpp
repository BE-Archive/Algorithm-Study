#include <iostream>
#include <map>

int main()
{
	std::ios::sync_with_stdio(false);
	std::cin.tie(NULL); std::cout.tie(NULL);

	int N, M;
	std::string site, pwd;
	std::map<std::string, std::string > map;
	
	std::cin >> N >> M;
	for(int i=0; i<N; ++i)
	{
		std::cin >> site >> pwd;
		map[site] = pwd;
	}
	
	for(int i=0; i<M; ++i)
	{
		std::cin >> site;
		std::cout << map[site] << "\n";
	}
    
	return 0;
}
