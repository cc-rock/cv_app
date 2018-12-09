package net.cclounge.cvapp.cv.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.cclounge.cvapp.cv.view.viewholders.*
import net.cclounge.cvapp.dagger.ActivityScope
import javax.inject.Inject

@ActivityScope
class CvAdapter @Inject constructor(private val picasso: Picasso): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private enum class ViewTypes {
        HEADER, PROFILE, SKILLS, PROFESSIONAL_EXPERIENCE, SECTION_TITLE
    }

    private var items = mutableListOf<CvItemViewModel>()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return when (item) {
            is CvHeaderViewModel -> ViewTypes.HEADER.ordinal
            is CvProfileViewModel -> ViewTypes.PROFILE.ordinal
            is CvSkillsViewModel -> ViewTypes.SKILLS.ordinal
            is CvProfessionalExperienceViewModel -> ViewTypes.PROFESSIONAL_EXPERIENCE.ordinal
            is CvSectionTitleViewModel -> ViewTypes.SECTION_TITLE.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewTypeEnum = ViewTypes.values()[viewType]
        return when(viewTypeEnum) {
            ViewTypes.HEADER -> HeaderViewHolder.create(layoutInflater, parent, picasso)
            ViewTypes.PROFILE -> ProfileViewHolder.create(layoutInflater, parent)
            ViewTypes.SKILLS -> SkillsViewHolder.create(layoutInflater, parent)
            ViewTypes.PROFESSIONAL_EXPERIENCE -> ProfessionalExperienceViewHolder.create(layoutInflater, parent, picasso)
            ViewTypes.SECTION_TITLE -> SectionTitleViewHolder.create(layoutInflater, parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        when(item) {
            is CvHeaderViewModel -> (holder as HeaderViewHolder).bind(item)
            is CvProfileViewModel -> (holder as ProfileViewHolder).bind(item)
            is CvSkillsViewModel -> (holder as SkillsViewHolder).bind(item)
            is CvProfessionalExperienceViewModel -> (holder as ProfessionalExperienceViewHolder).bind(item)
            is CvSectionTitleViewModel -> (holder as SectionTitleViewHolder).bind(item)
        }
    }

    fun updateItems(items: List<CvItemViewModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

}