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
import app.epaper.com.bolang.ui.tool.UiUtil

class PaketCardAdapter(
    private val menus: List<PaketSubscribeCard>?,
    val view: View,
    val res: Resources
) :
    RecyclerView.Adapter<PaketCardAdapter.SubscribeViewHolder>() {
    private lateinit var menuCard: PaketSubscribeCard
    private var prevSelected: View? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscribeViewHolder {
        val itemBinding =
            ItemPaketCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubscribeViewHolder(itemBinding.root)
    }

    override fun getItemCount(): Int = 3


    override fun onBindViewHolder(holder: SubscribeViewHolder, position: Int) {
        var paket =
            when (position) {
                0 -> PaketSubscribeCard(
                    "Trial",
                    UiUtil.convertToCurrencyFormat(0),
                    1,
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
                    UiUtil.convertToCurrencyFormat(500000),
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
                2 -> PaketSubscribeCard(
                    "Paket 12 bulan",
                    UiUtil.convertToCurrencyFormat(1000000),
                    8,
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
        with(holder.binding) {

            itemName.text = paket?.name
            itemPrice.text = paket?.price
            itemPaket.tag = false
            root.setOnClickListener {
                var x = prevSelected?.tag
                var switchOn = itemPaket.tag
                if (switchOn == false) {
                    itemPaket.tag = true
                    itemPaket.setBackgroundResource(R.drawable.bg_btn_solid_orange_with_border)
                    if (prevSelected != null) {
                        prevSelected?.tag = false
                        prevSelected?.setBackgroundResource(R.drawable.bg_btn_border_blue)
                    }
                }
                prevSelected = itemPaket
            }
        }
    }

    inner class SubscribeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemPaketCardBinding.bind(view)
    }

    open class ItemClickListener {
        open fun execute(view: View) {

        }
    }
}