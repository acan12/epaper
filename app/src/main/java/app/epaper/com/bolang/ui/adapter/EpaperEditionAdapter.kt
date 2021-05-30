package app.epaper.com.bolang.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.epaper.com.bolang.databinding.ItemEpaperEditionBinding
import app.epaper.com.bolang.model.entity.Content
import app.epaper.com.bolang.ui.fragment.HomeFragment

class EpaperEditionAdapter(private val contents: List<Content>?, private val context: Context) :
    RecyclerView.Adapter<EpaperEditionAdapter.EpaperCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpaperCardViewHolder {
        val itemBinding =
            ItemEpaperEditionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpaperCardViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = 4

    override fun onBindViewHolder(holder: EpaperCardViewHolder, position: Int) {
//        var content = contents!![position + 1]

//        holder.bind(content!!, context)
    }

    class EpaperCardViewHolder(private val itemBinding: ItemEpaperEditionBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(content: Content, fragment: HomeFragment) {
            itemBinding.apply {
                itemTitle.text = content.title

                itemBinding.root.setOnClickListener {
                    fragment.showDetailEpaper(content, root)
                }
            }

        }
    }
}