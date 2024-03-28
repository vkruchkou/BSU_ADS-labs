import java.io.File

fun destructRoads(n: Int, qn: IntArray, gn: Array<IntArray>): String {
    val ks = IntArray(qn.size)
    val bq = BooleanArray(gn.size)
    for (i in qn)
        bq[i - 1] = true
    val ar = IntArray(n)
    var k = n
    for (i in 0 until n) {
        ar[i] = -1
    }
    for (i in bq.indices) {
        if (!bq[i]) {
            if (k != 1) {
                var h1 = gn[i][0]
                while (ar[h1 - 1] > 0) {
                    h1 = ar[h1 - 1]
                }
                var h2 = gn[i][1]
                while (ar[h2 - 1] > 0) {
                    h2 = ar[h2 - 1]
                }
                if (h1 != h2) {
                    k--
                    if (ar[h1 - 1] > ar[h2 - 1]) {
                        ar[h2 - 1] += ar[h1 - 1]
                        ar[h1 - 1] = h2
                    } else {
                        ar[h1 - 1] += ar[h2 - 1]
                        ar[h2 - 1] = h1
                    }
                }
            }
        }
    }
    for (i in (qn.size - 1 downTo 0)) {
        if (k != 1) {
            ks[i] = 0
            var h1 = gn[qn[i] - 1][0]
            while (ar[h1 - 1] > 0) {
                h1 = ar[h1 - 1]
            }
            var h2 = gn[qn[i] - 1][1]
            while (ar[h2 - 1] > 0) {
                h2 = ar[h2 - 1]
            }
            if (h1 != h2) {
                k--
                if (ar[h1 - 1] > ar[h2 - 1]) {
                    ar[h2 - 1] += ar[h1 - 1]
                    ar[h1 - 1] = h2
                } else {
                    ar[h1 - 1] += ar[h2 - 1]
                    ar[h2 - 1] = h1
                }
            }
        }
        else {
            ks[i] = 1
        }
    }
    return ks.joinToString(separator = "")
}


fun main() {
    val br = File("input.txt").bufferedReader()
    var l = br.readLine().split(" ")
    val n = l[0].toInt()
    val m = l[1].toInt()
    val q = l[2].toInt()
    val gn = Array(m) { IntArray(2) }
    val qn = IntArray(q)
    for (i in 0 until m) {
        l = br.readLine().split(" ")
        gn[i][0] = l[0].toInt()
        gn[i][1] = l[1].toInt()
    }
    for (i in 0 until q) {
        qn[i] = br.readLine().toInt()
    }
    val writer = File("output.txt").bufferedWriter()
    writer.write(destructRoads(n, qn, gn))
    writer.close()
}