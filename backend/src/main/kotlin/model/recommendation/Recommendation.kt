package cl.sixtape.model.recommendation

import cl.sixtape.model.user.User
import cl.sixtape.model.movie.Movie
import kotlinx.serialization.Serializable

@Serializable
data class Recommendation(
    val movie: Movie,
    val user: User,
    val watched: Boolean
)