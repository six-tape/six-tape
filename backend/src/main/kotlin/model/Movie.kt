package cl.sixtape.model

import kotlinx.serialization.Serializable

/**
 *  A movie.
 *
 *  @property title the title of the movie.
 *  @property releaseYear the release year of the movie.
 *  @property runtime the runtime of the movie in minutes.
 *  @property overview the overview of the movie.
 */
@Serializable
data class Movie(
    val title: String,
    val releaseYear: Int,
    val runtime: Int,
    val overview: String = "",
) {
    override fun toString(): String = "$title ($releaseYear)"
}