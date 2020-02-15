import com.squareup.moshi.Moshi
import interfaces.Browsing
import models.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


// Time spent: 10h

class Api(
    private val url: String,
    private val userName: String,
    private val password: String,
    private val apiVersion: String,
    private val clientId: String
) {

    private val okHttp: OkHttpClient by lazy {
        OkHttpClient().newBuilder()
            //.cache(cache)
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .addInterceptor(Interceptor.invoke { chain ->
                val request = chain.request()
                val url = request.url.newBuilder()
                    .addQueryParameter("u", userName)
                    .addQueryParameter("p", password)
                    .addQueryParameter("v", apiVersion)
                    .addQueryParameter("c", clientId)
                    .addQueryParameter("f", "json")
                    .build()
                return@invoke chain.proceed(request.newBuilder().url(url).build())
            })
            .build()
    }

    private val moshi = Moshi.Builder()
//        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttp)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    private val client = retrofit.create(Browsing::class.java)

    suspend fun getArtists(): Artists {
        return client.getArtists().subsonicResponse.artists
    }

    suspend fun getArtist(id: String): Artist {
        return client.getArtist(id).subsonicResponse.artist
    }

    suspend fun getAlbum(id: String): Album {
        return client.getAlbum(id).subsonicResponse.album
    }

    suspend fun getSong(id: String): Song {
        return client.getSong(id).subsonicResponse.song
    }

    suspend fun getVideos(): List<Video> {
        return client.getVideos().subsonicResponse.videos.videos
    }

    suspend fun getVideoInfo(id: String): VideoInfo {
        return client.getVideoInfo(id).subsonicResponse.videoInfo
    }


    suspend fun getArtistInfo(
        id: String,
        count: Int? = null,
        includeNotPresent: Boolean? = null
    ): ArtistInfo {
        return client.getArtistInfo(id, count, includeNotPresent).subsonicResponse.artistInfo
    }

    suspend fun getArtistInfo2(
        id: String,
        count: Int? = null,
        includeNotPresent: Boolean? = null
    ): ArtistInfo {
        return client.getArtistInfo2(id, count, includeNotPresent).subsonicResponse.artistInfo2
    }

//    suspend fun getSimilarSongs(): List<Song> {
//        return client.getSimilarSongs().subsonicResponse.similarSongs
//    }
//
//    suspend fun getSimilarSongs2(): List<Song> {
//        return client.getSimilarSongs2().subsonicResponse.similarSongs
//    }
//
//    suspend fun getTopSongs(): List<Song> {
//        return client.getTopSongs().subsonicResponse.topSongs
//    }
//
//    suspend fun getMusicFolders(): List<Folder> {
//        return client.getMusicFolders().subsonicResponse.musicFolders
//    }
//
//    suspend fun getIndexes(): Indexes {
//        return client.getIndexes().subsonicResponse.indexes
//    }
//
//    suspend fun getMusicDirectory(): MusicDirectory {
//        return client.getMusicDirectory().subsonicResponse.musicDirectory
//    }
//
//    suspend fun getGenres(): List<Genre> {
//        return client.getGenres().subsonicResponse.genres
//    }
//
//    suspend fun get():  {
//        return client.get().subsonicResponse.
//    }
//
//    suspend fun get():  {
//        return client.get().subsonicResponse.
//    }
}
