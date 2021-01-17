package op.mobile.dev.faker

import retrofit2.http.GET

interface IAPIService {
    @GET("positions.json?description=go&page=1")
    suspend fun getProperties(): List<APIServiceProperty>
}