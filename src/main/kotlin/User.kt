import br.com.devsrsouza.redissed.RedisObject
import br.com.devsrsouza.redissed.RedissedCommands

class User(database: String, commands: RedissedCommands) : RedisObject(database, commands) {
    var adminLevel: Int by int(0)
    var banned: Boolean by boolean(false)
    var id: Int by int(0)
    var uid: Int by int(0)
    var regData: Long by long(0)
    var notifications: Boolean by boolean(false)
    var mention: Boolean by boolean(false)
    var nickname: String by string("Никнейм")
    val data: UserData by obj { UserData(it, commands) }
}

class UserData(database: String, commands: RedissedCommands) : RedisObject(database, commands) {
    var postedScripts: MutableList<Int> = mutableListOf()
    var posts: MutableList<Int> = mutableListOf()
}