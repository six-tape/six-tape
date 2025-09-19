package cl.sixtape.model

import java.util.UUID

interface MovieRepository {
    suspend fun allMovies(): List<Movie>
    suspend fun movieById(id: UUID): Movie?
    suspend fun movieByTitle(title: String): Movie?
    suspend fun addMovie(movie: Movie)
    suspend fun removeMovie(title: String): Boolean
}
