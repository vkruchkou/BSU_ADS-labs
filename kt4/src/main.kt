import java.io.File

class Tree: Runnable {
    class Node(var key: Long, var leftChild: Node? = null, var rightChild: Node? = null)

    private var root: Node? = null

    private fun read() {
        val br = File("bst.in").bufferedReader()
        val aln = ArrayList<Node?>(br.readLine().toInt())
        root = Node(br.readLine().toLong())
        aln.add(root!!)
        var t1: Int
        var t2: Int
        var tn: Node
        br.forEachLine {
            t1 = it.indexOf(' ')
            t2 = it.lastIndexOf(' ')
            tn = Node(it.substring(0 until t1).toLong())
            if(it.indexOf('L') > -1)
                aln[it.substring(t1+1 until t2).toInt()-1]!!.leftChild = tn
            else
                aln[it.substring(t1+1 until t2).toInt()-1]!!.rightChild = tn
            aln.add(tn)
        }
    }

    private fun checkCorrect(): Boolean{
        if(root != null) {
            var ptr1: Node = root!!
            var ptrs = ArrayList<Node>()
            var ptrRange = ArrayList<Long>()
            var startRange = Long.MIN_VALUE
            var endRange = Long.MAX_VALUE
            while (true) {
                if(ptr1.key < startRange  || ptr1.key > endRange)
                    return false
                if (ptr1.leftChild != null && ptr1.rightChild != null){
                    ptrs.add(ptr1)
                    ptrRange.add(endRange)
                    endRange = (ptr1.key - 1)
                    ptr1 = ptr1.leftChild!!
                    continue
                }
                else {
                    if (ptr1.leftChild != null) {
                        endRange = (ptr1.key - 1)
                        ptr1 = ptr1.leftChild!!
                        continue
                    }
                    else if (ptr1.rightChild != null){
                        startRange = ptr1.key
                        ptr1 = ptr1.rightChild!!
                        continue
                    }
                }
                if(ptrs.size != 0){
                    startRange = ptrs[ptrs.size-1].key
                    ptr1 = ptrs[ptrs.size-1].rightChild!!
                    endRange = ptrRange[ptrRange.size-1]
                    ptrs.removeAt(ptrs.size-1)
                    ptrRange.removeAt(ptrRange.size-1)
                }
                else break
            }
            return true
        }
       return true
    }

    override fun run() {
        read()
        var bl = checkCorrect()
        val writer = File("bst.out").bufferedWriter()
        if(bl)
            writer.write("YES")
        else
            writer.write("NO")
        writer.close()
    }
}
fun main() {
    Thread(null, Tree(), "", 64 * 1024 * 1024).start()
}