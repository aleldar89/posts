import chat.*

object Chatservice {

    private var chats: MutableMap<Pair<Int, Int>, Chat> = mutableMapOf()
    private var countChats = 0

    private val chatsSearch: (Pair<Int, Int>) -> Boolean = { chats.containsKey(it) }
    private val getChat: (chatId: Int) -> Chat? = { chats.values.elementAt(it - 1) }

    private fun Chat.edit() {
        messageCount += 1
        unreadMessageCount += 1
        readed = false
    }

    fun sendMessage(userId: Int, newMessage: NewMessage) {
        chats.getOrPut(Pair(userId, newMessage.peerId)) { Chat(chatId = ++countChats) }.let {
            it.edit()
            it.chatMessages += Message(it.messageCount, newMessage.text)
        }
    }

    fun getChats(userId: Int): Map<Pair<Int, Int>, Chat> {
        return chats.filterKeys { it.first == userId || it.second == userId }
    }

    private val searchMessages: (Chat) -> Boolean = { it.chatMessages.none { !it.deleted } }

    fun deleteMessage(firstUserId: Int, secondUserId: Int, message: Message) {
        val key = Pair(firstUserId, secondUserId)
        chats[key].let { it?: throw NonExistentChat("Данные некорректны") }
                .chatMessages[message.messageId - 1].deleted = true

        if (chats[key]?.let { searchMessages(it) } == true)
            deleteChat(firstUserId, secondUserId)
    }

    fun deleteChat(firstUserId: Int, secondUserId: Int) {
        val key = Pair(firstUserId, secondUserId)
        chats.remove(key) ?: throw NonExistentChat("Указанный чат не существует")
    }

    fun getMessage(chatId: Int, messageId: Int, maxCount: Int): List<Message> {
        val messageList = getChat(chatId)
            .let { it?: throw NonExistentChat("Данные некорректны")  }
            .chatMessages.asSequence()
            .filter { it.messageId >= messageId && !it.readed && !it.deleted }
            .ifEmpty { throw NonExistentChat("Сообщений нет") }
            .drop(messageId-1)
            .take(maxCount)
            .toList()
        readChat(chatId)
        return messageList
    }

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

//        fun getMessages(chatId: Int, messageId: Int, maxCount: Int): MutableList<Message> {
//        val messageList = mutableListOf<Message>()
//        try {
//            messageList += getChat(chatId).chatMessages
//                .filter { it.messageId >= messageId && !it.readed && !it.deleted }
//                .subList(0, maxCount)
//            messageList.forEach { it.readed = true }
//            readChat(chatId)
//        } catch (e: RuntimeException) {
//            println("Веденные данные некорректны")
//        }
//        return messageList
//    }

//    fun deleteMessage(firstUserId: Int, secondUserId: Int, message: Message) {
//        val key = Pair(firstUserId, secondUserId)
//        chats[key].let { it?: throw NonExistentChat("Чат отсутствует") }
//            .chatMessages[message.messageId - 1]
//            .let { it?: throw NonExistentChat("Сообщение отсутствует") }.deleted = true
//
//        if (chats[key]?.let { searchMessages(it) } == true)
//            deleteChat(firstUserId, secondUserId)
//    }

//    fun deleteMessage(firstUserId: Int, secondUserId: Int, message: Message) {
//        val key = Pair(firstUserId, secondUserId)
//        if (chatsSearch(key))
//            chats[key]?.chatMessages?.set(message.messageId - 1, Message(deleted = true))
//        else
//            throw RuntimeException("Указанного сообщения не существует")
//        if (chats[key]?.let { searchMessages(it) } == true)
//            deleteChat(firstUserId, secondUserId)
//    }

//    fun deleteMessage(firstUserId: Int, secondUserId: Int, message: Message) {
//        val key = Pair(firstUserId, secondUserId)
//        try {
//            chats[key].let { it?: throw NonExistentChat("Чат отсутствует") }
//                .chatMessages[message.messageId - 1] = Message(deleted = true)
//        } catch (e: RuntimeException) {
//            println("Веденные данные некорректны")
//        }
//        if (chats[key]?.let { searchMessages(it) } == true)
//            deleteChat(firstUserId, secondUserId)
//    }

}