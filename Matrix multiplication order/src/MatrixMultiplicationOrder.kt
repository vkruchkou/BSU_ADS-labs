import java.io.File

class MatrixMultiplicationOrder: Runnable {
    fun readFile(): Array<ShortArray>{
        val bf = File("input.txt").bufferedReader()
        val arr = Array(bf.readLine().toInt()) { ShortArray(2) }
        var i = 0
        bf.forEachLine {
           var t = it.indexOf(' ')
           arr[i][0] = it.substring(0 until t).toShort()
           arr[i][1] = it.substring(t + 1 until it.length).toShort()
            i++
        }
        return arr
    }

    fun minAtomOperations(arrM: Array<ShortArray>): Long {
        val mtr = Array(arrM.size) { LongArray(arrM.size) }
        for(i in 0 until mtr.size - 1)
            mtr[i][i + 1] = (arrM[i][0] * arrM[i][1] * arrM[i + 1][1]).toLong()
        for(l in 2 until mtr.size){
            var j = l
            for(i in 0 until mtr.size - l){
                mtr[i][j] = Long.MAX_VALUE
                for(k in i until j){
                    var t = mtr[i][k] + mtr[k + 1][j] + arrM[i][0] * arrM[k][1] * arrM[j][1]
                    if(t < mtr[i][j])
                        mtr[i][j] = t
                }
                j++
            }
        }
        return mtr[0][mtr.size-1]
    }

    override fun run() {
        var arrOfMatrix = readFile()
        val writer = File("output.txt").bufferedWriter()
        writer.write(minAtomOperations(arrOfMatrix).toString())
        writer.close()
    }
}
fun main() {
    Thread(null, MatrixMultiplicationOrder(), "", 64 * 1024 * 1024).start()
}