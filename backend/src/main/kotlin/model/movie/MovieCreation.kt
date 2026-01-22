package cl.sixtape.model.movie

import kotlinx.serialization.Serializable

/**
 * Represents the data required to create a new movie entry.
 *
 * @property title The title of the movie.
 * @property runtime The runtime of the movie in minutes.
 * @property watched Indicates whether the movie has been watched.
 * @property releaseYear The release year of the movie.
 */
@Serializable
data class MovieCreation(
    val title: String,
    val runtime: Int,
    val watched: Boolean,
    val releaseYear: Int
) {
    override fun toString(): String = title
}

