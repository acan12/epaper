package app.epaper.com.bolang.ui.dialog

import android.os.Bundle
import android.view.View
import app.beelabs.com.codebase.base.BaseDialog
import app.epaper.com.bolang.App
import app.epaper.com.bolang.R
import kotlinx.android.synthetic.main.dialog_subscribe_offer.*

class SubscribeOfferDialog(var view: View, style: Int) : BaseDialog(view.context, style) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWindowContentDialogLayout(R.layout.dialog_subscribe_offer)

        initUI()

    }

    private fun initUI() {
        btn_subscribe.setOnClickListener {
            dismiss()

            App.getNavigationComponent()
                .homeNavigation()
                .navigateToSubscribe(view, view.context)
        }

    }
}