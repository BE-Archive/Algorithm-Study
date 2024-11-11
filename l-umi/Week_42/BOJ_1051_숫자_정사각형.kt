import java.io.*
import java.util.*

fun main() {
   val br = BufferedReader(InputStreamReader(System.`in`))
   var st = StringTokenizer(br.readLine())
   
   val N = st.nextToken().toInt()
   val M = st.nextToken().toInt()
   
   ///////////////////////////////////////
   
   val board = Array(N) { IntArray(M) }
   for(i in 0 until N) {
       val line = br.readLine()
       for(j in 0 until M) {
           // 문자를 숫자로 변환 ('1' -> 1로 변환하기 위해 '0'을 빼줌)
           board[i][j] = line[j] - '0'
       }
   }
   
   // 특정 크기의 정사각형이 가능한지 확인하는 함수
   fun checkSize(size: Int): Boolean {
       for(i in 0..N-size) {
           for(j in 0..M-size) {
               // 네 꼭짓점이 모두 같은지 확인
               if(board[i][j] == board[i][j+size-1] && 
                  board[i][j] == board[i+size-1][j] && 
                  board[i][j] == board[i+size-1][j+size-1]) {
                   return true
               }
           }
       }
       return false
   }
   
   ///////////////////////////////////////   
   val maxSize = minOf(N, M)
   
   for(size in maxSize downTo 1) {
       if(checkSize(size)) {
           println(size * size)
           break
       }
   }
}