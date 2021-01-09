import java.io.File

object Settings {
    var token: String? = null
    var groupID: Int? = null

    init {
        loadSettings()
    }

    private fun readSettings(settingsFile: File) {
        val lines = settingsFile.readLines()
        token = lines[0]
        groupID = lines[1].toInt()
    }

    private fun askAndWriteNewSettings(settingsFile: File) {
        print("Enter your group ID: ")
        groupID = readLine()!!.toInt()
        print("Enter your token: ")
        token = readLine()!!
        settingsFile.createNewFile()
        settingsFile.writeText(
            """
            $token
            $groupID
        """.trimIndent()
        )
    }

    private fun loadSettings() {
        val settingsFile = File("settings.cfg")
        if (settingsFile.exists()) {
            readSettings(settingsFile)
        } else {
            askAndWriteNewSettings(settingsFile)
        }
    }
}