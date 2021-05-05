package app.epaper.com.bolang.model.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Transaction {
    var id: String = ""
    var product: String = ""
    var period_in_month: String = ""
    var payment: String = ""
    var status: String = ""
    var amount: String = ""
    var buyer_name: Boolean = false
    var buyer_phone = 0
    var user_id = ""
    var created_at = ""
}