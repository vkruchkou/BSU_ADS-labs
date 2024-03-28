import java.io.File

fun main() {
    var sum: Long = 0
    File("input.txt").readLines().toSet().forEach(){
        sum += it.toInt()
    }
    val writer = File("output.txt").bufferedWriter()
    writer.write(sum.toString())
    writer.close()
}