import java.util.*

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    var st = StringTokenizer(readLine())
    val cranes = List(n) { st.nextToken().toInt() }
    val m = readLine().toInt()
    st = StringTokenizer(readLine())
    val boxes = List(m) { st.nextToken().toInt() }
    
    // 불가능한 경우
    if (boxes.max() > cranes.max()) {
        println(-1)
        return
    }
    
    // 내림차순 정렬
    val sortedCranes = cranes.sortedDescending()
    val sortedBoxes = boxes.sortedDescending()
    
    // 각 크레인이 현재 작업 중인 박스 인덱스
    val positions = IntArray(n) { 0 }
    // 박스가 이미 옮겨졌는지 표시
    val completed = BooleanArray(m) { false }
    var moved = 0 // 옮긴 박스 수
    var time = 0 // 소요 시간
    
    while (moved < m) {
        // 한 타임에 각 크레인 작업
        for (i in 0 until n) {
            while (positions[i] < m) {
                if (!completed[positions[i]] && sortedBoxes[positions[i]] <= sortedCranes[i]) {
                    completed[positions[i]] = true
                    moved++
                    break
                }
                positions[i]++
            }
        }
        time++
    }
    
    println(time)
}