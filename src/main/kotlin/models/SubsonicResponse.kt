package models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

interface SubsonicResponse

@JsonClass(generateAdapter = true)
data class Response<T : SubsonicResponse>(
    @Json(name = "subsonic-response") val subsonicResponse: T
)

