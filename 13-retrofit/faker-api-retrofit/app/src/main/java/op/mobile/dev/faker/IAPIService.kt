package op.mobile.dev.faker

import retrofit2.http.GET

interface IAPIService {
    @GET("companies?_quantity=1")
    suspend fun getProperties(): APIServiceProperty
}