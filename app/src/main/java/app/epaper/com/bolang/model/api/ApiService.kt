package app.epaper.com.bolang.model.api

import app.epaper.com.bolang.model.entity.reponse.*
import app.epaper.com.bolang.model.entity.request.LoginRequest
import app.epaper.com.bolang.model.entity.request.SignupRequest
import app.epaper.com.bolang.model.entity.request.TransactionRequest
import io.reactivex.Observable
import retrofit2.http.*


interface ApiService {
    @POST("api/v1/auth/login")
    fun callApiLogin(@Body request: LoginRequest): Observable<LoginResponse?>?

    @POST("api/v1/users")
    fun callApiSignup(@Body request: SignupRequest): Observable<SignupResponse?>?

    @GET("api/v1/users/{id}")
    fun callApiProfileUser(@HeaderMap header: Map<String, String>, @Path("id") id: String): Observable<ProfileResponse?>?

    @GET("api/v1/contents")
    fun callApiContentEdition(@HeaderMap header: Map<String, String>, @QueryMap(encoded = true) params :Map<String, String>): Observable<ContentResponse?>?

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