package net.cclounge.cvapp.cv.view

data class CvScreenViewModel(val cvItems: List<CvItemViewModel>?,
                             val state: CvScreenState)

enum class CvScreenState {LOADING, SUCCESS, ERROR}

sealed class CvItemViewModel

data class CvHeaderViewModel(val name: String,
                        val contactInfo: String,
                        val photo: String?): CvItemViewModel()

data class CvProfileViewModel(val profileText: String): CvItemViewModel()

data class CvSkillsViewModel(val skillText: String): CvItemViewModel()

data class CvProfessionalExperienceViewModel(val companyName: String,
                                        val companyLogo: String?,
                                        val companyWebsite: String?,
                                        val roleName: String,
                                        val period: String,
                                        val description: String): CvItemViewModel()

data class CvSectionTitleViewModel(val title: String): CvItemViewModel()