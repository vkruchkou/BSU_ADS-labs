import java.io.File
import java.util.*
import kotlin.collections.ArrayList

class Increasing: Runnable {

    private fun foundGIndex(mas: ArrayList<Int>, x: Int): Int {
        var first = 0
        var last = mas.size - 1
        var gIndex: Int? = null
        while (first <= last) {
            val middle = (first + last) / 2
            when {
                mas[middle] == x -> {
                    first = middle + 1
                }
                mas[middle] < x -> first = middle + 1
                mas[middle] > x -> {
                    last = middle - 1
                    gIndex = middle
                }
            }
        }
        if(gIndex == null)
            return mas.size
        return gIndex
    }

    private fun getIncreasingSize(arr: IntArray): Int{
        var lf =  ArrayList<Int>()
        lf.add(arr[0])
        for(i in 1 until arr.size) {
            var index = foundGIndex(lf, arr[i])
            when(index){
                0 -> lf[0] = arr[i]
                lf.size -> {
                    if(lf[lf.size -1] != arr[i])
                        lf.add(arr[i])
                }
                else ->{
                    if(lf[index-1] != arr[i])
                        lf[index] = arr[i]
                }
            }
        }
        return lf.size
    }

    override fun run() {
        var br = File("input.txt").bufferedReader()
        var arr = IntArray(br.readLine().toInt())
        for ((i, j) in br.readLine().split(" ").withIndex()) {
            arr[i] = j.toInt()
        }
        val writer = File("output.txt").bufferedWriter()
        writer.write(getIncreasingSize(arr).toString())
        writer.close()
    }
}
fun main() {
    Thread(null, Increasing(), "", 64 * 1024 * 1024).start()
}