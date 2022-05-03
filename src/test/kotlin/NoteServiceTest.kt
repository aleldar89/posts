import note.*
import org.junit.Test
import org.junit.Assert.*

class NoteServiceTest {

    private val noteService = NoteService

    @Test
    fun deleteSuccessful() {
        noteService.add(Note(text = "первая заметка"))
        val result = noteService.delete(0)
        assertTrue(result)
    }

    @Test
    fun deleteNotSuccessful() {
        val result = noteService.delete(1)
        assertFalse(result)
    }

    @Test
    fun successfulGetById() {
        noteService.cleanNoteService()
        noteService.add(Note(text = "первая заметка"))
        val result = noteService.getById(0)
        assertNotNull(result)
    }

    @Test
    fun notSuccessfulGetById() {
        val result = noteService.getById(1)
        assertNull(result)
    }

    @Test
    fun editSuccessful() {
        noteService.cleanNoteService()
        noteService.add(Note(text = "первая заметка"))
        val result = noteService.edit(Note(noteId = 0, text = "изменение заметки"))
        assertTrue(result)
    }

    @Test
    fun editNotSuccessful() {
        val result = noteService.edit(Note(noteId = 1, text = "изменение заметки"))
        assertFalse(result)
    }

    @Test ()
    fun getNoteCollection() {
        noteService.cleanNoteService()
        noteService.add(Note(text = "первая заметка"))
        noteService.add(Note(text = "вторая заметка"))
        noteService.add(Note(text = "третья заметка"))
        noteService.get()
    }

    @Test
    fun createCommentSuccessful() {
        noteService.cleanNoteService()
        noteService.add(Note(text = "первая заметка"))
        val result = noteService.createComment(NoteComment(message = "комментарий"), 0)
        assertEquals(0, result)
    }

    @Test
    fun createCommentNotSuccessful() {
        val result = noteService.createComment(NoteComment(message = "комментарий"), 1)
        assertEquals(null, result)
    }

    @Test
    fun deleteCommentSuccessful() {
        noteService.cleanNoteService()
        noteService.add(Note(text = "первая заметка"))
        noteService.createComment(NoteComment(message = "комментарий"), 0)
        val result = noteService.deleteComment(0,0)
        assertTrue(result)
    }

    @Test
    fun deleteCommentNotSuccessfulByNoteId() {
        val result = noteService.deleteComment(1,0)
        assertFalse(result)
    }

    @Test
    fun deleteCommentNotSuccessfulByCommentId() {
        val result = noteService.deleteComment(0,1)
        assertFalse(result)
    }

    @Test
    fun editCommentSuccessful() {
        noteService.cleanNoteService()
        noteService.add(Note(text = "первая заметка"))
        noteService.createComment(NoteComment(message = "комментарий"), 0)
        val result = noteService.editComment(0,0,"изменение комментария")
        assertTrue(result)
    }

    @Test
    fun editCommentNotSuccessfulByNoteId() {
        noteService.cleanNoteService()
        noteService.add(Note(text = "первая заметка"))
        noteService.createComment(NoteComment(message = "комментарий"), 0)
        val result = noteService.editComment(1,0,"изменение комментария")
        assertFalse(result)
    }

    @Test
    fun editCommentNotSuccessfulByCommentId() {
        noteService.cleanNoteService()
        noteService.add(Note(text = "первая заметка"))
        noteService.createComment(NoteComment(message = "комментарий"), 0)
        val result = noteService.editComment(0,1,"изменение комментария")
        assertFalse(result)
    }

    @Test
    fun getComments() {
        noteService.cleanNoteService()
        noteService.add(Note(text = "первая заметка"))
        noteService.createComment(NoteComment(message = "первый комментарий"), 0)
        noteService.createComment(NoteComment(message = "второй комментарий"), 0)
        noteService.createComment(NoteComment(message = "третий комментарий"), 0)
        noteService.getComments(0)
    }

    @Test
    fun restoreCommentSuccessful() {
        noteService.cleanNoteService()
        noteService.add(Note(text = "первая заметка"))
        noteService.createComment(NoteComment(message = "комментарий"), 0)
        noteService.deleteComment(0,0)
        val result = noteService.restoreComment(0,0)
        assertTrue(result)
    }

    @Test
    fun restoreCommentNotSuccessfulByNoteId() {
        noteService.deleteComment(0,0)
        val result = noteService.restoreComment(1,0)
        assertFalse(result)
    }

    @Test
    fun restoreCommentNotSuccessfulByCommentId() {
        noteService.deleteComment(0,0)
        val result = noteService.restoreComment(0,1)
        assertFalse(result)
    }

}