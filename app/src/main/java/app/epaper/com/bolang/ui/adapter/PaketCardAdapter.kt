package app.epaper.com.bolang.ui.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import app.epaper.com.bolang.R
import app.epaper.com.bolang.databinding.ItemPaketCardBinding

import app.epaper.com.bolang.model.entity.PaketSubscribeCard

class PaketCardAdapter(
    private val menus: List<PaketSubscribeCard>?,
    val view: View,
    val res: Resources
) :
    RecyclerView.Adapter<PaketCardAdapter.MenuCardViewHolder>() {
    private lateinit var menuCard: PaketSubscribeCard

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuCardViewHolder {
        val itemBinding =
            ItemPaketCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuCardViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = 2


    override fun onBindViewHolder(holder: MenuCardViewHolder, position: Int) {
        var menuCard =
            when (position) {
                0 -> PaketSubscribeCard(
                    "Paket 1 Tahun",
                    "Rp 2000.000",
                    12,
                    object : ItemClickListener() {
                        override fun execute(view: View) {
                            Toast.makeText(
                                view.context,
                                (position + 1).toString(),
                                Toast.LENGTH_SHORT
                            ).show()
//                            App.getNavigationComponent().homeNavigation().navigateToBmcLogin(view)
                        }
                    }
                )
                1 -> PaketSubscribeCard(
                    "Paket 6 bulan",
                    "Rp 500.000",
                    6,
                    object : ItemClickListener() {
                        override fun execute(view: View) {
                            Toast.makeText(
                                view.context,
                                (position + 1).toString(),
                                Toast.LENGTH_SHORT
                            ).show()
//                            App.getNavigationComponent().homeNavigation().navigateToBmcLogin(view)
                        }
                    }
                )
                else -> null
            }

        holder.bind(menuCard!!)
    }

    class MenuCardViewHolder(val itemBinding: ItemPaketCardBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        private var prevSelected: View? = null

        fun bind(paket: PaketSubscribeCard) {
            itemBinding.apply {
                itemName.text = paket.name
                itemPrice.text = paket.price
                root.setOnClickListener {
                    var x = itemPaket.tag
                    if (itemPaket.tag == null || itemPaket.tag == false) {
                        itemPaket.tag = true
                        itemPaket.setBackgroundResource(R.drawable.bg_btn_solid_orange_with_border)
                    }
                    if(itemPaket.tag != null) prevSelected?.setBackgroundResource(R.drawable.bg_btn_border_blue)
                    prevSelected = itemPaket
                }

//                tvMenuItem.text = menuCard.text
//                tvMenuItem.setCompoundDrawablesWithIntrinsicBounds(0, menuCard.imgIcon, 0, 0)
//                tvMenuItem.setOnClickListener {
//                    menuCard.clickListener?.execute(it)
//                }
            }

        }
    }

    open class ItemClickListener {
        open fun execute(view: View) {

        }
    }
}