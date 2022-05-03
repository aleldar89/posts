package chat

data class Message(
    val messageId: Int = 0,
    val text: String = "",
    var readed: Boolean = false,
    var deleted: Boolean = false
)

data class NewMessage(
    val peerId: Int,
    val text: String
)