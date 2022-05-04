import chat.*

object Chatservice {

    private var chats: MutableMap<Pair<Int, Int>, Chat> = mutableMapOf()
    private var countChats = 0

    //проверка коллекции пар-ключей на уже созданный чат двух пользователей
    private val chatsSearch: (Pair<Int, Int>) -> Boolean = { chats.containsKey(it) }

    //отдает чат по id
    private val getChat: (chatId: Int) -> Chat = { chats.values.elementAt(it - 1) }

    //увеличивает счетчики сообщений в чате на 1 при добавлении сообщения
    private fun Chat.edit() {
        messageCount += 1
        unreadMessageCount += 1
    }

    //высылает сообщение или создает чат, если сообщение первое
    fun sendMessage(userId: Int, newMessage: NewMessage) {
        chats.getOrPut(Pair(userId, newMessage.peerId)) { Chat(chatId = ++countChats) }.let {
            it.edit()
            it.chatMessages += Message(it.messageCount, newMessage.text)
        }
    }

    //получение чатов пользователя
    fun getChats (userId: Int): Map<Pair<Int, Int>,Chat> {
       return chats.filterKeys { it.first == userId || it.second == userId }
    }

    //проверка, есть ли неудаленные сообщения
    private val searchMessages: (Chat) -> Boolean = { it.chatMessages.none { !it.deleted } }

    //"удаляет" сообщение (меняет статус на "удалено")
    fun deleteMessage(firstUserId: Int, secondUserId: Int, message: Message) {
        val key = Pair(firstUserId, secondUserId)
        if (chatsSearch(key))
            chats[key]?.chatMessages?.set(message.messageId - 1, Message(deleted = true))
        else
            throw RuntimeException("Указанного сообщения не существует")
        if (chats[key]?.let { searchMessages(it) } == true)
            deleteChat(firstUserId, secondUserId)
    }

    //удаление чата пользователем
    fun deleteChat(firstUserId: Int, secondUserId: Int) {
        val key = Pair(firstUserId, secondUserId)
        chats.remove(key) ?: throw NonExistentChat("Указанный чат не существует")
    }

    //Отдает список сообщений из чата со статусом "непрочитано" и "неудалено"
    fun getMessages(chatId: Int, messageId: Int, maxCount: Int): MutableList<Message> {
        val messageList = mutableListOf<Message>()
        try {
            messageList += getChat(chatId).chatMessages
                .filter { it.messageId >= messageId && !it.readed && !it.deleted }
                .subList(0, maxCount)
            messageList.forEach { it.readed = true }
            readChat(chatId)
        } catch (e: RuntimeException) {
            println("Веденные данные некорректны")
        }
        return messageList
    }

    //Отмечает чат и вложенные сообщения прочитанными
    private fun Chat.readMessages() {
        readed = true
        chatMessages.forEach { it.readed = true }
    }

    //Поиск чата по chatId и его прочтение
    private fun readChat (chatId: Int) {
        val key = chats.keys.toList()[chatId - 1]
        chats[key]?.readMessages()
    }

    fun clearChatservice() {
        chats.clear()
        countChats = 0
    }

    //отдает кол-во сообщений в чате
//    private val getMessageCount: (Pair<Int, Int>) -> Int = { chats[it]?.messageCount ?: 0 }

//    private val searchMessages: (Chat) -> Boolean = { it.chatMessages.filter { it.deleted == false }.isEmpty() }

//        fun sendMessage(userId: Int, newMessage: NewMessage) {
//        val key = Pair(userId, newMessage.peerId)
//        if (!chatsSearch(key))
//            chats[key] = Chat(
//                chatId = ++countChats,
//                chatMessages = arrayOf(Message(1, newMessage.text)),
//                messageCount = 1,
//                unreadMessageCount = 1
//            )
//        else {
//            chats[key]?.let { chats[key]?.edit(it) } //здесь все считает нормально
//            val message = Message(getMessageCount(key), newMessage.text)
//            chats[key]?.chatMessages?.plus(message.copy())
//        }
//    }

}