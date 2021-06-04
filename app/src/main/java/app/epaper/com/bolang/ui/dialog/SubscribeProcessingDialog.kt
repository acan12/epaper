package app.epaper.com.bolang.ui.dialog

import android.os.Bundle
import android.view.View
import app.beelabs.com.codebase.base.BaseDialog
import app.beelabs.com.codebase.base.contract.IView
import app.epaper.com.bolang.App
import app.epaper.com.bolang.R
import app.epaper.com.bolang.ui.impl.IDialogSubscribeView
import kotlinx.android.synthetic.main.dialog_subscribe_offer.*
import kotlinx.android.synthetic.main.fragment_signup.*

class SubscribeProcessingDialog(var iview: IDialogSubscribeView, style: Int) : BaseDialog(iview.currentActivity, style) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWindowContentDialogLayout(R.layout.dialog_subscribe_processing)

        initUI()

    }

    private fun initUI() {
        btn_subscribe.setOnClickListener {
            dismiss()

            iview.handleButtonNextClicked(this@SubscribeProcessingDialog)
        }

    }
}