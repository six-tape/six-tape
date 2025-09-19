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
    val releaseYear = integer("release_year")
    val overview = varchar("overview", 1000)
    val runtime = integer("runtime")
}

class MovieDAO(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<MovieDAO>(MovieTable)

    var title by MovieTable.title
    var releaseYear by MovieTable.releaseYear
    var overview by MovieTable.overview
    var runtime by MovieTable.runtime
}

suspend fun <T> suspendTransaction(block: suspend Transaction.() -> T): T =
    newSuspendedTransaction(Dispatchers.IO, statement = block)
