import note.*
import org.junit.Test

import org.junit.Assert.*
import post.Post

class NoteServiceTest {

    private val noteService = NoteService

    @Test
    fun deleteSuccessful() {
        noteService.add(Note(text = "первая заметка"))
        val result = noteService.delete(1)
        assertTrue(result)
    }

    @Test
    fun deleteNotSuccessful() {
        val result = noteService.delete(2)
        assertFalse(result)
    }

    @Test
    fun successfulGetById() {
        noteService.cleanNoteService()
        noteService.add(Note(text = "первая заметка"))
        val result = noteService.getById(1)
        assertNotNull(result)
    }

    @Test
    fun notSuccessfulGetById() {
        val result = noteService.getById(2)
        assertNull(result)
    }

    @Test
    fun editSuccessful() {
        noteService.cleanNoteService()
        noteService.add(Note(text = "первая заметка"))
        val result = noteService.edit(Note(noteId = 1, text = "изменение заметки"))
        assertTrue(result)
    }

    @Test
    fun editNotSuccessful() {
        val result = noteService.edit(Note(noteId = 2, text = "изменение заметки"))
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
        val result = noteService.createComment(NoteComment(message = "комментарий"), 1)
        assertEquals(1, result)
    }

    @Test
    fun createCommentNotSuccessful() {
        val result = noteService.createComment(NoteComment(message = "комментарий"), 2)
        assertEquals(null, result)
    }

    @Test
    fun deleteCommentSuccessful() {
        noteService.cleanNoteService()
        noteService.add(Note(text = "первая заметка"))
        noteService.createComment(NoteComment(message = "комментарий"), 1)
        val result = noteService.deleteComment(1,1)
        assertTrue(result)
    }

    @Test
    fun deleteCommentNotSuccessfulByNoteId() {
        val result = noteService.deleteComment(2,1)
        assertFalse(result)
    }

    @Test
    fun deleteCommentNotSuccessfulByCommentId() {
        val result = noteService.deleteComment(1,2)
        assertFalse(result)
    }

    @Test
    fun editCommentSuccessful() {
        noteService.cleanNoteService()
        noteService.add(Note(text = "первая заметка"))
        noteService.createComment(NoteComment(message = "комментарий"), 1)
        val result = noteService.editComment(1,1,"изменение комментария")
        assertTrue(result)
    }

    @Test
    fun editCommentNotSuccessfulByNoteId() {
        noteService.cleanNoteService()
        noteService.add(Note(text = "первая заметка"))
        noteService.createComment(NoteComment(message = "комментарий"), 1)
        val result = noteService.editComment(2,1,"изменение комментария")
        assertFalse(result)
    }

    @Test
    fun editCommentNotSuccessfulByCommentId() {
        noteService.cleanNoteService()
        noteService.add(Note(text = "первая заметка"))
        noteService.createComment(NoteComment(message = "комментарий"), 1)
        val result = noteService.editComment(1,2,"изменение комментария")
        assertFalse(result)
    }

    @Test
    fun getComments() {
        noteService.cleanNoteService()
        noteService.add(Note(text = "первая заметка"))
        noteService.createComment(NoteComment(message = "первый комментарий"), 1)
        noteService.createComment(NoteComment(message = "второй комментарий"), 1)
        noteService.createComment(NoteComment(message = "третий комментарий"), 1)
        noteService.getComments(1)
    }

    @Test
    fun restoreCommentSuccessful() {
        noteService.cleanNoteService()
        noteService.add(Note(text = "первая заметка"))
        noteService.createComment(NoteComment(message = "комментарий"), 1)
        noteService.deleteComment(1,1)
        val result = noteService.restoreComment(1,1)
        assertTrue(result)
    }

    @Test
    fun restoreCommentNotSuccessfulByNoteId() {
        noteService.deleteComment(1,1)
        val result = noteService.restoreComment(2,1)
        assertFalse(result)
    }

    @Test
    fun restoreCommentNotSuccessfulByCommentId() {
        noteService.deleteComment(1,1)
        val result = noteService.restoreComment(1,2)
        assertFalse(result)
    }

}