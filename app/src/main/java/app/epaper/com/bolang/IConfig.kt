package app.epaper.com.bolang

interface IConfig {
    companion object {

        val KEY_SUBSCRIBE_PRODUCT_SELECTED: String? = "subscribe_product_selected"
        const val SESSION_PERSONA_FIRSTNAME: String = "persona_firstname"
        const val TEXT_NAME_OF_PDF_REPLACEMENT: String = "[idPDF]"
        const val API_BASE_URL = "http://188.166.254.157:4000/"
        const val SESSION_TOKEN_CREDENTIAL = ""
        const val SESSION_LOGIN_KEY = "session_login"
        const val SESSION_SKIP_KEY = "session_skip"
        const val SESSION_SUBS_KEY = "session_subscriber"
        const val ARG_URL_PDF_LINK = "urlPdfLink"
        var ARG_ID_PDF = "idPdf"
        var ARG_NAME_PDF = "namePdf"

    }
}
