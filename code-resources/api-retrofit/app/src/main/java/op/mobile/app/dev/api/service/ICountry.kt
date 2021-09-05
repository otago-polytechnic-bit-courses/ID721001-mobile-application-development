package op.mobile.app.dev.api.service

import op.mobile.app.dev.api.model.Country
import retrofit2.http.GET

interface ICountry {
    @GET("raw")
    suspend fun getResponse(): List<Country>
}