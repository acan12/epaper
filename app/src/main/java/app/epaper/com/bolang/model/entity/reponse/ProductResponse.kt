package app.epaper.com.bolang.model.entity.reponse

import app.beelabs.com.codebase.base.response.BaseResponse
import app.epaper.com.bolang.model.entity.Product
import app.epaper.com.bolang.model.entity.Transaction
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ProductResponse : BaseResponse() {
    lateinit var responseCode: String
    lateinit var responseStatus: String
    lateinit var data: List<Product>
}