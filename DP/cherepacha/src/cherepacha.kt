import java.io.File
import kotlin.math.pow

fun cherepacha(n: Int, m: Int): Int{
    var del = 10.0.pow(9).toInt() + 7
    var f = Array(n) {IntArray(m)}
    for(i in f[0].indices){
        f[n - 1][i] = 1
    }
    for(i in f.indices){
        f[i][0] = 1
    }
    for(i in n - 2 downTo 0){
        for(j in 1 until m){
            f[i][j] = (f[i + 1][j] + f[i][j - 1]) % del
        }
    }
    return f[0][m - 1]
}

fun main(){
    var nm = File("input.txt").bufferedReader().readLine()!!.split(" ")
    val writer = File("output.txt").bufferedWriter()
    writer.write(cherepacha(nm[0].toInt(), nm[1].toInt()).toString())
    writer.close()
}