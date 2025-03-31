package data

abstract class Content(open val id: Int, open val message: String, open var isDelete: Boolean) {

    abstract fun copy(id: Int = this.id, message: String = this.message): Content
}