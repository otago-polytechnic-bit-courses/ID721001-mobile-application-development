package op.mobile.app.dev.graysono.travelling.api

import op.mobile.app.dev.graysono.travelling.model.Country
import retrofit2.http.GET

interface ICountry {
    @GET("raw")
    suspend fun getResponse(): List<Country>
}
