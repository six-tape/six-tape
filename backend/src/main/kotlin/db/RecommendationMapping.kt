package cl.sixtape.db

import org.jetbrains.exposed.dao.id.IntIdTable

object RecommendationTable: IntIdTable("recommendation") {
    val movie = reference("movie", MovieTable.id)
}