package note

data class Note(
    var noteId: Int = 0,
    val title: String? = null,
    val text: String,
    var noteComments: Array<NoteComment> = emptyArray()
)