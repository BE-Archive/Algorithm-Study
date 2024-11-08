import java.util.*
import java.io.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    
    val N = st.nextToken().toInt()
    val K = st.nextToken().toInt()
    val dist = IntArray(100001) { 0 }
    val move = IntArray(100001) { 0 }
    var found = false
    
    ///////////////////////////////////
    val q: Deque<Int> = LinkedList<Int>()
    q.offer(N)
    
    while (q.isNotEmpty() && !found) {
        val x = q.poll()
        
        if (x == K) {
            found = true
            break
        }
        
        val next = arrayOf(x + 1, x - 1, x * 2)
        for (nx in next) {
            if (nx in 0..100000 && dist[nx] == 0) {
                q.offer(nx)
                dist[nx] = dist[x] + 1
                move[nx] = x
            }
        }
    }
    
    ///////////////////////////////////
    println(dist[K])
    
    val path: Deque<String> = LinkedList<String>()
    var curr = K
    while (curr != N) {
        path.addFirst(curr.toString())
        curr = move[curr]
    }
    path.addFirst(N.toString())
    
    println(path.joinToString(" "))
}