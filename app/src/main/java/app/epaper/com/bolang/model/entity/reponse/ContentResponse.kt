package app.epaper.com.bolang.model.entity.reponse

import app.beelabs.com.codebase.base.response.BaseResponse
import app.epaper.com.bolang.model.entity.Content
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class ContentResponse : BaseResponse() {
    lateinit var responseCode: String
    lateinit var responseStatus: String

    @JsonProperty("content")
    lateinit var contents: List<Content>
    lateinit var meta: Meta

    inner class Meta {
        @JsonProperty("transaction_status")
        var transactionStatus: String = ""

        @JsonProperty("has_subscribe")
        var hasSubscribe: Boolean = false
    }
}