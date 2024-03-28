import java.io.File
import java.util.*

class Increasing: Runnable {

    fun foundGIndex(mas: LinkedList<IntArray>, x: Int): Int {
        var first = 0
        var last = mas.size - 1
        var gIndex: Int? = null
        while (first <= last) {
            val middle = (first + last) / 2
            when {
                mas[middle][mas[middle].size - 1] == x -> {
                    first = middle + 1
                }
                mas[middle][mas[middle].size - 1] < x -> first = middle + 1
                mas[middle][mas[middle].size - 1] > x -> {
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
        var res = 1
        var lf = LinkedList<IntArray>()
        lf.add(intArrayOf(0))
        for(i in 1 until arr.size) {
            var index = foundGIndex(lf, arr[i])
            if (lf[index - 1][lf[index - 1].size - 1] != arr[i]) {
                var t = lf[index - 1].copyOf(lf[index - 1].size + 1)
                t[t.size - 1] = arr[i]
                lf.add(index, t)
                if (index + 1 != lf.size)
                    if (lf[index].size == lf[index + 1].size)
                        lf.removeAt(index + 1)
            }
        }
        return res
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