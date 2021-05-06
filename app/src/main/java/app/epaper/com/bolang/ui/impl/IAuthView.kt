package app.epaper.com.bolang.ui.impl

import app.beelabs.com.codebase.base.contract.IView
import app.beelabs.com.codebase.base.response.BaseResponse

interface IAuthView : IView {
    fun handleSuccess(data: BaseResponse)
}