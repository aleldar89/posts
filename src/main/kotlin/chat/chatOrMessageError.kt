package chat

class ChatOrMessageError (message: String): NullPointerException(message)

class NonExistentChat (message: String): RuntimeException(message)