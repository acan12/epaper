package app.epaper.com.bolang.model.entity.reponse

import app.beelabs.com.codebase.base.response.BaseResponse
import app.epaper.com.bolang.model.entity.User
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ProfileResponse : BaseResponse() {
    lateinit var responseCode: String
    lateinit var responseStatus: String
    lateinit var data: User

}