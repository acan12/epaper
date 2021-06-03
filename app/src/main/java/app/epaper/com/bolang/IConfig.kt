package app.epaper.com.bolang

interface IConfig {
    companion object {
        const val yyyy_MM_dd = "yyyy-MM-dd"
        const val dd_MMM_yyyy= "dd MMM yyyy"
        const val DATE_FORMAT_TZ = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        const val SORT_ASC = "asc"
        const val SORT_DESC = "desc"

        val KEY_SUBSCRIBE_PRODUCT_SELECTED: String? = "subscribe_product_selected"
        const val SESSION_PERSONA_FIRSTNAME: String = "persona_firstname"
        const val TEXT_NAME_OF_PDF_REPLACEMENT: String = "[idPDF]"
        const val API_BASE_URL = "http://188.166.254.157:4000/"
        const val SESSION_TOKEN_CREDENTIAL = ""
        const val SESSION_LOGIN_KEY = "session_login"
        const val SESSION_SKIP_KEY = "session_skip"
        const val SESSION_SUBS_KEY = "session_subscriber"
        const val SESSION_STATUS_SUBS_KEY = "session_status_subscriber"
        const val ARG_URL_PDF_LINK = "urlPdfLink"

        const val STATUS_PENDING_TEXT = "pending"
        const val STATUS_DONE_TEXT = "done"
        const val STATUS_CANCEL_TEXT = "cancel"
        const val STATUS_PENDING = 1
        const val STATUS_DONE = 2
        const val STATUS_CANCEL = 3
        var ARG_ID_PDF = "idPdf"
        var ARG_NAME_PDF = "namePdf"

    }
}
