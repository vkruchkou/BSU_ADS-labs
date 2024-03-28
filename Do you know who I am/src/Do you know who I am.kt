import kotlin.math.*

fun binSearch(mas: LongArray, x: Long, l: Int = 0, r: Int = mas.size - 1): Int {
    var l = l
    var r = r
    while (l < r) {
        val k = (l + r) / 2
        if (x <= mas[k])
            r = k
        else
            l = k + 1
    }
    return l
}

fun f(a: LongArray, t: Array<LongArray>, l: Long, r: Long): String {
    var pl = binSearch(a, l) + 1
    var pr = binSearch(a, r)
    var m1 = 0L
    var m2 = 0L
    if(r == l)
        return "1"
    if (l != a[pl - 1])
        m1 = a[pl - 1] - l
    if (pl - 1 == pr){
        if((r == a[pr]))
            return (r - l).toString()
        return (r - l + 1).toString()
    }
    if (r != a[pr])
        m2 = r - a[pr - 1] + 1
    else
        pr++
    if (pl == pr) {
        return (max(a[pl - 1] - l, r - a[pr - 1] + 1)).toString()
    }
        val p = (log2((pr - pl).toDouble())).toInt()
        var m3 = max(t[p][pl], t[p][(pr - 2.0.pow(p)).toInt()])
    return max(max(m1, m2), m3).toString()
}

fun main() {
    val s = readLine()!!.split(" ")
    var m = s[0].toLong()
    var n = s[1].toInt()
    var k = s[2].toInt()
    val a = LongArray(n)
    val table = Array(ceil(log2(n.toDouble())).toInt()) { LongArray(n) }
    for ((i, j) in readLine()!!.split(" ").withIndex()) {
        if (i != 0) {
            var k = j.toLong()
            a[i] = k
            table[0][i] = k - a[i - 1]
        } else {
            var k = j.toLong()
            a[i] = k
            table[0][i] = k - 1
        }
    }
    a[n - 1] = m + 1
    table[0][n - 1] = m + 1 - a[n - 2]
    for (i in 1 until n) {
        for (j in 0 until n - 2.0.pow(i).toInt())
            table[i][j] = max(table[i - 1][j], table[i - 1][j + 2.0.pow(i - 1).toInt()])
    }
    var kk = Array(k) { LongArray(2) }
    for (i in 0 until k) {
        var s = readLine()!!.split(" ")
        var l = s[0].toLong()
        var r = s[1].toLong()
        kk[i][0] = l
        kk[i][1] = r
    }
    for (i in 0 until k)
        println(f(a, table, kk[i][0], kk[i][1]))
}
