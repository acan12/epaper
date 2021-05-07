package app.epaper.com.bolang.model.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class User {
    var id: String = ""
    var email: String = ""
    var name: String = ""
    var phone: String = ""
    var address: String = ""
    var status: String = ""
    var blocked: Boolean = false
    var has_subscribe: Boolean = false
    var subscribe_period_in_month = 0
    var subscribe_start_at = ""
    var subscribe_end_at = ""
    var avatar_url = ""
    var avatar_thumb_url = ""
    var avatar_medium_url = ""
    var avatar_small_url = ""
    var created_at = ""
}