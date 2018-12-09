package net.cclounge.cvapp.cv.view.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.cclounge.cvapp.R
import net.cclounge.cvapp.cv.view.CvSectionTitleViewModel

class SectionTitleViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    companion object {
        fun create(layoutInflater: LayoutInflater, parent: ViewGroup): SectionTitleViewHolder {
            return SectionTitleViewHolder(layoutInflater.inflate(R.layout.cv_section_title, parent, false))
        }
    }

    fun bind(viewModel: CvSectionTitleViewModel) {
        (itemView as TextView).text = viewModel.title
    }

}