import Logger.logMessage
import Longpoll.reply
import br.com.devsrsouza.redissed.RedissedCommands
import br.com.devsrsouza.redissed.clients.redissed
import io.lettuce.core.RedisClient
import io.lettuce.core.RedisURI

fun main() {
    println("Bot is starting...")
    val redis = RedisClient.create(RedisURI.create("localhost", 6379))
    val conn = redis.connect()
    val sync = conn.sync()

    val commands: RedissedCommands = sync.redissed
    val database = "users:db"
    println("Database connected!")

    Longpoll.client.onMessage { messageEvent ->
        logMessage(messageEvent.message)
        val user = User("$database:${messageEvent.message.fromId}", commands)
        when (messageEvent.message.text.split(" ")[0]) {
            "привет" -> {
                messageEvent.reply("Привет, ${user.nickname}")
            }
            "ник" -> {
                if (messageEvent.message.text.split(" ").size >= 2) {
                    user.nickname = messageEvent.message.text.removePrefix("ник ")
                    messageEvent.reply("Установлен ник ${user.nickname}")
                } else
                    messageEvent.reply("Введите 'ник <новый ник>'")
            }
        }
    }
    println("Starting longpolling...")
    Longpoll.client.startLongPolling()
}