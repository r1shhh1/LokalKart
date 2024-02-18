package kom.apnawallet.myapplication

import com.google.common.truth.Truth.assertThat
import junit.framework.TestCase.assertEquals
import kom.apnawallet.myapplication.util.AppUtil
import kom.apnawallet.myapplication.util.AppUtil.formatTime
import org.junit.Test
import java.time.Instant

class AppUtilTest {

    @Test
    fun `format time returned is correct`(){
        // Set a specific timestamp for testing
        val currentTimeMillis = System.currentTimeMillis()

        // Test scenarios
        val result30SecondsAgo = formatTime(currentTimeMillis - 30 * 1000)
        val result2MinutesAgo = formatTime(currentTimeMillis - 120 * 1000)
        val result2HoursAgo = formatTime(currentTimeMillis - 7200 * 1000)
        val result3DaysAgo = formatTime(currentTimeMillis - 3 * 86400 * 1000)

        // Assertions
        assertEquals("30 seconds ago", result30SecondsAgo)
        assertEquals("2 minutes ago", result2MinutesAgo)
        assertEquals("2 hours ago", result2HoursAgo)
        assertEquals("3 days ago", result3DaysAgo)
    }
}