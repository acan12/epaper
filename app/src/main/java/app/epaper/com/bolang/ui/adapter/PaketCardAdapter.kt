package app.epaper.com.bolang.ui.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.beelabs.com.codebase.support.util.CacheUtil
import app.epaper.com.bolang.IConfig
import app.epaper.com.bolang.R
import app.epaper.com.bolang.databinding.ItemPaketCardBinding
import app.epaper.com.bolang.model.entity.PaketSubscribeCard
import app.epaper.com.bolang.model.entity.Product
import app.epaper.com.bolang.model.entity.request.TransactionRequest
import app.epaper.com.bolang.ui.tool.CoreUtil
import app.epaper.com.bolang.ui.tool.UiUtil

class PaketCardAdapter(
    private val products: List<Product>,
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

    override fun getItemCount(): Int = products.size


    override fun onBindViewHolder(holder: SubscribeViewHolder, position: Int) {
        var paket = products[position]

        with(holder.binding) {
            itemName.text = paket?.name
            itemPrice.text = UiUtil.convertToCurrencyFormat(paket?.price.toFloat().toInt())
            itemPaket.tag = false
            root.setOnClickListener {
                val jsonStr = CoreUtil.convertObjectToJsonString(
                    TransactionRequest(
                        paket.name,
                        paket.price.toFloat().toInt(),
                        paket.period_in_month.toInt()
                    )
                )
                CacheUtil.putPreferenceString(
                    IConfig.KEY_SUBSCRIBE_PRODUCT_SELECTED,
                    jsonStr, root.context
                )

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
}