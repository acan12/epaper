package app.epaper.com.bolang.ui.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.epaper.com.bolang.IConfig.Companion.API_BASE_URL
import app.epaper.com.bolang.databinding.ItemEpaperBinding
import app.epaper.com.bolang.model.entity.Content
import app.epaper.com.bolang.ui.fragment.HomeFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class EpaperCardAdapter(private val contents: List<Content>?, private val fragment: HomeFragment) :
    RecyclerView.Adapter<EpaperCardAdapter.EpaperCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpaperCardViewHolder {
        val itemBinding =
            ItemEpaperBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpaperCardViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = contents!!.size - 1

    override fun onBindViewHolder(holder: EpaperCardViewHolder, position: Int) {
        var content = contents!![position + 1]

        holder.bind(content!!, fragment)
    }

    class EpaperCardViewHolder(private val itemBinding: ItemEpaperBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(content: Content, fragment: HomeFragment) {
            itemBinding.apply {
                itemTitle.text = content.title
                Glide.with(fragment.currentActivity)
                    .load(API_BASE_URL+content.cover_image_url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .addListener(object : RequestListener<Drawable> {
                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            itemProgressbar.visibility = View.GONE
                            return false
                        }

                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean = false
                    })
                    .into(itemImage)
                itemBinding.root.setOnClickListener {
                    fragment.showDetailEpaper(content, root)
                }
            }

        }
    }
}