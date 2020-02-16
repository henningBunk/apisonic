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

    fun ignoredArticlesList(): List<String> {
        return ignoredArticles.split(' ')
    }

    fun asArtistList(): List<Artist> {
        return indices.flatMap { index: Index -> index.artists }
    }

    fun asIndexedMap(): Map<String, List<Artist>> {
        return indices.associate { index: Index -> Pair(index.name, index.artists) }
    }
}


