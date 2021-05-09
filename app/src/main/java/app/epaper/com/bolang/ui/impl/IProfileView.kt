package app.epaper.com.bolang.ui.impl

import app.beelabs.com.codebase.base.contract.IView
import app.beelabs.com.codebase.base.response.BaseResponse

interface IProfileView : IView {

    fun handleProfileSuccess(data: BaseResponse)
}