import interfaces.Api
import models.*
import network.AuthenticationInterceptor
import network.NetworkFactory

// Time spent: 18h

// TODO
// [] create better tests with mockwebserver
// [] add more api calls
// [] maybe do not re-use models? (artists vs artist)
// [] organize api calls a little better
// [x] create a dope md file with sweet badges
// [x] upload to github
// [x] configure github actions for unit testing
// [] upload to some sort of repository (maven, jitpack)
// [] think about nullability, error handling etc... write test cases
// [] add javadoc
// [] add Wrapper for Ids in form of inline classes?
// [] use token instead of password
// [] check if some fields are missing in the models. like starred for song, album artist.
// [] why do getStarred and getStarred2 return so different results?
// [] hide all methods which arent relevant for a consumer

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

    enum class ListType(val value: String) {
        RANDOM("random"),
        NEWEST("newest"),
        HIGHEST("highest"),
        FREQUENT("frequent"),
        RECENT("recent"),
        ALPHABETICAL_BY_NAME("alphabeticalByName"),
        ALPHABETICAL_BY_ARTIST("alphabeticalByArtist"),
        STARRED("starred"),
        BY_YEAR("byYear"),
        BY_GENRE("byGenre"),
    }

    //TODO test
    suspend fun getAlbumList(
        type: ListType,
        size: Int? = null,
        offset: Int? = null,
        fromYear: Int? = null,
        toYear: Int? = null,
        genre: String? = null,
        musicFolderId: String? = null
    ): List<AlbumList.Album> = api.getAlbumList(
        type.value, size, offset, fromYear, toYear, genre, musicFolderId
    ).subsonicResponse.albumList.albums

    //TODO test
    suspend fun getAlbumList2(
        type: ListType,
        size: Int? = null,
        offset: Int? = null,
        fromYear: Int? = null,
        toYear: Int? = null,
        genre: String? = null,
        musicFolderId: String? = null
    ): List<AlbumList2.Album> = api.getAlbumList2(
        type.value, size, offset, fromYear, toYear, genre, musicFolderId
    ).subsonicResponse.albumList2.albums

    //TODO test
    suspend fun getRandomSongs(
        size: Int? = null,
        genre: String? = null,
        fromYear: Int? = null,
        toYear: Int? = null,
        musicFolderId: String? = null
    ): List<Song> = api.getRandomSongs(
        size, genre, fromYear, toYear, musicFolderId
    ).subsonicResponse.randomSongs.randomSongs

    //TODO test
    suspend fun getSongsByGenre(
        genre: String,
        count: Int? = null,
        offset: Int? = null,
        musicFolderId: String? = null
    ): List<Song> = api.getSongsByGenre(
        genre, count, offset, musicFolderId
    ).subsonicResponse.songsByGenre.songsByGenre

    //TODO test
    suspend fun getNowPlaying(): List<NowPlayingEntry> = api.getNowPlaying().subsonicResponse.nowPlaying.entries

    //TODO test
    suspend fun getStarred(
        musicFolderId: String? = null
    ): Starred = api.getStarred(musicFolderId).subsonicResponse.starred

    //TODO test
    suspend fun getStarred2(
        musicFolderId: String? = null
    ): Starred2 = api.getStarred2(musicFolderId).subsonicResponse.starred2

    //TODO Test
    suspend fun startScan(): ScanStatus = api.startScan().subsonicResponse.scanStatus

    //Todo Test
    suspend fun getScanStatus(): ScanStatus = api.getScanStatus().subsonicResponse.scanStatus

//    suspend fun get():  {
//        return api.get().subsonicResponse.
//    }
//
//    suspend fun get():  {
//        return api.get().subsonicResponse.
//    }
//    suspend fun get():  {
//        return api.get().subsonicResponse.
//    }
//
//    suspend fun get():  {
//        return api.get().subsonicResponse.
//    }
//    suspend fun get():  {
//        return api.get().subsonicResponse.
//    }
//
//    suspend fun get():  {
//        return api.get().subsonicResponse.
//    }
//    suspend fun get():  {
//        return api.get().subsonicResponse.
//    }
//
//    suspend fun get():  {
//        return api.get().subsonicResponse.
//    }
//    suspend fun get():  {
//        return api.get().subsonicResponse.
//    }
//
//    suspend fun get():  {
//        return api.get().subsonicResponse.
//    }
//    suspend fun get():  {
//        return api.get().subsonicResponse.
//    }
//
//    suspend fun get():  {
//        return api.get().subsonicResponse.
//    }
//    suspend fun get():  {
//        return api.get().subsonicResponse.
//    }
//
//    suspend fun get():  {
//        return api.get().subsonicResponse.
//    }
//    suspend fun get():  {
//        return api.get().subsonicResponse.
//    }
//
//    suspend fun get():  {
//        return api.get().subsonicResponse.
//    }
}
