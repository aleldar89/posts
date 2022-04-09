package note

data class NoteComment(
    val commentId: Int = 0,
    var message: String,
    var deleted: Boolean = false
)