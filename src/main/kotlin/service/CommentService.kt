package service

import data.*
import exceptions.*

object CommentService : ContentService<Comment>() {

    private val notes = NoteService
    override fun add(value: Comment): Int {
        for (noteList in notes.contents) {
            if (value.noteId == noteList.id && !noteList.isDelete) {
                return super.add(value)
            }
        }
        throw IdOutOfBoundsException()
    }

    override fun edit(value: Comment): Boolean {
        for (noteList in notes.contents) {
            if (value.noteId == noteList.id) {
                return super.edit(value)
            }
        }
        throw IdOutOfBoundsException()
    }

    fun readByNoteId(noteId: Int): List<Comment> {            // Возвращает комментарии относящиеся к одной заметке
        val tempList: ArrayList<Comment> = arrayListOf()
        if (noteId in 1..notes.contents.lastIndex + 1 && !notes.contents[noteId - 1].isDelete) {
            for (comment in contents) {
                if (comment.noteId == noteId && !comment.isDelete) {
                    tempList += comment
                }
            }
            return tempList
        }
        throw IdOutOfBoundsException()
    }
}