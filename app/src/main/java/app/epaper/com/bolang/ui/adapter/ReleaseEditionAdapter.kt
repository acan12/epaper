package app.epaper.com.bolang.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.epaper.com.bolang.IConfig
import app.epaper.com.bolang.databinding.ItemReleaseEditionBinding
import app.epaper.com.bolang.model.entity.Content
import app.epaper.com.bolang.ui.impl.IHomeView
import app.epaper.com.bolang.ui.tool.UiUtil

class ReleaseEditionAdapter(private val contents: List<Content>?, private val iview: IHomeView) :
    RecyclerView.Adapter<ReleaseEditionAdapter.EpaperCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpaperCardViewHolder {
        val itemBinding =
            ItemReleaseEditionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpaperCardViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = contents!!.size

    override fun onBindViewHolder(holder: EpaperCardViewHolder, position: Int) {
        var content = contents!![position]

        holder.bind(content!!, iview)
    }

    class EpaperCardViewHolder(private val itemBinding: ItemReleaseEditionBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(content: Content, iview: IHomeView) {
            itemBinding.apply {
                itemTitle.text = content.title
                itemDate.text = UiUtil.convertStringToDateWithFormat(
                    content.release_date,
                    IConfig.YYYY_MM_DD,
                    "dd MMMM yyyy"
                )

                itemBinding.root.setOnClickListener {
                    iview.showDetailEpaper(content, root)
                }
            }

        }
    }
}