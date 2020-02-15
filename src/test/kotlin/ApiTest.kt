import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.kotlintest.specs.StringSpec

class ApiTest : StringSpec() {

    init {

        "the api can be initialized" {
            val api = Api("http://demo.subsonic.org/rest/", "guest2", "guest", "1.15.0", "apisonic")

            api.javaClass shouldBe Api::class.java
        }

        val api = Api("http://demo.subsonic.org/rest/", "guest2", "guest", "1.15.0", "apisonic")

        "the api can get artists" {
            api.getArtists() shouldNotBe null
        }

        "the api has an artist named 'Antígona'" {
            api.getArtists().indices[0]
                .artists
                .find { artist -> artist.name == "Antígona" } shouldNotBe null
        }

        "the api can get an artist" {
            api.getArtist("5") shouldNotBe null
        }

        "the api can get an album" {
            api.getAlbum("5") shouldNotBe null
        }

        "the api can get a song" {
            api.getSong("72") shouldNotBe null
        }

        "the api can get videos" {
            api.getVideos() shouldNotBe null
            api.getVideos()[0].title shouldBe "Big Buck Bunny"
        }

        "the api can get videoinfos" {
            api.getVideoInfo("460") shouldNotBe null
            api.getVideoInfo("460").id shouldBe "460"
        }

        "the api can get artist infos" {
            api.getArtistInfo("5") shouldNotBe null
            api.getArtistInfo("5").lastFmUrl shouldBe "https://www.last.fm/music/johndoe"
        }

        "the api can get artist infos 2" {
            api.getArtistInfo2("5") shouldNotBe null
            api.getArtistInfo2("5").lastFmUrl shouldBe "https://www.last.fm/music/+noredirect/Ant%C3%ADgona"
        }

    }
}
