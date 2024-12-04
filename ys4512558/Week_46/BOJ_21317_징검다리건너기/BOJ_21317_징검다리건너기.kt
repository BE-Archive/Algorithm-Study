import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))

    val N = br.readLine().toInt()

    var array = Array(N - 1) {
        val st = StringTokenizer(br.readLine())
        intArrayOf(st.nextToken().toInt(), st.nextToken().toInt())
    }

    val K = br.readLine().toInt()

    println(bfs(N, K, array))
}

fun bfs(N: Int, K: Int, array: Array<IntArray>): Int {
    var pq = PriorityQueue<Pair>()
    pq.offer(Pair(0, 0, false))

    var res = Integer.MAX_VALUE
    while (!pq.isEmpty()) {
        val pair = pq.poll()

        if(pair.num == N - 1) {
            res = pair.cnt
            break
        }
        if (pair.num + 1 <= N - 1) {
            pq.offer(Pair(pair.num + 1, pair.cnt + array[pair.num][0], pair.isBigJump))
        }
        if (pair.num + 2 <= N - 1) {
            pq.offer(Pair(pair.num + 2, pair.cnt + array[pair.num][1], pair.isBigJump))
        }
        if (pair.num + 3 <= N - 1 && !pair.isBigJump) {
            pq.offer(Pair(pair.num + 3, pair.cnt + K, true))
        }
    }
    return res
}

class Pair(var num : Int, var cnt: Int, var isBigJump: Boolean) : Comparable<Pair> {

    override fun compareTo(other: Pair): Int {
        return Integer.compare(this.cnt, other.cnt)
    }
}