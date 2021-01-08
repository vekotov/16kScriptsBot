import com.charleskorn.kaml.Yaml
import kotlinx.serialization.Serializable
import java.io.File

fun main() {
    println("Hello world!")
    val settingsFile = File("settings.yml")
    val settings: Settings
    settings = if (settingsFile.exists()) {
        readSettings(settingsFile)
    } else {
        writeNewSettings(settingsFile)
    }

    println("" + settings.groupId + " " + settings.token)
}

fun readSettings(settingsFile: File): Settings {
    return Yaml.default.decodeFromString(Settings.serializer(), settingsFile.readText())
}

fun writeNewSettings(settingsFile: File): Settings {
    print("Enter your group ID: ")
    val groupId = readLine()!!.toInt()
    print("Enter your token: ")
    val token = readLine()!!
    val settings = Settings(groupId, token)
    settingsFile.createNewFile()
    settingsFile.writeText(Yaml.default.encodeToString(Settings.serializer(), settings))
    return Settings(groupId, token)
}

@Serializable
data class Settings(val groupId: Int, val token: String)