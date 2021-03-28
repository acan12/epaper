package app.epaper.com.bolang.ui.dialog

import android.content.Context
import android.os.Bundle
import app.beelabs.com.codebase.base.BaseDialog
import app.epaper.com.bolang.R

class MainDialog(context: Context, style: Int) : BaseDialog(context, style) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_main)
    }
}