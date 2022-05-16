import chat.*

object Chatservice {

    private var chats: MutableMap<Pair<Int, Int>, Chat> = mutableMapOf()
    private var countChats = 0

    fun sendMessage(userId: Int, newMessage: NewMessage) {
        chats.getOrPut(Pair(userId, newMessage.peerId)) { Chat(chatId = ++countChats) }.let {
            it.edit()
            it.chatMessages += Message(it.messageCount, newMessage.text)
        }
    }

    private fun Chat.edit() {
        messageCount += 1
        unreadMessageCount += 1
        readed = false
    }

    fun getChats(userId: Int): Map<Pair<Int, Int>, Chat> {
        return chats.filterKeys { it.first == userId || it.second == userId }
    }

    private val searchMessages: (Chat) -> Boolean = { it.chatMessages.none { !it.deleted } }

    fun deleteMessage(firstUserId: Int, secondUserId: Int, message: Message) {
        val key = Pair(firstUserId, secondUserId)
        chats[key]
            .let { it ?: throw NonExistentChat("Чат не существует") }
            .let {
                it.getMessageByIndex(message.messageId) ?: throw NonExistentMessage("Сообщение не существует")
                it.chatMessages[message.messageId - 1].deleted = true
                if (searchMessages(it))
                    deleteChat(firstUserId, secondUserId)
            }
    }

    private fun Chat.getMessageByIndex(index: Int): Message? {
        return chatMessages.getOrNull(index - 1)
    }

    fun deleteChat(firstUserId: Int, secondUserId: Int) {
        val key = Pair(firstUserId, secondUserId)
        chats.remove(key) ?: throw NonExistentChat("Чат не существует")
    }

    fun getMessage(chatId: Int, messageId: Int, maxCount: Int): List<Message> {
        val messageList = getChat(chatId)
            .let { it ?: throw NonExistentChat("Чат не существует") }
            .chatMessages.asSequence()
            .filter { it.messageId >= messageId && !it.readed && !it.deleted }
            .ifEmpty { throw NonExistentMessage("Сообщений нет") }
            .drop(messageId-1)
            .take(maxCount)
            .toList()
        readChat(chatId)
        return messageList
    }

    private val getChat: (chatId: Int) -> Chat? = { chats.values.elementAtOrNull(it - 1) }

    private fun readChat (chatId: Int) {
        val key = chats.keys.toList()[chatId - 1]
        chats[key]?.let {
            it.readed = true
            it.chatMessages.forEach { it.readed = true }
        }
    }

    fun clearChatservice() {
        chats.clear()
        countChats = 0
    }

//    private val searchMessages: (Chat) -> Boolean = { it.chatMessages.filter { it.deleted == false }.isEmpty() }

//    private val chatsSearch: (Pair<Int, Int>) -> Boolean = { chats.containsKey(it) }

}