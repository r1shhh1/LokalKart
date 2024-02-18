package kom.apnawallet.myapplication.util

object AppUtil {

    //format time for showing
    fun formatTime(time: Long): String {
        val currentTime = System.currentTimeMillis()
        val elapsedSeconds = (currentTime - time) / 1000
        if (elapsedSeconds < 60) {
            return "$elapsedSeconds seconds ago"
        } else if (elapsedSeconds < 3600) {
            val minutes = elapsedSeconds / 60
            return "$minutes minutes ago"
        } else if (elapsedSeconds < 86400) {
            val hours = elapsedSeconds / 3600
            return "$hours hours ago"
        } else {
            val days = elapsedSeconds / 86400
            return "$days days ago"
        }
    }
}