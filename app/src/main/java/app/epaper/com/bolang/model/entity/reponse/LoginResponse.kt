package app.epaper.com.bolang.model.entity.reponse

import app.beelabs.com.codebase.base.response.BaseResponse
import app.epaper.com.bolang.model.entity.User
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class LoginResponse : BaseResponse() {
    lateinit var responseCode: String
    lateinit var responseStatus: String
    lateinit var token: String

    @JsonProperty("user")
    lateinit var data: User


}