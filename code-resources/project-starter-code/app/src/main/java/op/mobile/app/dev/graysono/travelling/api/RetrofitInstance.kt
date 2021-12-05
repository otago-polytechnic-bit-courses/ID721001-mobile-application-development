package op.mobile.app.dev.graysono.travelling.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance(private val url: String) {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val retrofitCountryService: ICountry by lazy {
        retrofit.create(ICountry::class.java)
    }
}
