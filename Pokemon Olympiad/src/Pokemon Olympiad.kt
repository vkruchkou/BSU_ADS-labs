fun main() {
    val s = readLine()!!.split(" ")
    val m = s[0].toInt()
    val n = s[1].toInt()
    val m1 = readLine()!!.split(" ")
    val m2 = readLine()!!.split(" ").map { it.toInt() }.toIntArray()
    var l = 0
    var r = n - m + 1
    val tn = r
    val tm = Array(m) { IntArray(tn) }
    val ptm = Array(m) { IntArray(tn) }
    var q = m1[0].toInt()
    for (j in l until r) {
        tm[0][j] = q + m2[j]
        ptm[0][j] = j
    }
    l++
    r++
    for (i in 1 until m) {
        q = m1[i].toInt()
        var g = 0
        for (j in l until r) {
            var t = q + m2[j]
            for (k in 0 until tn){
                if (tm[i - 1][k] != 0 && ptm[i - 1][k] < j) {
                    if (t > tm[i - 1][k]) {
                        tm[i][g] = t
                        ptm[i][g] = j
                        g++
                        break
                    }
                }
                else
                    break
            }
        }
        l++
        r++
    }
    val arrPos = IntArray(m)
    var b: Boolean
    if(tm[m - 1][0] == 0){
        println("-1")
        return
    }
    else{
        q = ptm[m - 1][0]
        l = 0
        arrPos[m - 1] = q + 1
    }
    for (i in m - 2 downTo 1) {
        b = true
        var j = 0
        while (ptm[i][j] < q && ptm[i][j] != 0){
                if (tm[i][j] < tm[i + 1][l]) {
                    q = ptm[i][j]
                    l = j
                    arrPos[i] = q + 1
                    b = false
                    break
            }
            j++
        }
        if (b) {
            println("-1")
            return
        }
    }
    b = true
    var j = 0
    while (ptm[0][j] < q && j < tn){
        if (tm[0][j] < tm[1][l]) {
            q = ptm[0][j]
            arrPos[0] = q + 1
            b = false
            break
        }
        j++
    }
    if (b) {
        println("-1")
        return
    }
    println(arrPos.joinToString(separator = " "))
}