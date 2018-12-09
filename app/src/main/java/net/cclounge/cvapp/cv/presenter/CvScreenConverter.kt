package net.cclounge.cvapp.cv.presenter

import android.content.res.Resources
import net.cclounge.cvapp.R
import net.cclounge.cvapp.cv.model.Cv
import net.cclounge.cvapp.cv.view.*
import javax.inject.Inject

/**
 * Convert a Cv class coming from the backend to the appropriate
 * view model for the view to render
 */
class CvScreenConverter @Inject constructor(private val resources: Resources) {

    fun convert(cv: Cv): CvScreenViewModel {
        return CvScreenViewModel(
            listOf(
                convertHeader(cv),
                convertProfile(cv),
                convertSkills(cv),
                CvSectionTitleViewModel(resources.getString(R.string.professional_experiences)),
                *convertProfessionalExperiences(cv)
            ),
            CvScreenState.SUCCESS
        )
    }

    private fun convertHeader(cv: Cv): CvHeaderViewModel {
        return CvHeaderViewModel(
            cv.name,
            resources.getString(R.string.cv_contact_info, cv.address, cv.email, cv.phone),
            cv.photo
        )
    }

    private fun convertProfile(cv: Cv): CvProfileViewModel {
        return CvProfileViewModel(cv.profile)
    }

    private fun convertSkills(cv: Cv): CvSkillsViewModel {
        return CvSkillsViewModel(
            cv.skills.map {
                resources.getString(R.string.cv_skill, it.name, it.level, it.yearsOfExperience)
            }.joinToString(separator = "\n")
        )
    }

    private fun convertProfessionalExperiences(cv: Cv): Array<CvProfessionalExperienceViewModel> {
        return cv.professionalExperiences.map {
            CvProfessionalExperienceViewModel(
                it.companyName,
                it.companyLogo,
                it.companyWebsite,
                it.role,
                resources.getString(R.string.prof_exp_period, it.from, it.to),
                it.description
            )
        }.toTypedArray()
    }

}