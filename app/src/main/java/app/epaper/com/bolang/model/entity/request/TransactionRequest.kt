package app.epaper.com.bolang.model.entity.request

class TransactionRequest (
    var product: String = "",
    var amount: Int = 0,
    var period_in_month: Int = 0
)