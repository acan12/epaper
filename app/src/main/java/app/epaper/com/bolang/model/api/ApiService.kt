package app.epaper.com.bolang.model.api

import app.epaper.com.bolang.model.entity.reponse.*
import app.epaper.com.bolang.model.entity.request.LoginRequest
import app.epaper.com.bolang.model.entity.request.SignupRequest
import app.epaper.com.bolang.model.entity.request.TransactionRequest
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST


interface ApiService {
    @POST("api/v1/auth/login")
    fun callApiLogin(@Body request: LoginRequest): Observable<LoginResponse?>?

    @POST("api/v1/users")
    fun callApiSignup(@Body request: SignupRequest): Observable<SignupResponse?>?

    @GET("api/v1/users/1")
    fun callApiProfileUser(@HeaderMap header: Map<String, String>): Observable<ProfileResponse?>?

    @GET("api/v1/contents")
    fun callApiContentEdition(@HeaderMap header: Map<String, String>): Observable<ContentResponse?>?

    @GET("api/v1/package_products")
    fun callApiProduct(@HeaderMap header: Map<String, String>): Observable<ProductResponse?>?

    @POST("api/v1/transactions")
    fun callApiTransaction(
        @HeaderMap header: Map<String, String>,
        @Body request: TransactionRequest
    ): Observable<SubscribeResponse?>?

    @GET("api/v1/transactions")
    fun callApiListTransactionUser(
        @HeaderMap header: Map<String, String>
    ): Observable<TransactionResponse?>?
}