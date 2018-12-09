package net.cclounge.cvapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import net.cclounge.cvapp.cv.CvActivity
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CvActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(CvActivity::class.java)

    @Before
    fun setup() {
    }

    @Test
    fun showCvView() {
        onView(withId(R.id.loading_view)).check(matches(isDisplayed()))
        Thread.sleep(5000)
        onView(withId(R.id.cv_recycler_view)).check(matches(isDisplayed()))
    }
}
