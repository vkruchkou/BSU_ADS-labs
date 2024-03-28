import java.io.BufferedWriter
import java.io.File


class Tree: Runnable {
    class Node(var key: Int, var leftChild: Node? = null, var rightChild: Node? = null)
     private var root: Node? = null
    fun getRoot(): Node? {
        return root
    }
    fun hasRoot(): Boolean{
        return root != null
    }
    fun insertRoot(key: Int){
        root = insert(key,root)
    }
    fun insert( key: Int, ptr: Node?): Node? {
        when{
            ptr == null -> return Node(key)
            ptr.key > key -> ptr.leftChild = insert(key, ptr.leftChild)
            ptr.key < key -> ptr.rightChild = insert(key, ptr.rightChild)
        }
        return ptr
    }
    fun writePreOrderTraversal(v:Node?,bw: BufferedWriter){
        if (v != null) {
            if (v == root)
                bw.write(v.key.toString())
            else
                bw.write(System.lineSeparator()+v.key.toString())
            writePreOrderTraversal(v.leftChild,bw)
            writePreOrderTraversal(v.rightChild,bw)
        }
    }
    override fun run() {
        val tree = Tree()
        File("input.txt").bufferedReader().forEachLine {
            if(!tree.hasRoot())
                tree.insertRoot(it.toInt())
            tree.insert(it.toInt(),tree.getRoot())
        }
        val writer = File("output.txt").bufferedWriter()
        tree.writePreOrderTraversal(tree.getRoot(), writer)
        writer.close()
    }
}
fun main() {
    Thread(null, Tree(), "", 64 * 1024 * 1024).start()
}

