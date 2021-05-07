package app.epaper.com.bolang.ui.impl

import app.beelabs.com.codebase.base.contract.IView
import app.beelabs.com.codebase.base.response.BaseResponse

interface IHomeView : IView {
    fun handleSuccess(data: BaseResponse)
}