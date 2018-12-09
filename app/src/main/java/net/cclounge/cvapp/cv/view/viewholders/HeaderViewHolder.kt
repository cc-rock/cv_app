package net.cclounge.cvapp.cv.view.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.cclounge.cvapp.R
import net.cclounge.cvapp.cv.view.CvHeaderViewModel

class HeaderViewHolder(itemView: View, private val picasso: Picasso): RecyclerView.ViewHolder(itemView) {

    companion object {
        fun create(layoutInflater: LayoutInflater, parent: ViewGroup, picasso: Picasso): HeaderViewHolder {
            return HeaderViewHolder(layoutInflater.inflate(R.layout.cv_header_item, parent, false), picasso)
        }
    }

    private val photoView: ImageView = itemView.findViewById(R.id.photo)
    private val nameView: TextView = itemView.findViewById(R.id.name)
    private val contactsView: TextView = itemView.findViewById(R.id.contact_info)

    fun bind(viewModel: CvHeaderViewModel) {
        viewModel.photo?.let {
            photoView.visibility = View.VISIBLE
            picasso.load(it).into(photoView)
        } ?: run {
            photoView.visibility = View.GONE
        }
        nameView.text = viewModel.name
        contactsView.text = viewModel.contactInfo
    }

}