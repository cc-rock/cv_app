package net.cclounge.cvapp.cv.view.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.cclounge.cvapp.R
import net.cclounge.cvapp.cv.view.CvProfessionalExperienceViewModel

class ProfessionalExperienceViewHolder(itemView: View, private val picasso: Picasso): RecyclerView.ViewHolder(itemView) {

    private val companyLogo: ImageView = itemView.findViewById(R.id.company_logo)
    private val companyName: TextView = itemView.findViewById(R.id.company_name)
    private val companyWebsite: TextView = itemView.findViewById(R.id.company_website)
    private val period: TextView = itemView.findViewById(R.id.period)
    private val role: TextView = itemView.findViewById(R.id.role)
    private val description: TextView = itemView.findViewById(R.id.description)

    companion object {
        fun create(layoutInflater: LayoutInflater, parent: ViewGroup, picasso: Picasso): ProfessionalExperienceViewHolder {
            return ProfessionalExperienceViewHolder(layoutInflater.inflate(R.layout.cv_professional_exp_item, parent, false), picasso)
        }
    }

    fun bind(viewModel: CvProfessionalExperienceViewModel) {
        viewModel.companyLogo?.let {
            companyLogo.visibility = View.VISIBLE
            picasso.load(it).into(companyLogo)
        } ?: run {
            companyLogo.visibility = View.GONE
        }
        viewModel.companyWebsite?.let {
            companyWebsite.visibility = View.VISIBLE
            companyWebsite.text = viewModel.companyWebsite
        } ?: run {
            companyWebsite.visibility = View.GONE
        }
        companyName.text = viewModel.companyName
        period.text = viewModel.period
        role.text = viewModel.roleName
        description.text = viewModel.description
    }

}