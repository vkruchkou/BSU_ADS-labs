import java.io.File
import kotlin.collections.ArrayList
import kotlin.math.min

fun maxFlow(adjList: Array<ArrayList<Int>>, n: Int, s: Int, t: Int): Int {
    return if (t == s)
        0
    else
        Ff(adjList, s, t, n).ff()
}

class Ff(private val adjList: Array<ArrayList<Int>>, private var s: Int, private var t: Int, private val n: Int) {
    private val visited = BooleanArray(2 * n)
    private val net = Array(2 * n) { ArrayList<Int>() }
    private val fe = ArrayList<Edge>()
    private val pr = Array<Int?>(2 * n) { null }
    private fun buildNet() {
        for (v in 0 until n) {
                for (u in adjList[v]) {
                    if(v != 0) {
                        net[v * 2 - 1].add(fe.size)
                        fe.add(Edge(v * 2 - 1, v * 2, 1, 0))
                        net[v * 2 - 1].add(fe.size)
                        fe.add(Edge(v * 2, v * 2 - 1, 0, 0))
                    }
                    else{
                        net[1].add(fe.size)
                        fe.add(Edge(1, v * 2, 1, 0))
                        net[1].add(fe.size)
                        fe.add(Edge(v * 2, 1, 0, 0))
                    }
            }
        }
        s *= 2
        t *= 2
    }

    fun ff(): Int {
        buildNet()
        var f = 0
        while (true) {
            visited.fill(false)
            pr.fill(null)
            fp(s)
            if (!visited[t])
                break
            var pt = ArrayList<Int>()
            var k = t
            while (pr[k] != null) {
                val e = pr[k]!!
                pt.add(e)
                k = fe[e].s
            }
            var c = fe[pt[0]].c - fe[pt[0]].f
            for (e in pt)
                c = min(c, fe[e].c - fe[e].f)
            val ff = c
            for (e in pt) {
                fe[e].f += ff
                if (e % 2 == 0)
                    fe[e + 1].f -= ff
                else
                    fe[e - 1].f -= ff
            }
            f += ff
        }
        return f
    }

    fun fp(v: Int) {
        visited[v] = true
        for (e in net[v]) {
            var u = fe[e].t
            if (!visited[u] && fe[e].c - fe[e].f > 0) {
                pr[u] = e
                fp(u)
            }
        }
    }

}

class Edge(var s: Int, var t: Int, var c: Int, var f: Int)


fun main() {
    var br = File("input.in").bufferedReader()
    var s = br.readLine().split(" ")
    val n = s[0].toInt()
    val adjList = Array(2 * n) { ArrayList<Int>() }
    for (i in 0 until n) {
        for (s in br.readLine().split(" ")) {
            if (s != "0")
                adjList[2 * i + 1].add((s.toInt() - 1) * 2 - 1)
        }
        adjList[2 * i].add(2 * i + 1)
    }
    s = br.readLine().split(" ")
    val v = s[0].toInt() - 1
    val w = s[1].toInt() - 1
    val wr = File("output.out").bufferedWriter()
    wr.write(maxFlow(adjList, n, v, w).toString())
    wr.close()
}