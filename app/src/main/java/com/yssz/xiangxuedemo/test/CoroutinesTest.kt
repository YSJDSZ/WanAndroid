import kotlinx.coroutines.*
fun main2() {
    val job = Job()
    val scope = CoroutineScope(job)
        scope.launch {
            for (i in 1..5) {
                println(i)
                delay(1000)
            }
        }
    println("----------")
    runBlocking {
        coroutineScope {  }
        launch {
            for (i in 1..3) {
                println("aaa"+i)
                delay(1000)
            }
        }
    }
    println("++++++++")
}
fun main() {
    runBlocking {
        val start = System.currentTimeMillis()
        val result1 = async {
            delay(1000)
            5 + 5
        }
        println("result1 is ${result1 }.")
        val result2 = async {
            delay(1000)
            4 + 6
        }
        println("result is ${result1.await()+result2.await() }.")
        val end = System.currentTimeMillis()
        println("cost ${end - start} ms.")
    }
}