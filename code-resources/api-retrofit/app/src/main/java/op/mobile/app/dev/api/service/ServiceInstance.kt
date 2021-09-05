package op.mobile.app.dev.api.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://gist.githubusercontent.com/Grayson-Orr/49223bcae755ef9479b3150182dc125e/"

object ServiceInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val retrofitService: ICountry by lazy {
        retrofit.create(ICountry::class.java)
    }
}