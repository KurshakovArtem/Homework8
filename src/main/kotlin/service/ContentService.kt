package service

import data.Content
import exceptions.*

abstract class ContentService<T : Content> : Service<T> {
    internal var contents: ArrayList<T> = arrayListOf()


    override fun add(value: T): Int {
        // Не смог сделать чтобы функция copy возвращала Generic параметр T
        // воспользовался приведением типов (безопасно)
        contents += value.copy(id = if (contents.isEmpty()) 1 else (contents.lastIndex + 2)) as T
        return contents.last.id
    }

    override fun delete(id: Int): Boolean {
        if (id !in 1..(contents.lastIndex + 1) || contents[id - 1].isDelete) {
            throw IdOutOfBoundsException()
        }
        contents[id - 1].isDelete = true
        return true
    }

    override fun read(startId: Int, lastId: Int): List<T> {
        if (startId !in 1..(contents.lastIndex + 1) ||
            lastId !in 1..(contents.lastIndex + 1) ||
            startId > lastId                     // Проверка на пустой список не нужна(проверяется условием выше)
        ) {
            throw IdRangeOutOfBoundsException()
        }
        contents.subList(startId - 1, lastId) // lastId - включительно
        val tempList: ArrayList<T> = arrayListOf()
        for (value in contents.subList(startId - 1, lastId)) {
            if (!value.isDelete) {
                tempList += value
            }
        }
        return tempList
    }

    override fun getById(id: Int): T {
        if (id !in 1..(contents.lastIndex + 1) || contents[id - 1].isDelete) {
            throw IdOutOfBoundsException()
        }
        return contents[id - 1]
    }

    override fun edit(value: T): Boolean {
        if (value.id !in 1..(contents.lastIndex + 1) || contents[value.id - 1].isDelete) {
            throw IdOutOfBoundsException()
        }
        contents[value.id - 1] = value.copy() as T
        return true
    }

    override fun restore(id: Int): Boolean {
        if (id !in 1..(contents.lastIndex + 1) || !contents[id - 1].isDelete) {
            throw IdOutOfBoundsException()
        }
        contents[id - 1].isDelete = false
        return true
    }

}