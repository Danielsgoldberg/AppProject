import android.util.Log
import com.grp8.appproject.ui.components.api.Drinks
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


const val BASE_URL = "https://thecocktaildb.com/api/json/v1/1/list.php?i=list"

class KtorClient {


    private val client = HttpClient(Android) {
        install(Logging) {
            level = LogLevel.ALL
        }

        install(ContentNegotiation)
        {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }

    }

    suspend fun get(): Drinks? {
        return try {
            client.get {
                url(BASE_URL)
                Log.v("Ktor URL", BASE_URL)
            }.body()
        } catch (e: Exception) {
            Log.v("Post Service", e.toString())
            null
        }

    }

    fun close() {
        client.close()
    }

}