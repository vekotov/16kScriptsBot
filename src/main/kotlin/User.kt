import Longpoll.client
import br.com.devsrsouza.redissed.RedisObject
import br.com.devsrsouza.redissed.RedissedCommands
import com.petersamokhin.vksdk.core.http.paramsOf
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class User(database: String, commands: RedissedCommands, id: Int) : RedisObject(database, commands) {
    var adminLevel: Int by int(0)
    var banned: Boolean by boolean(false)
    var id: Int by int(0)
    var regData: Long by long(0)
    var notifications: Boolean by boolean(false)
    var mention: Boolean by boolean(false)
    var nickname: String by string("")
    val data: UserData by obj { UserData(it, commands) }

    init {
        this.id = id
        if (this.regData == 0L) regData = System.currentTimeMillis()
        if (this.nickname == "") {
            val response = client.call("users.get", paramsOf("user_ids" to id)).execute()
            val userInfo = Json { ignoreUnknownKeys = true; isLenient = true }.decodeFromString<VkUserData>(response)
            nickname = userInfo.response[0]["first_name"].toString()
        }
    }
}

class UserData(database: String, commands: RedissedCommands) : RedisObject(database, commands) {
    var postedScripts: MutableList<Int> = mutableListOf()
    var posts: MutableList<Int> = mutableListOf()
}

@Serializable
class VkUserData(var response: List<Map<String, String>>)