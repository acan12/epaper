package app.epaper.com.bolang.ui.adapter

import android.app.Activity
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import app.epaper.com.bolang.App
import app.epaper.com.bolang.databinding.ItemEpaperBinding

import app.epaper.com.bolang.model.entity.Epaper
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class EpaperCardAdapter(private val epapers: List<Epaper>?, val activity: Activity) :
    RecyclerView.Adapter<EpaperCardAdapter.EpaperCardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpaperCardViewHolder {
        val itemBinding =
            ItemEpaperBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpaperCardViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = 4


    override fun onBindViewHolder(holder: EpaperCardViewHolder, position: Int) {
        var epaper =
            when (position) {
                0 -> Epaper(
                    1,
                    "Pelita Baru\n11 Juni 2020",
                    "https://lelogama.go-jek.com/post_thumbnail/promo-gomart-blog-header-voucher.jpg",
                    "https://www.deusain.com/wp-content/uploads/2021/03/EPAPER_PB_514_24032021_OK-1.pdf"
                )
                1 -> Epaper(
                    2,
                    "Pelita Baru\n20 Des 2020",
                    "https://epaper.solopos.com/assets/uploads/2021/04/01/0001.jpg",
                    "https://www.deusain.com/wp-content/uploads/2021/03/EPAPER_PB_515_25032021_OK-1.pdf"
                )
                2 -> Epaper(
                    3,
                    "Pelita Baru\n18 Jan 2021",
                    "https://fin.co.id/wp-content/uploads/2020/04/EPaper-Koran-Fajar-Indonesia-Network-Edisi-14-April-2020-scaled.jpg",
                    "https://www.deusain.com/wp-content/uploads/2021/03/EPAPER_PB_512_22032021_OK-1.pdf"
                )
                3 -> Epaper(
                    4,
                    "Pelita Baru\n30 Mar 2021",
                    "https://fin.co.id/wp-content/uploads/2020/04/EPaper-Koran-Fajar-Indonesia-Network-Edisi-15-April-2020-scaled.jpg",
                    "https://www.deusain.com/wp-content/uploads/2021/03/EPAPER_PB_26MARET2021_OK-1.pdf"
                )
                else -> null
            }

        holder.bind(epaper!!, activity)
    }

    class EpaperCardViewHolder(val itemBinding: ItemEpaperBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(epaper: Epaper, activity: Activity) {
            itemBinding.apply {
                itemTitle.text = epaper.name
                Glide.with(activity)
                    .load(epaper.urlImage)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(itemImage)
                itemBinding.root.setOnClickListener {
                    App.getNavigationComponent()
                        .homeNavigation()
                        .navigateToPreview(epaper.id, epaper.name, epaper.urlEpaper, itemBinding.root)
                }
            }

        }
    }
}