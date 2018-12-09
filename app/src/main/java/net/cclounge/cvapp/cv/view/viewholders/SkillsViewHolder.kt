package net.cclounge.cvapp.cv.view.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.cclounge.cvapp.R
import net.cclounge.cvapp.cv.view.CvSkillsViewModel

class SkillsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val skillsTextView: TextView = itemView.findViewById(R.id.skills_text)

    companion object {
        fun create(layoutInflater: LayoutInflater, parent: ViewGroup): SkillsViewHolder {
            return SkillsViewHolder(layoutInflater.inflate(R.layout.cv_skills_item, parent, false))
        }
    }

    fun bind(viewModel: CvSkillsViewModel) {
        skillsTextView.text = viewModel.skillText
    }

}