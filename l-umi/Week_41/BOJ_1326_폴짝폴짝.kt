fun main() {
    val n = readln().toInt()
    val stones = readln().trim().split(" ").map { it.toInt() }
    val (start, end) = readln().split(" ").map { it.toInt() }

    ///////////////////////////////////
    val visited = IntArray(n) { -1 }
    val q = ArrayDeque<Int>()
    q.add(start - 1)
    visited[start - 1] = 0
    
    while (q.isNotEmpty()) {
        val cur = q.removeFirst()
        if (cur == end - 1) break
        
        val jump = stones[cur]
        if (jump == 0) continue
        
        // 오른쪽 점프
        var next = cur + jump
        while (next < n) {
            if (visited[next] == -1) {
                visited[next] = visited[cur] + 1
                q.add(next)
            }
            next += jump
        }
        
        // 왼쪽 점프
        next = cur - jump
        while (next >= 0) {
            if (visited[next] == -1) {
                visited[next] = visited[cur] + 1
                q.add(next)
            }
            next -= jump
        }
    }
    
    println(visited[end - 1])
}
