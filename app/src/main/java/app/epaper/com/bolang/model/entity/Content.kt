package app.epaper.com.bolang.model.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class Content {
    var id = 0
    var title = ""
    var cover_image_url = ""
    var cover_image_thumb_url = ""
    var cover_image_small_url = ""
    var pdf_doc = ""
}