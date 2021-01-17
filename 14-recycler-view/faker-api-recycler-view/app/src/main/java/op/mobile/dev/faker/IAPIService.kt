package op.mobile.dev.faker

import retrofit2.http.GET

interface IAPIService {
    @GET("a9618bffd60e5236b68e138b96ea8adb/raw/22c34e1fdcb78db7e91e9875176f72741fba93bc/faker-companies.json")
    suspend fun getProperties(): List<APIServiceProperty>
}