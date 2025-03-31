package data

data class Note(
    override val id: Int,
    override val message: String,
    val title: String,
    override var isDelete: Boolean = false
) : Content(id, message, isDelete) {
    override fun copy(id: Int, message: String): Note {
        return Note(id, message, title)
    }

}