import java.io.File
import kotlin.math.max
import kotlin.properties.Delegates

fun tape(arr: IntArray): Int {
    var f = IntArray(arr.size)
    val b = arr.size % 2 == 0
    for (i in arr.indices) {
        var h = false
        var k = i
        var l = i
        f[i] = arr[i]
        while (k > 0 || l < arr.size - 1) {
            var kt = 0
            var lt = 0
            for (j in k - 1 downTo 0 step 2) {
                kt += arr[j]
            }
            for (j in l + 1 until arr.size step 2) {
                lt += arr[j]
            }
            if (lt == kt) {
                if (k > 0) {
                    if (l < arr.size) {
                        if (arr[k - 1] >= arr[l + 1]) {
                            k -= 1
                            if (h) {
                                f[i] += arr[k]
                                h = false
                            } else h = true
                        } else {
                            l += 1
                            if (h) {
                                f[i] += arr[l]
                                h = false
                            } else h = true
                        }
                    } else {
                        k -= 1
                        if (h) {
                            f[i] += arr[k]
                            h = false
                        } else h = true
                    }
                } else {
                    if (l < arr.size) {
                        l += 1
                        if (h) {
                            f[i] += arr[l]
                            h = false
                        } else h = true
                    }
                }
            } else {
                if ((l == i && k != i) || kt > lt) {
                    k -= 1
                    if (k == -1) {
                        l += 1
                        if (h) {
                            f[i] += arr[l]
                            h = false
                        } else h = true
                    }
                    else {
                        if (h) {
                            f[i] += arr[k]
                            h = false
                        } else h = true
                    }
                } else {
                    if ((l != i && k == i) || kt < lt) {
                        l += 1
                        if (l == arr.size) {
                            k -= 1
                            if (h) {
                                f[i] += arr[k]
                                h = false
                            } else h = true
                        }
                        else {
                            if (h) {
                                f[i] += arr[l]
                                h = false
                            } else h = true
                        }
                    }
                }
            }
        }
    }
    var m = Int.MIN_VALUE
    if (!b) {
        for (i in f.indices)
            if (f[i] > m)
                m = f[i]
    } else {
        for (i in 0 until f.size - 1) {
            if (arr[i] > arr[i + 1]) {
                if (f[i] > m)
                    m = f[i]
            }
            if (arr[i] < arr[i + 1]) {
                if (f[i + 1] > m)
                    m = f[i + 1]
            }
            if (arr[i] == arr[i + 1]) {
                if (max(f[i], f[i + 1]) > m)
                    m = max(f[i], f[i + 1])
            }
        }
    }
    return m
}

fun main() {
    var br = File("input.txt").bufferedReader()
    var n = br.readLine().toInt()
    var arr = IntArray(n)
    for ((i, j) in br.readLine()!!.split(" ").withIndex()) {
        arr[i] = j.toInt()
    }
    val writer = File("output.txt").bufferedWriter()
    writer.write(tape(arr).toString())
    writer.close()
}
