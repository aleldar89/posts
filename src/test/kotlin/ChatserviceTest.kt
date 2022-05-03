import org.junit.Test
import org.junit.Assert.*
import chat.*

class ChatserviceTest {

    private val chatService = Chatservice

    @Test
    fun deleteChatShouldNotThrow() {
        chatService.sendMessage(1, NewMessage(2, "hello"))
        chatService.deleteChat(1, 2)
    }

    @Test (expected = NonExistentChat::class)
    fun deleteChatShouldThrow()  {
        chatService.clearChatservice()
        chatService.sendMessage(1, NewMessage(2, "hello"))
        chatService.deleteChat(1, 3)
    }

    @Test
    fun getChats() {
        chatService.clearChatservice()
        chatService.sendMessage(1, NewMessage(2, "hello"))
        chatService.sendMessage(1, NewMessage(3, "hi"))
        chatService.getChats(1)
    }

    @Test
    fun sendMessageOrCreateChat() {
        chatService.clearChatservice()
        chatService.sendMessage(1, NewMessage(2, "hello"))
        chatService.sendMessage(1, NewMessage(2, "how do you do?"))
        chatService.sendMessage(1, NewMessage(2, "my name is anon"))
        chatService.sendMessage(2, NewMessage(3, "hi"))
    }

    @Test
    fun deleteMessageShouldNotThrow() {
        chatService.clearChatservice()
        chatService.sendMessage(1, NewMessage(2, "hello"))
        chatService.deleteMessage(1,2,Message(1))
    }

    @Test (expected = RuntimeException::class)
    fun deleteMessageShouldThrow() {
        chatService.clearChatservice()
        chatService.sendMessage(1, NewMessage(2, "hello"))
        chatService.deleteMessage(1,2,Message(2))
    }

    @Test
    fun getMessages() {
        chatService.clearChatservice()
        chatService.sendMessage(1, NewMessage(2, "hello"))
        chatService.sendMessage(1, NewMessage(2, "how do you do?"))
        chatService.sendMessage(1, NewMessage(2, "my name is anon"))
        chatService.sendMessage(1, NewMessage(2, "do you hear me?"))
        chatService.getMessages(1,1,4)
    }

}