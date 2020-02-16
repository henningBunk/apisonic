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

    suspend fun getArtists(): Artists = api.getArtists().subsonicResponse.artists

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
//
//    suspend fun getSimilarSongs2(): List<Song> {
//        return api.getSimilarSongs2().subsonicResponse.similarSongs
//    }
//
//    suspend fun getTopSongs(): List<Song> {
//        return api.getTopSongs().subsonicResponse.topSongs
//    }
//
//    suspend fun getMusicFolders(): List<Folder> {
//        return api.getMusicFolders().subsonicResponse.musicFolders
//    }
//
//    suspend fun getIndexes(): Indexes {
//        return api.getIndexes().subsonicResponse.indexes
//    }
//
//    suspend fun getMusicDirectory(): MusicDirectory {
//        return api.getMusicDirectory().subsonicResponse.musicDirectory
//    }
//
//    suspend fun getGenres(): List<Genre> {
//        return api.getGenres().subsonicResponse.genres
//    }
//
//    suspend fun get():  {
//        return api.get().subsonicResponse.
//    }
//
//    suspend fun get():  {
//        return api.get().subsonicResponse.
//    }
}
