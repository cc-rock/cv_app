package net.cclounge.cvapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import net.cclounge.cvapp.cv.presenter.CvPresenter
import net.cclounge.cvapp.cv.view.*
import net.cclounge.cvapp.dagger.TestApplication
import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CvActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(CvActivity::class.java, false, false)

    lateinit var mockPresenter: CvPresenter

    @Before
    fun setup() {
        mockPresenter = (InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as TestApplication).mockPresenter
    }

    @Test
    fun when_the_data_is_loading_the_loading_view_is_shown() {
        whenever(mockPresenter.loadData()).thenReturn(Observable.just(CvScreenViewModel(null, CvScreenState.LOADING)))
        activityRule.launchActivity(null)
        onView(withId(R.id.loading_view)).check(matches(isDisplayed()))
    }

    @Test
    fun when_data_is_returned_the_cv_view_is_shown() {
        val successViewModel = CvScreenViewModel(
            listOf(
                CvHeaderViewModel("Test Name", "Test contact info", "test photo url"),
                CvProfileViewModel("Test profile"),
                CvSkillsViewModel("Test skill\nTest skill"),
                CvSectionTitleViewModel("Test professional experience title"),
                CvProfessionalExperienceViewModel(
                    "Company 1", "test logo 1", "test website 1", "test role 1", "Test period", "test description 1"
                ),
                CvProfessionalExperienceViewModel(
                    "Company 2", "test logo 2", "test website 2", "test role 2", "Test period", "test description 2"
                )
            ),
            CvScreenState.SUCCESS
        )
        whenever(mockPresenter.loadData()).thenReturn(Observable.just(successViewModel))
        activityRule.launchActivity(null)
        onView(withId(R.id.cv_recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun when_an_error_occurs_the_error_view_is_shown() {
        whenever(mockPresenter.loadData()).thenReturn(Observable.just(CvScreenViewModel(null, CvScreenState.ERROR)))
        activityRule.launchActivity(null)
        onView(withId(R.id.error_view)).check(matches(isDisplayed()))
    }
}
