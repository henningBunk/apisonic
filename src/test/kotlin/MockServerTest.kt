import io.kotlintest.matchers.collections.shouldContain
import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.kotlintest.specs.WordSpec
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

class MockServerTest : WordSpec() {

    init {

        val server = MockWebServer().apply { start() }

        val baseUrl = server.url("/")

        val mockServer = ApiSonic(
            baseUrl.toString(),
            "someUser",
            "aStrongPassword",
            "1.15.0",
            "apisonic"
        )

        "The artists object from the mock web server" should {

            server.enqueue(MockResponse().setBody(getJson("responses/getArtists.json")))

            val artists = runBlocking { mockServer.getArtists() }

            "have ignored articles" {
                artists.ignoredArticles shouldBe "The El La Los Las Le"
            }

            "contain 'Los' as an ignored article" {
                artists.ignoredArticlesList() shouldContain "Los"
            }

            "have 10 indices" {
                artists.indices.size shouldBe 10
            }

            "contain the letter U" {
                artists.indices.firstOrNull { it.name == "U" } shouldNotBe null
            }

            "not contain the letter X" {
                artists.indices.firstOrNull { it.name == "X" } shouldBe null
            }

            "return the artists as a simple list containing the same amount of artists" {
                var count = 0
                artists.indices.forEach {
                    count += it.artists.size
                }
                artists.asArtistList().size shouldBe count
            }

            "return the artists as a simple list containing 'Silence is Sexy'" {
                artists.asArtistList().firstOrNull { it.name == "Silence is Sexy" } shouldNotBe null
            }

            "return a map with all indices" {
                artists.indices.size shouldBe artists.asIndexedMap().size
            }

            "return a map with same amount of artists" {
                var count = 0
                artists.indices.forEach {
                    count += it.artists.size
                }
                artists.asIndexedMap().values.flatten().size shouldBe count
            }
        }
    }
}