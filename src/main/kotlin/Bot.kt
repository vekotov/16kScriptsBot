import Logger.logMessage

fun main() {
    println("Hello world!")
    Longpoll.client.onMessage { messageEvent ->
        logMessage(messageEvent.message)
        when (messageEvent.message.text) {
            "привет" -> Longpoll.sendMessage(messageEvent.message.peerId, "Лежать плюс сосать!")
        }
    }
    Longpoll.client.startLongPolling()
    println("Longpolling started!")
}