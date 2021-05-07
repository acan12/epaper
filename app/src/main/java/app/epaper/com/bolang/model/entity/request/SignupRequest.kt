package app.epaper.com.bolang.model.entity.request

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SignupRequest (
    var name: String = "",
    var email: String = "",
    var phone: String = "",
    var address: String = "",
    var password: String = ""
): Parcelable