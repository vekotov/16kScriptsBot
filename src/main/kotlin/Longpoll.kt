import com.petersamokhin.vksdk.core.client.VkApiClient
import com.petersamokhin.vksdk.core.model.VkSettings
import com.petersamokhin.vksdk.core.model.event.MessageNew
import com.petersamokhin.vksdk.core.model.objects.Keyboard
import com.petersamokhin.vksdk.http.VkOkHttpClient

object Longpoll {
    val client = VkApiClient(
        Settings.groupID!!,
        Settings.token!!,
        VkApiClient.Type.Community,
        VkSettings(VkOkHttpClient())
    )

    fun sendMessage(peerId: Int, message: String, keyboard: Keyboard? = null) {

        client.sendMessage {
            this.peerId = peerId
            this.message = message
            if (keyboard != null) this.keyboard = keyboard
        }.execute()
    }

    fun MessageNew.reply(message: String) {
        Longpoll.sendMessage(this.message.peerId, message)
    }

    fun MessageNew.replyWithKeyboard(message: String, keyboard: Keyboard) {
        Longpoll.sendMessage(this.message.peerId, message, keyboard)
    }
}