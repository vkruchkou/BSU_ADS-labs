import java.io.File
import java.util.*

class pair(val first: Long, val second: Int) : Comparable<pair> {
    override fun compareTo(other: pair): Int {
        return first.compareTo(other.first)
    }
}
fun distances(start: Int, n: Int, mt: Array<ArrayList<Pair<Int, Int>>>): String {
    val q = PriorityQueue<pair>()
    val processed = BooleanArray(n) { false }
    val dist = LongArray(n)
    q.add(pair(0L, start))
    while (!q.isEmpty()){
        var t = q.remove()

        if(processed[t.second])
            continue

        processed[t.second] = true
        dist[t.second] = t.first
        for(p in mt[t.second])
            if(!processed[p.first])
                q.add(pair(t.first + p.second, p.first))
    }
    return dist[n - 1].toString()
}

fun main() {
    var br = File("input.txt").bufferedReader()
    var s = br.readLine().split(" ")
    val n = s[0].toInt()
    val m = s[1].toInt()
    val mt = Array(n) { ArrayList<Pair<Int,Int>>() }
    for (i in 0 until m) {
        s = br.readLine().split(" ")
        mt[s[0].toInt() - 1].add(Pair(s[1].toInt() - 1, s[2].toInt()))
        mt[s[1].toInt() - 1].add(Pair(s[0].toInt() - 1, s[2].toInt()))
    }
    var writer = File("output.txt").bufferedWriter()
    writer.write(distances(0, n, mt))
    writer.close()
}