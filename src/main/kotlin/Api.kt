import interfaces.Browsing
import models.Artists
import models.SubsonicResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Api(
    val url: String,
    val userName: String,
    val password: String,
    val apiVersion: String,
    val clientId: String
) {

    val okHttp: OkHttpClient by lazy {
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

//    val moshi = Moshi.Builder()
//        .add(KotlinJsonAdapterFactory())
//        .build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttp)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val client = retrofit.create(Browsing::class.java)


    suspend fun getArtists(): Artists {
        return client.getArtists().subsonicResponse.artists
    }
}