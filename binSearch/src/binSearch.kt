class BinSearch: Runnable {
    fun binSearch(mas: LongArray, x: Long): String {
        var first = 0
        var last = mas.size - 1
        var geIndex: Int? = null
        var gIndex: Int? =  foundGIndex(mas, x)
        var b = false
        while (first <= last) {
            val middle = (first + last) / 2
            when {
                mas[middle] == x -> {
                    if (!b)
                        b = true
                    geIndex = middle
                    last = middle - 1
                }
                mas[middle] < x -> first = middle + 1
                mas[middle] > x -> last = middle - 1
            }
        }
        if(gIndex == null)
            gIndex = mas.size
        if(geIndex == null)
            geIndex = gIndex
        return if(b)
            "1 $geIndex $gIndex"
        else
            "0 $geIndex $gIndex"
    }
    fun foundGIndex(mas: LongArray, x: Long): Int? {
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
        return gIndex
    }
    override fun run() {
        val arraySize = readLine()!!.toInt()
        val mas = LongArray(arraySize)
        for((i, line) in readLine()!!.split(" ").withIndex()){
            mas[i] = line.toLong()
        }
        val numberRequests = readLine()!!.toInt()
        val masRequests = LongArray(numberRequests)
        for((i, line) in readLine()!!.split(" ").withIndex()){
            masRequests[i] = line.toLong()
        }
        masRequests.forEach(){
            println(binSearch(mas, it))
        }
    }
}
fun main() {
    Thread(null, BinSearch(), "", 64 * 1024 * 1024).start()
}