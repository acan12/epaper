package app.epaper.com.bolang.ui.component.impl

import android.content.Context
import app.epaper.com.bolang.ui.component.ILogic

interface IPaymentLogic : ILogic {

    fun setupPayment(context: Context)
}