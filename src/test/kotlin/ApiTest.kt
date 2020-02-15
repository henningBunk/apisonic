import io.kotlintest.shouldBe
import io.kotlintest.shouldNotBe
import io.kotlintest.specs.StringSpec

class ApiTest : StringSpec() {

    init {
        "length should return size of string" {
            "hello".length shouldBe 5
        }

        "the api can be initialized" {

            val api = Api("http://demo.subsonic.org/rest/", "guest2", "guest", "1.15.0", "apisonic")

            api.javaClass shouldBe Api::class.java
        }

        "the api can get clients" {
            val api = Api("http://demo.subsonic.org/rest/", "guest2", "guest", "1.15.0", "apisonic")
            api.getArtists() shouldNotBe null
        }
    }
}
