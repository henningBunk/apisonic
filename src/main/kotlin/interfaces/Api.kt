package interfaces

import models.*
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("ping")
    suspend fun ping(
    ): Response<PingResponse>

    @GET("getLicense")
    suspend fun getLicense(
    ): Response<LicenseResponse>

    @GET("getArtists")
    suspend fun getArtists(
    ): Response<ArtistsResponse>

    @GET("getArtist")
    suspend fun getArtist(
        @Query("id") id: String
    ): Response<ArtistResponse>

    @GET("getAlbum")
    suspend fun getAlbum(
        @Query("id") id: String
    ): Response<AlbumResponse>

    @GET("getSong")
    suspend fun getSong(
        @Query("id") id: String
    ): Response<SongResponse>

    @GET("getVideos")
    suspend fun getVideos(
    ): Response<VideosResponse>

    @GET("getVideoInfo")
    suspend fun getVideoInfo(
        @Query("id") id: String
    ): Response<VideoInfoResponse>

    @GET("getArtistInfo")
    suspend fun getArtistInfo(
        @Query("id") id: String,
        @Query("count") count: Int?,
        @Query("includeNotPresent") includeNotPresent: Boolean?
    ): Response<ArtistInfoResponse>

    @GET("getArtistInfo2")
    suspend fun getArtistInfo2(
        @Query("id") id: String,
        @Query("count") count: Int?,
        @Query("includeNotPresent") includeNotPresent: Boolean?
    ): Response<ArtistInfo2Response>

    @GET("getSimilarSongs")
    suspend fun getSimilarSongs(
        @Query("id") id: String,
        @Query("count") count: Int?
    ): Response<SimilarSongsResponse>

}
