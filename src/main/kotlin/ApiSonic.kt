import interfaces.Api
import models.*
import network.*

// Time spent: 10h
// begin 1030

// TODO
// [] create better tests with mockwebserver
// [] create a dope md file with sweet badges
// [] upload to github
// [] configure github actions for unit testing
// [] upload to some sort of repository (maven, jitpack)
// [] think about nullability, error handling etc... write test cases
// [] add javadoc
// [] add Wrapper for Ids in form of inline classes?
// [] use token instead of password

class ApiSonic(
    url: String,
    userName: String,
    password: String,
    apiVersion: String,
    clientId: String
) {

    private val authenticationInterceptor = AuthenticationInterceptor(
        userName, password, apiVersion, clientId
    )
    private val network = NetworkFactory(
        authenticationInterceptor
    )

    private val api = network.createApi(Api::class.java, url)

    suspend fun ping(): PingResponse = api.ping().subsonicResponse

    suspend fun getLicense(): License = api.getLicense().subsonicResponse.license

    suspend fun getArtists(): Artists = api.getArtists().subsonicResponse.artists

    //TODO Test
    suspend fun getGenres(): List<Genre> = api.getGenres().subsonicResponse.genres.genres

    //TODO Test this
    suspend fun getArtist(id: String): Artist = api.getArtist(id).subsonicResponse.artist

    //TODO Test this
    suspend fun getAlbum(id: String): Album = api.getAlbum(id).subsonicResponse.album

    //TODO Test this
    suspend fun getSong(id: String): Song = api.getSong(id).subsonicResponse.song

    //TODO Test this
    suspend fun getVideos(): List<Video> = api.getVideos().subsonicResponse.videos.videos

    //TODO Test this
    suspend fun getVideoInfo(id: String): VideoInfo = api.getVideoInfo(id).subsonicResponse.videoInfo

    //TODO Test this
    suspend fun getArtistInfo(
        id: String,
        count: Int? = null,
        includeNotPresent: Boolean? = null
    ): ArtistInfo = api.getArtistInfo(id, count, includeNotPresent).subsonicResponse.artistInfo

    //TODO Test this
    suspend fun getArtistInfo2(
        id: String,
        count: Int? = null,
        includeNotPresent: Boolean? = null
    ): ArtistInfo = api.getArtistInfo2(id, count, includeNotPresent).subsonicResponse.artistInfo2

    suspend fun getSimilarSongs(
        id: String,
        count: Int? = null
    ): List<Song> {
        return api.getSimilarSongs(id, count).subsonicResponse.similarSongs.similarSongs
    }

    //TODO Test
    suspend fun getSimilarSongs2(
        id: String,
        count: Int? = null
    ): List<Song> {
        return api.getSimilarSongs2(id, count).subsonicResponse.similarSongs.similarSongs
    }

    //TODO Test
    suspend fun getTopSongs(
        artist: String,
        count: Int? = null
    ): List<Song> {
        return api.getTopSongs(artist, count).subsonicResponse.topSongs.topSongs
    }

    //TODO Test
    suspend fun getMusicFolders(): List<MusicFolder> {
        return api.getMusicFolders().subsonicResponse.musicFolders.musicFolders
    }

    //TODO Test
    suspend fun getIndexes(
        musicFolderId: String? = null,
        ifModifiedSince: Long? = null
    ): Indexes {
        return api.getIndexes(musicFolderId, ifModifiedSince).subsonicResponse.indexes
    }

    //TODO test
    suspend fun getMusicDirectory(
        id: String
    ): Directory {
        return api.getMusicDirectory(id).subsonicResponse.directory
    }


//    suspend fun get():  {
//        return api.get().subsonicResponse.
//    }
//
//    suspend fun get():  {
//        return api.get().subsonicResponse.
//    }
}
