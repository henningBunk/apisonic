package models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Response(
    @Json(name = "subsonic-response") val subsonicResponse: SubsonicResponse
)

@JsonClass(generateAdapter = true)
data class SubsonicResponse(
    @Json(name = "status") val status: String,
    @Json(name = "version") val version: String,
    @Json(name = "artists") val artists: Artists
)


@JsonClass(generateAdapter = true)
data class Artists(
    @Json(name = "ignoredArticles") val ignoredArticles: String,
    @Json(name = "index") val indices: List<Index>
) {

    @JsonClass(generateAdapter = true)
    data class Index(
        @Json(name = "name") val name: String,
        @Json(name = "artist") val artists: List<Artist>
    )
}

@JsonClass(generateAdapter = true)
data class Artist(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "coverArt") val coverArt: String,
    @Json(name = "albumCount") val albumCount: Int
)
