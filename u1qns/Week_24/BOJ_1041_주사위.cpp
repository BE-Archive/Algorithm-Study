#include <iostream>

int getAnswer()
{
  int answer = 0;
  
  if(N == 1)
  {
    // 예외 처리 필요
    return answer;
  }


  // 꼭지점 8개: 상단 4개(3면), 하단 4개(2면)
  // 면 5면 : 외부에 노출되지 않는 하단 면을 제외하면 5개, 꼭지점을 제외한 면적 (N-2)(N-2)개가 5면 존재 (1면)
  // 선 8개: 면이 맞닿는 선에 꼭지점을 제외하면 (N-1)개가 8개 존재

  /*
    - 주사위의 평면도 위치에 따라 조합이 다름을 유의
    1. 3면 조합 중 가장 작은 값
    2. 2면 조합 중 가장 작은 값
    3. 가장 작은 dice값

    이 3개의 조합을 이용해서 그냥 구현하면 됨
  */
  
  
  return answer;
}

int main()
{
  
  std::cin >> N;
  for(int i=0; i<6; ++i) 
    std::cin >> dice[i];

  std::cout << getAnswer();

  return 0;
}
