package chat

import post.Post

data class Chat(
    var chatId: Int = 0,
    var chatMessages: Array<Message> = emptyArray(),
    var messageCount: Int = 0,
    var unreadMessageCount: Int = 0
)
