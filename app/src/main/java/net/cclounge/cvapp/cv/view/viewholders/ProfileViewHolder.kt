package net.cclounge.cvapp.cv.view.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import net.cclounge.cvapp.R
import net.cclounge.cvapp.cv.view.CvProfileViewModel

class ProfileViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val profileTextView: TextView = itemView.findViewById(R.id.profile_text)

    companion object {
        fun create(layoutInflater: LayoutInflater, parent: ViewGroup): ProfileViewHolder {
            return ProfileViewHolder(layoutInflater.inflate(R.layout.cv_profile_item, parent, false))
        }
    }

    fun bind(viewModel: CvProfileViewModel) {
        profileTextView.text = viewModel.profileText
    }

}