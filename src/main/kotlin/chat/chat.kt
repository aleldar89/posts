package chat

data class Chat(
    var chatId: Int = 0,
    var chatMessages: MutableList<Message> = mutableListOf(),
    var messageCount: Int = 0,
    var unreadMessageCount: Int = 0,
    var readed: Boolean = false
)
