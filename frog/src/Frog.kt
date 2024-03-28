import kotlin.math.max

class Frog: Runnable {

    private fun jumpsBack(arr: IntArray): IntArray{
        var arrF = IntArray(arr.size)
        arrF[0] = arr[0]
        arrF[1] = Int.MIN_VALUE
        arrF[2] = arrF[0] + arr[2]
        for(i in 3 until arr.size){
            arrF[i] = max(arrF[i-2], arrF[i-3]) + arr[i]
        }
        return arrF
    }

    fun getWay(arrF: IntArray): String{
        var way = ArrayList<Int>()
        var i = arrF.size-1
        way.add(i + 1)
        while(i > 2){
            if(arrF[i-2] >= arrF[i-3]){
                i -= 2
                way.add(i + 1)
            }
            else{
                i -= 3
                way.add(i + 1)
            }
        }
        if(i == 2)
            way.add(1)
        way.reverse()
        return way.toIntArray().joinToString(separator = " ")
    }

    override fun run() {
        var arr = IntArray(readLine()!!.toInt())
        var arrK = readLine()!!.split(" ")
        when (arr.size){
            1 -> {
                println(arrK[0])
                println(1)
            }
            2 -> println(-1)
            else -> {
                for ((i, line) in arrK.withIndex()) {
                    arr[i] = line.toInt()
                }
                var arrF = jumpsBack(arr)
                println(arrF[arr.size-1])
                println(getWay(arrF))
            }
        }
    }

}
fun main() {
    Thread(null, Frog(), "", 64 * 1024 * 1024).start()
}