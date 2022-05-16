package chat

import java.lang.IndexOutOfBoundsException

class NonExistentChat (message: String): IndexOutOfBoundsException(message)
class NonExistentMessage (message: String): IndexOutOfBoundsException(message)