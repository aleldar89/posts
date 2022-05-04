package chat

data class Chat(
    var chatId: Int = 0,
    var chatMessages: Array<Message> = emptyArray(),
//    var chatMessages: MutableList<Message>,
    var messageCount: Int = 0,
    var unreadMessageCount: Int = 0,
    var readed: Boolean = false
)
