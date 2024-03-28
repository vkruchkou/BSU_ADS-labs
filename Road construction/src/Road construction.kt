import java.io.File

fun constructRoad(n: Int, gn: Array<IntArray>): String {
    val ks = IntArray(gn.size)
    val ar = IntArray(n)
    var k = n;
    var h1: Int
    var h2: Int
    for (i in 0 until n) {
        ar[i] = -1
    }
    for (i in gn.indices) {
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
        ks[i] = k
    }
    return ks.joinToString(separator = "\n")
}

fun main() {
    val br = File("input.txt").bufferedReader()
    var l = br.readLine().split(" ")
    val n = l[0].toInt()
    val q = l[1].toInt()
    val gn = Array(q) { IntArray(2) }
    for ((i, j) in br.readLines().withIndex()) {
        l = j.split(" ")
        gn[i][0] = l[0].toInt()
        gn[i][1] = l[1].toInt()
    }
    val writer = File("output.txt").bufferedWriter()
    writer.write(constructRoad(n, gn))
    writer.close()
}