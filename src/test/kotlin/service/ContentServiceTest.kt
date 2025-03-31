package service

import data.*
import exceptions.*
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class ContentServiceTest {

    @Before
    fun clearBeforeTest() {
        NoteService.clear()
    }

    @Test
    fun createCorrectNoteAdd() {
        val noteService = NoteService
        val note = Note(5, "Проверочный текст", "Заголовок")
        noteService.add(note)
        assertEquals(Note(1, "Проверочный текст", "Заголовок", false), noteService.getById(1))
    }

    @Test
    fun createCorrectCommentAdd() {
        val noteService = NoteService
        val note = Note(5, "Проверочный текст", "Заголовок")
        noteService.add(note)
        noteService.comments.add(Comment(5, "Текст комментария", 1))
        assertEquals(Comment(1, "Текст комментария", 1, false), noteService.comments.getById(1))
    }

    @Test(expected = IdOutOfBoundsException::class)
    fun createIncorrectCommentAdd() {
        val noteService = NoteService
        val note = Note(5, "Проверочный текст", "Заголовок")
        noteService.add(note)
        noteService.comments.add(Comment(5, "Текст комментария", 2))
    }

    @Test
    fun correctUseDeleteNote() {
        val noteService = NoteService
        val note = Note(5, "Проверочный текст", "Заголовок")
        noteService.add(note)
        assertTrue(noteService.delete(1))
    }

    @Test
    fun correctUseDeleteComment() {
        val noteService = NoteService
        val note = Note(5, "Проверочный текст", "Заголовок")
        noteService.add(note)
        noteService.comments.add(Comment(5, "Текст комментария", 1))
        assertTrue(noteService.comments.delete(1))
    }

    @Test(expected = IdOutOfBoundsException::class)
    fun correctUseDeleteNoteAndInsertedComment() {
        val noteService = NoteService
        val note = Note(5, "Проверочный текст", "Заголовок")
        noteService.add(note)
        noteService.comments.add(Comment(5, "Текст комментария", 1))
        noteService.delete(1)
        noteService.comments.getById(1)
    }

    @Test(expected = IdOutOfBoundsException::class)
    fun incorrectUseDeleteNote() {
        val noteService = NoteService
        val note = Note(5, "Проверочный текст", "Заголовок")
        noteService.add(note)
        noteService.delete(5)
    }

    @Test(expected = IdOutOfBoundsException::class)
    fun incorrectUseDeleteComment() {
        val noteService = NoteService
        val note = Note(5, "Проверочный текст", "Заголовок")
        noteService.add(note)
        noteService.comments.add(Comment(5, "Текст комментария", 1))
        noteService.comments.delete(5)
    }

    @Test
    fun correctReadNote() {
        val noteService = NoteService
        val note = Note(5, "Проверочный текст", "Заголовок")
        noteService.add(note)
        noteService.add(note.copy(3, "Проверочный текст 2", "Заголовок 2"))
        assertEquals(
            arrayListOf(
                Note(1, "Проверочный текст", "Заголовок"),
                Note(2, "Проверочный текст 2", "Заголовок 2")
            ),
            noteService.read(1, 2)
        )
    }

    @Test(expected = IdRangeOutOfBoundsException::class)
    fun incorrectReadNote() {
        val noteService = NoteService
        val note = Note(5, "Проверочный текст", "Заголовок")
        noteService.add(note)
        noteService.add(note.copy(3, "Проверочный текст 2", "Заголовок 2"))
        assertEquals(
            arrayListOf(
                Note(1, "Проверочный текст", "Заголовок"),
                Note(2, "Проверочный текст 2", "Заголовок 2")
            ),
            noteService.read(1, 3)
        )
    }

    @Test
    fun correctReadCommentByNoteId() {
        val noteService = NoteService
        val note = Note(5, "Проверочный текст", "Заголовок")
        noteService.add(note)
        noteService.comments.add(Comment(5, "Текст комментария", 1))
        noteService.comments.add(Comment(3, "Текст комментария 2", 1))
        assertEquals(
            arrayListOf(
                Comment(1, "Текст комментария", 1),
                Comment(2, "Текст комментария 2", 1)
            ),
            noteService.comments.readByNoteId(1)
        )
    }

    @Test(expected = IdOutOfBoundsException::class)
    fun incorrectReadCommentByNoteId() {
        val noteService = NoteService
        val note = Note(5, "Проверочный текст", "Заголовок")
        noteService.add(note)
        noteService.comments.add(Comment(5, "Текст комментария", 1))
        noteService.comments.add(Comment(3, "Текст комментария 2", 1))
        assertEquals(
            arrayListOf(
                Comment(1, "Текст комментария", 1),
                Comment(2, "Текст комментария 2", 1)
            ),
            noteService.comments.readByNoteId(2)
        )
    }

    @Test
    fun correctGetByIdNote() {
        val noteService = NoteService
        val note = Note(5, "Проверочный текст", "Заголовок")
        noteService.add(note)
        assertEquals(Note(1, "Проверочный текст", "Заголовок"), noteService.getById(1))
    }

    @Test(expected = IdOutOfBoundsException::class)
    fun incorrectGetByIdNote() {
        val noteService = NoteService
        val note = Note(5, "Проверочный текст", "Заголовок")
        noteService.add(note)
        assertEquals(Note(1, "Проверочный текст", "Заголовок"), noteService.getById(2))
    }

    @Test
    fun correctEditNote() {
        val noteService = NoteService
        val note = Note(5, "Проверочный текст", "Заголовок")
        noteService.add(note)
        assertTrue(noteService.edit(Note(1, "Исправленный текст", "Заголовок")))
    }

    @Test(expected = IdOutOfBoundsException::class)
    fun incorrectEditNote() {
        val noteService = NoteService
        val note = Note(5, "Проверочный текст", "Заголовок")
        noteService.add(note)
        noteService.edit(Note(2, "Исправленный текст", "Заголовок"))
    }

    @Test
    fun correctEditComment() {
        val noteService = NoteService
        val note = Note(5, "Проверочный текст", "Заголовок")
        noteService.add(note)
        noteService.comments.add(Comment(5, "Текст комментария", 1))
        assertTrue(noteService.comments.edit(Comment(1, "Исправленный текст комментария", 1)))
    }

    @Test(expected = IdOutOfBoundsException::class)
    fun incorrectEditComment() {
        val noteService = NoteService
        val note = Note(5, "Проверочный текст", "Заголовок")
        noteService.add(note)
        noteService.comments.add(Comment(5, "Текст комментария", 1))
        noteService.comments.edit(Comment(1, "Исправленный текст комментария", 2))
    }

    @Test
    fun correctRestoreComment() {
        val noteService = NoteService
        val note = Note(5, "Проверочный текст", "Заголовок")
        noteService.add(note)
        noteService.comments.add(Comment(5, "Текст комментария", 1))
        noteService.comments.delete(1)
        assertTrue(noteService.comments.restore(1))
    }

    @Test(expected = IdOutOfBoundsException::class)
    fun incorrectRestoreComment() {
        val noteService = NoteService
        val note = Note(5, "Проверочный текст", "Заголовок")
        noteService.add(note)
        noteService.comments.add(Comment(5, "Текст комментария", 1))
        noteService.comments.delete(1)
        noteService.comments.restore(2)
    }

    @Test(expected = UnusedFunctionException::class)
    fun correctRestoreNote() {
        val noteService = NoteService
        val note = Note(5, "Проверочный текст", "Заголовок")
        noteService.add(note)
        noteService.delete(1)
        noteService.restore(1)
    }
}