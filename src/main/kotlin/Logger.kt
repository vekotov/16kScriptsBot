import com.petersamokhin.vksdk.core.model.event.IncomingMessage

object Logger {
    fun logMessage(message: IncomingMessage) {
        println(
            """
        -----------------------------
        NEW MESSAGE EVENT
        SENDER ID: ${message.fromId}
        PEER ID: ${message.peerId}
        TEXT: ${message.text}
        PAYLOAD: ${message.payload}
        ATTACHMENTS: ${message.attachments.joinToString { "$it, " }}
    """.trimIndent()
        )
    }
}