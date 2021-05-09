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
//        var epaper =
//            when (position) {
//                0 -> Epaper(
//                    1,
//                    "Pelita Baru\n11 Juni 2020",
//                    "https://lelogama.go-jek.com/post_thumbnail/promo-gomart-blog-header-voucher.jpg",
//                    "https://www.deusain.com/wp-content/uploads/2021/03/EPAPER_PB_514_24032021_OK-1.pdf"
//                )
//                1 -> Epaper(
//                    2,
//                    "Pelita Baru\n20 Des 2020",
//                    "https://epaper.solopos.com/assets/uploads/2021/04/01/0001.jpg",
//                    "https://www.deusain.com/wp-content/uploads/2021/03/EPAPER_PB_515_25032021_OK-1.pdf"
//                )
//                2 -> Epaper(
//                    3,
//                    "Pelita Baru\n18 Jan 2021",
//                    "https://fin.co.id/wp-content/uploads/2020/04/EPaper-Koran-Fajar-Indonesia-Network-Edisi-14-April-2020-scaled.jpg",
//                    "https://www.deusain.com/wp-content/uploads/2021/03/EPAPER_PB_512_22032021_OK-1.pdf"
//                )
//                3 -> Epaper(
//                    4,
//                    "Pelita Baru\n30 Mar 2021",
//                    "https://fin.co.id/wp-content/uploads/2020/04/EPaper-Koran-Fajar-Indonesia-Network-Edisi-15-April-2020-scaled.jpg",
//                    "https://www.deusain.com/wp-content/uploads/2021/03/EPAPER_PB_26MARET2021_OK-1.pdf"
//                )
//                else -> null
//            }

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