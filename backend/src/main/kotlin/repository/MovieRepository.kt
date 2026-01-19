package cl.sixtape.repository

import cl.sixtape.model.movie.Movie
import cl.sixtape.model.movie.MovieCreation
import java.util.UUID

interface MovieRepository {
    suspend fun findAllMovies(): List<Movie>
    suspend fun findMovieById(id: UUID): Movie?
    suspend fun findMovieByTitle(title: String): Movie?
    suspend fun addMovie(movie: MovieCreation): Movie
    suspend fun updateMovie(id: UUID, watched: Boolean)
    suspend fun deleteMovie(id: UUID): Boolean
}
