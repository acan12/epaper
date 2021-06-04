package app.epaper.com.bolang.ui.impl

import app.beelabs.com.codebase.base.BaseDialog
import app.beelabs.com.codebase.base.contract.IView
import app.beelabs.com.codebase.base.response.BaseResponse

interface IDialogSubscribeView : IView {
    fun handleButtonNextClicked(dialog: BaseDialog)

    fun handleButtonJoinClicked(dialog: BaseDialog)
}