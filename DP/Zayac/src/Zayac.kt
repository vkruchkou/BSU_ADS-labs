import java.io.File

fun zayac(n: Int): Int{
    var f = IntArray(n)
    f[0] = 1
    f[1] = 1
    for(i in 2 until f.size){
        f[i] = f[i - 1] + f[i - 2]
    }
    return f[n - 1]
}

fun main(){
    var n = File("input.txt").bufferedReader().readLine().toInt()
    val writer = File("output.txt").bufferedWriter()
    writer.write(zayac(n).toString())
    writer.close()
}