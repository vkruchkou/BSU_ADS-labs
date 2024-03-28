import kotlin.math.pow


class Units: Runnable {

    private fun getCombinations(n:Int, k:Int): Long{
        var del = 10.0.pow(9).toLong() + 7
        val mtr = Array(n+1) { LongArray(n+1) }
        for(i in 0..n){
            mtr[i][0] = 1
            for (j in 1..i)
                mtr[i][j] = (mtr[i-1][j-1] + mtr[i-1][j]) % del
        }
        return mtr[n][k]
    }

    override fun run() {
        var line = readLine()!!
        var t = line.indexOf(' ')
        var n = line.substring(0 until t).toInt()
        var k = line.substring(t + 1 until line.length).toInt()
        println(getCombinations(n,k))
    }

}
fun main() {
    Thread(null, Units(), "", 64 * 1024 * 1024).start()
}