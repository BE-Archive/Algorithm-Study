fun main() {
    val injured = readln().toLong() // 다친 손가락
    val limit = readln().toLong() // 셀 수 있는 횟수
    var answer = 0L // int아님 주의

    // 다친 손가락을 하나도 못 쓰는 경우
    if (limit == 0L) {
        println(injured - 1)
        return
    } 
    
    when (injured) {
        1L -> answer = 8 * limit //엄지
        5L -> answer = 4 + 8 * limit //새끼
        else -> {
            // 기본 계산
            answer = 4 * limit + 1
            // limit가 짝수면: injured-2를 더함
            // limit가 홀수면: 4-injured를 더함
            answer += if (limit % 2 == 0L) injured - 2 else 4 - injured
        }
    }
    
    println(answer)
}
