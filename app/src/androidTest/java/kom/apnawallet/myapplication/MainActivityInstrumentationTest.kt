package kom.apnawallet.myapplication

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import kom.apnawallet.myapplication.ui.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentationTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        // Start the activity under test
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun testRecyclerViewDisplayed() {
        // Check if RecyclerView is displayed
        onView(withId(R.id.rvProducts)).check(matches(isDisplayed()))
    }

    @Test
    fun testSwipeToRefresh() {
        // Perform swipe to refresh action
        onView(withId(R.id.swipeRefreshLayout)).perform(click())

        // Check if the loading indicator is displayed
        onView(withId(R.id.shimmerView)).check(matches(isDisplayed()))
    }

    // Add more tests as needed

    @After
    fun tearDown() {
        // Close the activity after the test
        scenario.close()
    }
}