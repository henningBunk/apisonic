package interfaces

import models.Response
import models.SubsonicResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Browsing {

    @GET("getArtists")
    suspend fun getArtists(): Response

    @GET("getArtist")
    fun getArtist(
        @Query("id") id: Int
    ): Response

}
