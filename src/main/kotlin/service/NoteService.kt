package service


import data.*
import exceptions.*

object NoteService : ContentService<Note>() {

    val comments = CommentService

    override fun restore(id: Int): Boolean {        // по условию задания восстанавливать заметки нельзя
        throw UnusedFunctionException()
    }

    override fun delete(id: Int): Boolean {         // удаление заметки и комментариев к ней
        if (id !in 1..(contents.lastIndex + 1) || contents[id - 1].isDelete) {
            throw IdOutOfBoundsException()
        }
        contents[id - 1].isDelete = true
        for ((index, comment) in comments.contents.withIndex()) {
            if (comment.noteId == id && !comment.isDelete) {
                comments.contents[index].isDelete = true
            }
        }
        return true
    }

    fun clear() {
        contents = arrayListOf()
        comments.contents = arrayListOf()
    }
}