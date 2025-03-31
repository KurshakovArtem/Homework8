package service

interface Service<T> {
    fun add(value: T): Int
    fun delete(id: Int): Boolean
    fun edit(value: T): Boolean
    fun read(startId: Int, lastId: Int): List<T>
    fun getById(id: Int): T
    fun restore(id: Int): Boolean
}