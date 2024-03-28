import java.io.BufferedWriter
import java.io.File
import kotlin.math.max

class Tree: Runnable {
    class Node(var key: Int, var height: Int, var leftChild: Node? = null, var rightChild: Node? = null){
        fun setHeight(){
            height = max(rightChild?.height ?: -1, leftChild?.height ?: -1) + 1
        }
        fun getLengthHalfWay(): Int{
            return (rightChild?.height ?:-1) + (leftChild?.height ?:-1) + 2
        }
    }
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
    fun insert( key: Int, ptr: Node? = root): Node? {
        when{
            ptr == null -> return Node(key,0)
            ptr.key > key -> ptr.leftChild = insert(key, ptr.leftChild)
            ptr.key < key -> ptr.rightChild = insert(key, ptr.rightChild)
        }
        return ptr
    }
    fun writePreOrderTraversal(v:Node?,bw: BufferedWriter){
        v?.let {
            if (it == root)
                bw.write(it.key.toString())
            else
                bw.write(System.lineSeparator() + it.key.toString())
            writePreOrderTraversal(it.leftChild,bw)
            writePreOrderTraversal(it.rightChild,bw)
        }
    }
    fun setHeights(v:Node? = root,rhw: ArrayList<Node>): Int{
        if (v != null){
            v.height = setHeights(v.leftChild,rhw)
            v.height = setHeights(v.rightChild,rhw)
            v.setHeight()
            if(rhw.size == 0)
                rhw.add(v)
            else
            {
                val l = v.getLengthHalfWay()
                val rl = rhw[0].getLengthHalfWay()
                if (l > rl){
                    rhw.clear()
                    rhw.add(v)
                }
                else if (l == rl) {
                    rhw.add(v)
                }
            }
        }
        return 0
    }
    fun arrayToDeleteFrequency(mnf: ArrayList<Node>){
        val mp = LinkedHashMap<Node,Int>()
        mnf.forEach(){
            var amountL: Int = getAmountBranches(it.leftChild)[0]
            var amountR: Int = getAmountBranches(it.rightChild)[0]
            collectNode(it.leftChild,amountL,amountR,false,mp)
            collectNode(it.rightChild,amountL,amountR,true,mp)
            if(mp[it] == null)
                mp[it] = amountL * amountR
            else
                mp[it] = mp[it]!! + (amountL * amountR)
        }
        var minKey :Int? = null
        mp.forEach {
            if(it.value % 2 == 0){
                if(minKey == null)
                    minKey = it.key.key
                if(it.key.key < minKey!!)
                    minKey = it.key.key
            }
        }
        minKey?.let { deleteRecursively(x = it) }
    }
    fun collectNode(v:Node?, amountL: Int, amountR: Int, switch: Boolean, mnf: LinkedHashMap<Node,Int>){
        if(v != null) {
            if(mnf[v] == null)
                mnf[v] = amountL * amountR
            else
                mnf[v] = mnf[v]!! + (amountL * amountR)
            when{
                (v.leftChild == null) && (v.rightChild == null) -> {
                   return
                }
                (v.leftChild?.height ?: -1) == (v.rightChild?.height ?: -1) -> {
                    if(!switch){
                        collectNode(v.leftChild,getAmountBranches(v.leftChild)[0],amountR,switch,mnf)
                        collectNode(v.rightChild,getAmountBranches(v.rightChild)[0],amountR,switch,mnf)
                    }
                    else{
                        collectNode(v.leftChild,amountL,getAmountBranches(v.leftChild)[0],switch,mnf)
                        collectNode(v.rightChild,amountL,getAmountBranches(v.rightChild)[0],switch,mnf)
                    }
                }
                (v.leftChild?.height ?: -1) > (v.rightChild?.height ?: -1) -> collectNode(v.leftChild,amountL,amountR,switch,mnf)
                (v.leftChild?.height ?: -1) < (v.rightChild?.height ?: -1) -> collectNode(v.rightChild,amountL,amountR,switch,mnf)
            }
        }
    }
    fun getAmountBranches(v:Node?, amount: Int = 0, c:Array<Int> = arrayOf(0)):  Array<Int>{
        if(v != null)
            when{
                (v.leftChild == null) && (v.rightChild == null) -> {
                    c[0]++
                }
                (v.leftChild?.height ?: -1) == (v.rightChild?.height ?: -1) -> {
                    getAmountBranches(v.leftChild,amount,c)
                    getAmountBranches(v.rightChild,amount,c)
                }
                (v.leftChild?.height ?: -1) > (v.rightChild?.height ?: -1) -> getAmountBranches(v.leftChild,amount,c)
                (v.leftChild?.height ?: -1) < (v.rightChild?.height ?: -1) -> getAmountBranches(v.rightChild,amount,c)
            }
        else c[0]++
        return c
    }

    fun deleteRecursively(v: Node? = root, x: Int): Node?
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
        File("tst.in").bufferedReader().forEachLine {
            if(!hasRoot())
                insertRoot(it.toInt())
            insert(it.toInt())
        }
        var rhw = ArrayList<Node>()
        setHeights(rhw = rhw)
        arrayToDeleteFrequency(rhw)
        val writer = File("tst.out").bufferedWriter()
        writePreOrderTraversal(getRoot(), writer)
        writer.close()
    }
}
fun main() {
    Thread(null, Tree(), "", 64 * 1024 * 1024).start()
}