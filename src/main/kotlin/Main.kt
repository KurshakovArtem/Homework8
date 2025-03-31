import data.Comment
import data.Note
import exceptions.UnusedFunctionException
import service.NoteService

fun main() {
    val noteService = NoteService
    var note = Note(5, "Заголовок", "Проверочный текст")
    println(noteService.add(Note(10, "Заголовок 2", "Проверочный текст 2")))
    println(noteService.add(note))
    println(noteService.add(note.copy(id = 12)))
    note = Note(7, "Заголовок 3", "123123")
    noteService.add(note)
    println(noteService.read(1, 4))
    noteService.delete(3)
    noteService.edit(Note(2, "", ""))
    try {
        noteService.restore(3)
    } catch (e: UnusedFunctionException) {
        println(e)
    }
    println(noteService.read(1, 4))
    println(noteService.getById(2))

    val comment = Comment(5, "Комментарий", 2)
    noteService.comments.add(comment)
    noteService.comments.add(Comment(1, "Комментарий2", 1))
    noteService.comments.edit(Comment(1, "Замена комментария", 2))
    println(noteService.comments.read(1, 2))
    noteService.delete(2)
    println(noteService.comments.read(1, 2))
    println(noteService.comments.readByNoteId(1))
}