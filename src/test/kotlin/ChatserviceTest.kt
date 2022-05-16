import org.junit.Test
import chat.*

class ChatserviceTest {

    private val chatService = Chatservice

    @Test
    fun sendMessageOrCreateChat() {
        chatService.sendMessage(1, NewMessage(2, "hello"))
        chatService.sendMessage(1, NewMessage(2, "how do you do?"))
        chatService.sendMessage(2, NewMessage(3, "hi"))
    }

    @Test
    fun deleteChatShouldNotThrow() {
        chatService.deleteChat(1, 2)
    }

    @Test (expected = NonExistentChat::class)
    fun deleteChatShouldThrow()  {
        chatService.deleteChat(1, 3)
    }

    @Test
    fun getChats() {
        chatService.clearChatservice()
        chatService.sendMessage(1, NewMessage(2, "hello"))
        chatService.getChats(1)
    }

    @Test
    fun getMessagesShouldNotThrow() {
        chatService.clearChatservice()
        chatService.sendMessage(1, NewMessage(2, "hello"))
        chatService.sendMessage(1, NewMessage(2, "how do you do?"))
        chatService.sendMessage(1, NewMessage(2, "my name is anon"))
        chatService.sendMessage(1, NewMessage(2, "do you hear me?"))
        chatService.getMessage(1, 1, 4)
    }

    @Test (expected = NonExistentMessage::class)
    fun getMessagesShouldThrowIfEmpty() {
        chatService.clearChatservice()
        chatService.sendMessage(1, NewMessage(2, "hello"))
        chatService.sendMessage(1, NewMessage(2, "how do you do?"))
        chatService.sendMessage(1, NewMessage(2, "my name is anon"))
        chatService.sendMessage(1, NewMessage(2, "do you hear me?"))
        chatService.getMessage(1, 1, 4)
        chatService.getMessage(1, 1, 4)
    }

    //todo
    @Test (expected = NonExistentChat::class)
    fun getMessagesShouldThrowIfWrongChat() {
        chatService.clearChatservice()
        chatService.sendMessage(1, NewMessage(2, "hello"))
        chatService.sendMessage(1, NewMessage(2, "how do you do?"))
        chatService.sendMessage(1, NewMessage(2, "my name is anon"))
        chatService.sendMessage(1, NewMessage(2, "do you hear me?"))
        chatService.getMessage(0, 1, 4)
    }

    @Test
    fun deleteSuccesful() {
        chatService.clearChatservice()
        chatService.sendMessage(1, NewMessage(2, "hello"))
        chatService.deleteMessage(1, 2, Message(1))
    }

    //todo
    @Test (expected = NonExistentMessage::class)
    fun deleteWrongMessage() {
        chatService.clearChatservice()
        chatService.sendMessage(1, NewMessage(2, "hello"))
        chatService.deleteMessage(1, 2, Message(2))
    }

    @Test (expected = NonExistentChat::class)
    fun deleteFromWrongChat() {
        chatService.clearChatservice()
        chatService.sendMessage(1, NewMessage(2, "hello"))
        chatService.deleteMessage(0, 2, Message(1))
    }

}