package cl.sixtape.model

import cl.sixtape.db.MovieDAO
import cl.sixtape.serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

/**
 *  A movie.
 *
 *  @property id UUID to identify the movie.
 *  @property title the title of the movie.
 *  @property releaseYear the release year of the movie.
 *  @property runtime the runtime of the movie in minutes.
 *  @property overview the overview of the movie.
 */
@Serializable
data class Movie(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val title: String,
    val releaseYear: Int,
    val runtime: Int,
    val overview: String = "",
) {
    companion object {
        fun fromDAO (movieDAO: MovieDAO?): Movie? {
            movieDAO ?: return null

            return Movie(
                movieDAO.id.value,
                movieDAO.title,
                movieDAO.releaseYear,
                movieDAO.runtime,
                movieDAO.overview
            )
        }
    }
    override fun toString(): String = "$title ($releaseYear)"
}