import io.kotlintest.specs.WordSpec
import kotlinx.coroutines.runBlocking

class LiveServerTest : WordSpec() {

    init {

        "A connection to the demo subsonic demo server" should {

            val demoServer = ApiSonic(
                "http://demo.subsonic.org/rest/",
                "guest2",
                "guest",
                "1.15.0",
                "apisonic"
            )

            "return a ping" {
                runBlocking {
                    //TODO
                }
            }

            "return a licence" {
                //TODO
            }
        }
    }
}