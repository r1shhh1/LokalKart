package kom.apnawallet.myapplication.util

object AppUtil {

    //format time for showing
    fun formatTime(time: Long): String {
        val currentTime = System.currentTimeMillis()
        val elapsedSeconds = (currentTime - time) / 1000
        if (elapsedSeconds < 60) {
            return "${elapsedSeconds}s ago"
        }else if (elapsedSeconds < 3600) {
            val minutes = elapsedSeconds / 60
            val seconds = elapsedSeconds % 60
            return "${minutes}m ${seconds}s ago"
        }else{
            return " "
        }
    }
}