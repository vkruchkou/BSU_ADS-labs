import java.io.File

fun main() {
    val br = File("input.txt").bufferedReader()
    var l = br.readLine().split(" ")
    val m = l[0].toInt()
    val c = l[1].toInt()
    val n = l[2].toInt()
    val arrM = IntArray(m)
    val arrB = BooleanArray(m)
    for (i in 0 until n) {
        var x = br.readLine().toInt()
        var j = 0
        var b = true
        var h = ((x % m) + c * j) % m
        while (arrB[h]) {
            if(arrM[h] == x){
                b = false
                break
            }
            j++
            h = ((x % m) + c * j) % m
        }
        if(b){
            arrB[h] = true
            arrM[h] = x
        }
    }
    for (i in 0 until m) {
        if(!arrB[i])
            arrM[i] = -1
    }
    val wr = File("output.txt").printWriter()
    wr.print(arrM.joinToString (separator = " "))
    wr.close()
}