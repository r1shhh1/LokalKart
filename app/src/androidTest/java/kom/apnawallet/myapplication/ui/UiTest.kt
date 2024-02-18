package kom.apnawallet.myapplication.ui

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.action.ViewActions.swipeRight
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import kom.apnawallet.myapplication.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UiTest{

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()


    //Test to see if Last Refreshed textview updates with every swipe
    @Test
    fun testLastRefreshTvFunctionality() {
        Thread.sleep(5000)

        onView(withId(R.id.swipeRefreshLayout)).perform(swipeDown())

        onView(withId(R.id.timer)).check { view, _ ->
            val newText = (view as TextView).text.toString()
            assert(newText.startsWith("Last refreshed: 0"))
        }
    }


    //Test to check if shimmer effect appears with every refresh
    @Test
    fun testRefreshFunctionality() {
        Thread.sleep(5000)

        onView(withId(R.id.swipeRefreshLayout)).perform(swipeDown())
        onView(withId(R.id.shimmerView)).check(matches(isDisplayed()))
    }

    // Test to check if recycler view populates properly
    @Test
    fun testRecyclerViewPopulation() {
        Thread.sleep(5000)

        onView(withId(R.id.rvProducts)).check(matches(isDisplayed()))
        onView(withId(R.id.rvProducts)).check { view, _ ->
            val recyclerView = view as RecyclerView
            assert(recyclerView.adapter != null)
            assert((recyclerView.adapter?.itemCount ?: 0) > 0)
        }
    }

    //Test to check if clicking on particular item opens the product page
    @Test
    fun testRecyclerViewItemClick() {
        Thread.sleep(5000)
        onView(withId(R.id.rvProducts)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.activity_product_detail_layout)).check(matches(isDisplayed()))
    }

    //Test to check if image sliding feature works properly
    @Test
    fun navigateToProductDetailAndCheckSlider() {
        onView(withId(R.id.rvProducts)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.viewPager)).check(matches(isDisplayed()))
        onView(withId(R.id.indicatorLayout)).check(matches(isDisplayed()))

        onView(withId(R.id.viewPager)).perform(swipeLeft())
        onView(withId(R.id.viewPager)).perform(swipeRight())
    }

}