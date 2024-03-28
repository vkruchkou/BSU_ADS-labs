import java.io.File

fun dfs(){

}


fun destructRoads(n: Int, qn: IntArray, gn: Array<IntArray>): String {
    val fq = IntArray(n){0}
    val ks = IntArray(qn.size)
    val gm = Array<ArrayList<Int>>(gn.size){ArrayList()}
    for(i in gn.indices){
        fq[gn[i][0] - 1] += 1
        fq[gn[i][1] - 1] += 1
        gm[gn[i][0] - 1].add(gn[i][1])
        gm[gn[i][1] - 1].add(gn[i][0])
    }
    var key = false
    for((k, i) in qn.withIndex()){
        if(!key){
            fq[gn[i - 1][0] - 1] -= 1
            fq[gn[i - 1][1] - 1] -= 1
            if(fq[gn[i - 1][0] - 1] == 0 || fq[gn[i - 1][1] - 1] == 0){
                key = true
            }
            else{
                ks[k] = 1
            }
        }
        else{
            ks[k] = 0
        }
    }
    return ks.joinToString(separator = "")
}

fun main() {
    val br = File("input.txt").bufferedReader()
    var l = br.readLine().split(" ")
    val n = l[0].toInt()
    val m = l[1].toInt()
    val q = l[2].toInt()
    val gn = Array(m) { IntArray(2) }
    val qn = IntArray(q)
    for (i in 0 until m) {
        l = br.readLine().split(" ")
        gn[i][0] = l[0].toInt()
        gn[i][1] = l[1].toInt()
    }
    for (i in 0 until q) {
        qn[i] = br.readLine().toInt()
    }
    val writer = File("output.txt").bufferedWriter()
    writer.write(destructRoads(n, qn, gn))
    writer.close()
}