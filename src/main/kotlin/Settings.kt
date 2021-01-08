import com.charleskorn.kaml.Yaml
import kotlinx.serialization.Serializable
import java.io.File

object Settings {
    @Serializable
    data class SettingsData(val groupId: Int, val token: String)

    val data = loadSettings()

    private fun readSettings(settingsFile: File): SettingsData {
        return Yaml.default.decodeFromString(SettingsData.serializer(), settingsFile.readText())
    }

    private fun writeNewSettings(settingsFile: File): SettingsData {
        print("Enter your group ID: ")
        val groupId = readLine()!!.toInt()
        print("Enter your token: ")
        val token = readLine()!!
        val settings = SettingsData(groupId, token)
        settingsFile.createNewFile()
        settingsFile.writeText(Yaml.default.encodeToString(SettingsData.serializer(), settings))
        return SettingsData(groupId, token)
    }

    private fun loadSettings(): SettingsData {
        val settingsFile = File("settings.yml")
        return if (settingsFile.exists()) {
            readSettings(settingsFile)
        } else {
            writeNewSettings(settingsFile)
        }
    }
}