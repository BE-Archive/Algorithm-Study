fun main() {
  val T = readln().toInt()
  
  repeat(T) {
      val W = readln()        
      val K = readln().toInt() 
      
      if (K == 1) {
          println("1 1")
          return@repeat  // 현재 테스트 케이스만 종료하고 다음으로 넘어감
      }
      
      // 알파벳 26개에 대해 각 문자의 등장 위치를 저장할 배열
      // 예: charArray[0]은 'a'의 위치들, charArray[1]은 'b'의 위치들
      val charArray = Array(26) { mutableListOf<Int>() }
      
      // 문자열을 순회하면서 각 문자의 위치를 저장
      // c - 'a'로 알파벳을 0~25 인덱스로 변환
      // 예: "aba" -> charArray[0] = [0,2], charArray[1] = [1]
      W.forEachIndexed { index, c -> 
          charArray[c - 'a'].add(index)
      }
      
      //////////////////////////////////////////////////
      var min = Int.MAX_VALUE
      var max = Int.MIN_VALUE
      
      for (i in 0..25) {
          if (charArray[i].size < K) continue
          
          // k개씩 묶어서 연속된 구간의 길이 확인
          for (j in 0..charArray[i].size - K) {
              // 끝 인덱스 - 시작 인덱스 + 1 = 실제 문자열 길이
              val len = charArray[i][j + K - 1] - charArray[i][j] + 1
              min = minOf(min, len)
              max = maxOf(max, len)
          }
      }
      
      if (min != Int.MAX_VALUE) println("$min $max")
      else println(-1)
  }
}
