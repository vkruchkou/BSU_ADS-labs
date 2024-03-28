import java.io.File
import java.util.*


fun codingHuffman(arr: PriorityQueue<Long>): Long{
    var len: Long = 0
    var n = arr.size
    if(arr.size == 1)
        return arr.remove()
    while (arr.size != 1){
        var s = arr.remove() + arr.remove()
        arr.add(s)
        len += s
    }
    return len
}

fun main(){
    var br = File("huffman.in").bufferedReader()
    val n = br.readLine().toInt()
    val arr = PriorityQueue<Long>()
    for((i, j) in br.readLine().split(" ").withIndex()){
        arr.add( j.toLong() )
    }
    val writer = File("huffman.out").bufferedWriter()
    writer.write(codingHuffman(arr).toString())
    writer.close()
}