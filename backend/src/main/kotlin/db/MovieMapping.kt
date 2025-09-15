package cl.sixtape.db

import cl.sixtape.model.movie.Movie
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass


object MovieTable: IntIdTable("movie") {
    val title = varchar("title", 50)
    val releaseYear = integer("release_year")
    val overview = varchar("overview", 1000)
    val runtime = integer("runtime")
}

class MovieDAO(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<MovieDAO>(MovieTable)

    var title by MovieTable.title
    var releaseYear by MovieTable.releaseYear
    var overview by MovieTable.overview
    var runtime by MovieTable.runtime
}


fun daoToModel(dao: MovieDAO): Movie = Movie(
    dao.title,
    dao.releaseYear,
    dao.runtime,
    dao.overview
)