package app.epaper.com.bolang.model.entity

import app.epaper.com.bolang.ui.adapter.PaketCardAdapter

class PaketSubscribeCard(
    val name: String,
    val price: String,
    val period_in_month: Int,
    var clickListener: PaketCardAdapter.ItemClickListener? = null
)