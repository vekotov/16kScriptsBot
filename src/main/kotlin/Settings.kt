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
/*
groupId: 200012732
token: "221ae798f0fe70c99946a62fa5e914e97b3ecf94e2d3162c7c673e68fb6bca84c0199fbb690b689760556"
*/