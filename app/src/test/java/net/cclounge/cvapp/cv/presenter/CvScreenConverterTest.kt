package net.cclounge.cvapp.cv.presenter

import android.content.res.Resources
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.whenever
import net.cclounge.cvapp.R
import net.cclounge.cvapp.cv.model.Cv
import net.cclounge.cvapp.cv.model.ProfessionalExperience
import net.cclounge.cvapp.cv.model.Skill
import net.cclounge.cvapp.cv.view.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import kotlin.test.assertEquals

class CvScreenConverterTest {

    @get:Rule
    val rule : MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var resources: Resources

    lateinit var converter: CvScreenConverter

    @Before
    fun setup() {
        converter = CvScreenConverter(resources)
        whenever(resources.getString(R.string.professional_experiences)).thenReturn("Test professional experience title")
        whenever(resources.getString(eq(R.string.cv_contact_info), any(), any(), any())).thenReturn("Test contact info")
        whenever(resources.getString(eq(R.string.cv_skill), any(), any(), any())).thenReturn("Test skill")
        whenever(resources.getString(eq(R.string.prof_exp_period), any(), any())).thenReturn("Test period")
    }

    @Test
    fun `the CV is converted as expected when all optional fields are present`() {
        val testCv = Cv(
            "Test Name", "Test address", "Test phone", "Test email",
            "test photo url", "Test profile", listOf(Skill("Skill 1", "good", 5f), Skill("Skill 2", "bad", 2f)),
            listOf(
                ProfessionalExperience("Company 1", "test logo 1", "test website 1", "test role 1", "1", "2", "test description 1"),
                ProfessionalExperience("Company 2", "test logo 2", "test website 2", "test role 2", "1", "2", "test description 2")
            )
        )
        val converted = converter.convert(testCv)
        assertEquals(
            CvScreenViewModel(
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
            ),
            converted
        )
    }

    @Test
    fun `the CV is converted as expected when all optional fields are absent`() {
        val testCv = Cv(
            "Test Name", "Test address", "Test phone", "Test email",
            null, "Test profile", listOf(Skill("Skill 1", "good", 5f), Skill("Skill 2", "bad", 2f)),
            listOf(
                ProfessionalExperience("Company 1", null, null, "test role 1", "1", "2", "test description 1"),
                ProfessionalExperience("Company 2", null, null, "test role 2", "1", "2", "test description 2")
            )
        )
        val converted = converter.convert(testCv)
        assertEquals(
            CvScreenViewModel(
                listOf(
                    CvHeaderViewModel("Test Name", "Test contact info", null),
                    CvProfileViewModel("Test profile"),
                    CvSkillsViewModel("Test skill\nTest skill"),
                    CvSectionTitleViewModel("Test professional experience title"),
                    CvProfessionalExperienceViewModel(
                        "Company 1", null, null, "test role 1", "Test period", "test description 1"
                    ),
                    CvProfessionalExperienceViewModel(
                        "Company 2", null, null, "test role 2", "Test period", "test description 2"
                    )
                ),
                CvScreenState.SUCCESS
            ),
            converted
        )
    }

}