package net.cclounge.cvapp.cv.model

class Cv(val name: String,
         val address: String,
         val phone: String,
         val email: String,
         val photo: String?,
         val profile: String,
         val skills: List<Skill>,
         val professionalExperiences: List<ProfessionalExperience>)

class Skill(val name: String,
            val level: String,
            val yearsOfExperience: Float)

class ProfessionalExperience(val companyName: String,
                             val companyLogo: String?,
                             val companyWebsite: String?,
                             val role: String,
                             val from: String,
                             val to: String,
                             val description: String)