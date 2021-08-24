package op.mobile.app.dev.api.service

import op.mobile.app.dev.api.model.GitHubJobs
import retrofit2.http.GET

interface IGitHubJobs {
    @GET("positions.json?description=kotlin&page=1")
    suspend fun getResponse(): List<GitHubJobs>
}