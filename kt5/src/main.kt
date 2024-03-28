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
        fun deleteRecursively(v: Node?, x: Int): Node?
        {
            when{
                v == null -> return null
                v.key > x ->{
                    v.leftChild = deleteRecursively(v.leftChild,x)
                    return v
                }
                v.key < x ->{
                    v.rightChild = deleteRecursively(v.rightChild,x)
                    return v
                }
            }
            if(v == root){
                if(v?.leftChild == null){
                    root = v?.rightChild
                    return root
                }
                else if(v.rightChild == null){
                    root =  v.leftChild
                    return root
                }
            }
            if(v?.leftChild == null)
                return v?.rightChild
            else if(v.rightChild == null)
                return v.leftChild
            else {
                val minKey: Int = findMin(v.rightChild)?.key ?: return null
                v.key = minKey
                v.rightChild = deleteRecursively(v.rightChild, minKey)
                return v
            }
        }
        private fun findMin(v: Node?):Node?{
            if(v == null)
                return null
            return if(v.leftChild != null)
                findMin(v.leftChild)
            else v
        }
    override fun run() {
        val tree = Tree()
        val br = File("input.txt").bufferedReader()
        val x: Int = br.readLine().toInt()
        br.readLine()
        br.forEachLine() {
            if(!tree.hasRoot())
                tree.insertRoot(it.toInt())
            tree.insert(it.toInt(),tree.getRoot())
        }
        tree.deleteRecursively(tree.getRoot(),x)
        val writer = File("output.txt").bufferedWriter()
        tree.writePreOrderTraversal(tree.getRoot(), writer)
        writer.close()
    }
}
fun main() {
    Thread(null, Tree(), "", 64 * 1024 * 1024).start()
}