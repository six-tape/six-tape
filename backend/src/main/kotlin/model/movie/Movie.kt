package cl.sixtape.model.movie

import cl.sixtape.db.MovieDAO
import cl.sixtape.serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

/**
 *  A movie.
 *
 *  @property id UUID to identify the movie.
 *  @property title The title of the movie.
 *  @property runtime The runtime of the movie in minutes.
 *  @property watched Whether the movie has been watched or not.
 */
@Serializable
data class Movie(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val title: String,
    val runtime: Int,
    val watched: Boolean,
) {
    companion object {
        fun fromDAO (movieDAO: MovieDAO?): Movie? {
            movieDAO ?: return null

            return Movie(
                movieDAO.id.value,
                movieDAO.title,
                movieDAO.runtime,
                movieDAO.watched
            )
        }
    }
    override fun toString(): String = title
}