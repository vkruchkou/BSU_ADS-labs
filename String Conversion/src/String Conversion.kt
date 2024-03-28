import java.io.File
import kotlin.math.min

class StringConversion: Runnable {

    private fun levenshteinDistance(x: Int, y: Int, z: Int, a: String, b: String): Int{
        val f = Array(a.length + 1) { IntArray(b.length + 1) }
        var d: Int
        for(i in f.indices)
            f[i][0] = i * x
        for(i in f[0].indices)
            f[0][i] = i * y
        for(i in 1 until f.size){
            for(j in 1 until f[0].size){
                 d =  if(a[i - 1] == b[j - 1]) 0 else 1
                f[i][j] = minOf((f[i - 1][j] + x), (f[i][j - 1] + y), (f[i - 1][j - 1] + d * min(z, x + y)))
            }
        }
        return f[f.size - 1][f[0].size - 1]
    }

    override fun run() {
        val br = File("in.txt").bufferedReader()
        val x = br.readLine().toInt()
        val y = br.readLine().toInt()
        val z = br.readLine().toInt()
        val a = br.readLine()
        val b = br.readLine()
        val writer = File("out.txt").bufferedWriter()
        writer.write(levenshteinDistance(x, y, z, a, b).toString())
        writer.close()
    }
}
fun main() {
    Thread(null, StringConversion(), "", 64 * 1024 * 1024).start()
}