package models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtistsResponse(@Json(name = "artists") val artists: Artists) : SubsonicResponse

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


