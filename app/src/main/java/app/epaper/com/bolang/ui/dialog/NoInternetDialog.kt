package app.epaper.com.bolang.ui.dialog

import android.content.Context
import android.os.Bundle
import app.beelabs.com.codebase.base.BaseDialog
import app.epaper.com.bolang.R
import kotlinx.android.synthetic.main.dialog_no_internet.*

class NoInternetDialog(context: Context, style: Int) : BaseDialog(context, style) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWindowContentDialogLayout(R.layout.dialog_no_internet)

        initUI()

    }

    private fun initUI() {
        btn_retry.setOnClickListener {
            dismiss()
            // recall api give stamp
        }

    }
}