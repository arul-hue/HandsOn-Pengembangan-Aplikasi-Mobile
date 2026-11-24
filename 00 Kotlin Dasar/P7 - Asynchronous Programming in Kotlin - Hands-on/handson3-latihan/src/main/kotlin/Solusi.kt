import kotlinx.coroutines.*

class DownloadManager(private val scope: CoroutineScope) {

    fun downloadFile(name: String, durationMs: Long): Job {
        return scope.launch {
            delay(durationMs)
            println("$name selesai di-download")
        }
    }
}

fun main() = runBlocking {
    val parentJob = Job()
    val scope = CoroutineScope(parentJob)

    val manager = DownloadManager(scope)

    manager.downloadFile("foto.jpg", 1000)
    manager.downloadFile("video.mp4", 3000)
    manager.downloadFile("dokumen.pdf", 1500)

    delay(1200)

    println("Membatalkan sisa download...")
    parentJob.cancel()

    delay(2000)
    println("Selesai.")
}

// Penjelasan:
// - downloadFile memakai scope.launch sehingga setiap download menjadi child coroutine dari scope yang diberikan.
// - Job() membuat parent Job; CoroutineScope(parentJob) menjadikannya scope induk bagi semua child coroutine.
// - Karena memakai structured concurrency, cancel() pada parentJob membatalkan SEMUA child yang belum selesai.
// - video.mp4 (3000ms) dibatalkan sebelum delay-nya selesai sehingga tidak pernah mencetak "selesai di-download",
//   dokumen.pdf (1500ms) juga dibatalkan pada 1200ms; hanya foto.jpg (1000ms) yang diharapkan sudah selesai.
// - parentJob dibuat mandiri: ia induk download, tetapi bukan child Job milik runBlocking.
