package app.epaper.com.bolang.ui.impl

import android.view.View
import app.beelabs.com.codebase.base.contract.IView
import app.beelabs.com.codebase.base.response.BaseResponse
import app.epaper.com.bolang.model.entity.Content

interface IHomeView : IView {
    fun handleSuccess(data: BaseResponse)

    fun showDetailEpaper(content: Content?, view: View)
}