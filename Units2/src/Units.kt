import kotlin.math.pow


class Units: Runnable {

    fun revMod(i: Long, del: Long): LongArray {
        if (del == 0L) return longArrayOf(i, 1, 0)
        val gcd = revMod(del, i % del)
        val d = gcd[0]
        val a = gcd[2]
        val b = gcd[1] - i / del * gcd[2]
        return longArrayOf(d, a, b)
    }

    private fun getCombinations(n:Int, k:Int): Long{
        var del = 10.0.pow(9).toLong() + 7
        var res: Long = 1
        var pf: Long = 1
        for (i in n downTo n - k + 1) {
            res = (i * (res % del)) % del
            var t = revMod((i - n + k).toLong(),del)[1]
            if( t < 0)
                t += del
            pf = (t * (pf % del)) % del
        }
        return (res * pf) % del
    }


    override fun run() {
        var line = readLine()!!
        var t = line.indexOf(' ')
        var n = line.substring(0 until t).toInt()
        var k = line.substring(t + 1 until line.length).toInt()
        if(k > n/2)
            k = n - k
        println(getCombinations(n,k))
    }

}
fun main() {
    Thread(null, Units(), "", 64 * 1024 * 1024).start()
}