import note.*

object NoteService {

    private var notes: MutableMap<Int, Note> = mutableMapOf()
    private var noteKey = 1
    private var commentId = 1

    fun add(note: Note): Int {
        if (notes.isEmpty())
            notes[noteKey] = note
        else {
            notes[noteKey + 1] = note
            noteKey++
        }
        note.noteId = noteKey
        return note.noteId
    }

    fun delete(noteId: Int): Boolean {
        return notes.remove(noteId) != null
    }

    fun getById(noteId: Int): Note? {
        return notes[noteId]
    }

    fun edit(note: Note): Boolean {
        return if (getById(note.noteId) == null) false
        else {
            notes[note.noteId] = note
            true
        }
    }

    fun get(): MutableCollection<Note> {
        return notes.values
    }

    fun createComment(noteComment: NoteComment, noteId: Int): Int? {
        val note = getById(noteId)
        return if (note == null) null
        else {
            if (note.noteComments.isEmpty())
                note.noteComments += noteComment.copy(commentId = commentId)
            else {
                note.noteComments += noteComment.copy(commentId = commentId + 1)
                commentId++
            }
            return commentId
        }
    }

    fun deleteComment(noteId: Int, commentId: Int): Boolean {
        val note = getById(noteId)
        return if (note == null) false
        else {
            for (noteComment in note.noteComments)
                if (noteComment.commentId == commentId)
                    noteComment.deleted = true
                else
                    return false
            true
        }
    }

    fun editComment(noteId: Int, commentId: Int, message: String): Boolean {
        val note = getById(noteId)
        return if (note == null) false
        else {
            for (noteComment in note.noteComments)
                if (noteComment.commentId == commentId)
                    noteComment.message = message
                else
                    return false
            true
        }
    }

    //Возвращает в том числе комментарии, помеченные как удаленные
    fun getComments(noteId: Int): Array<NoteComment>? {
        return getById(noteId)?.noteComments
    }

    fun restoreComment(noteId: Int, commentId: Int): Boolean {
        var result = false
        val note = getById(noteId)
        if (note != null) {
            for (noteComment in note.noteComments)
                if (noteComment.commentId == commentId) {
                    noteComment.deleted = false
                    result = true
                } else
                    result = false
            }
        return result
    }

    fun cleanNoteService() {
        notes.clear()
        noteKey = 1
        commentId = 1
    }
}