package cl.sixtape.db

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.util.UUID


object MovieTable: UUIDTable("movie") {
    val title = varchar("title", 50)
    val runtime = integer("runtime")
    val watched = bool("watched")
}

class MovieDAO(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<MovieDAO>(MovieTable)

    var title by MovieTable.title
    var runtime by MovieTable.runtime
    var watched by MovieTable.watched
}

suspend fun <T> suspendTransaction(block: suspend Transaction.() -> T): T =
    newSuspendedTransaction(Dispatchers.IO, statement = block)
