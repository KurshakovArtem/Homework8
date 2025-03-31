package data

data class Comment(
    override val id: Int,
    override val message: String,
    val noteId: Int,
    override var isDelete: Boolean = false
) : Content(id, message, isDelete) {
    override fun copy(id: Int, message: String): Comment {
        return Comment(id, message, noteId)
    }
}