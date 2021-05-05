package app.epaper.com.bolang.model.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Product {
    var id: String = ""
    var name: String = ""
    var period_in_month: String = ""
    var price: String = ""
    var created_at = ""
}