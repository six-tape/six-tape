package cl.sixtape.model.movie

import cl.sixtape.model.serializers.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

/**
 * Represents an update to a movie's properties.
 *
 * This data class is used to encapsulate changes to a movie entity. Any property different from the id that is set to
 * null will remain unchanged in the corresponding movie record.
 *
 * @property id The id of the movie.
 * @property title The updated title of the movie, or null if no update is specified.
 * @property runtime The updated runtime of the movie in minutes, or null if no update is specified.
 * @property watched The updated watched status of the movie, or null if no update is specified.
 * @property releaseYear The updated release year of the movie, or null if no update is specified.
 */
@Serializable
data class MovieUpdate(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val title: String? = null,
    val runtime: Int? = null,
    val watched: Boolean? = null,
    val releaseYear: Int? = null
)
