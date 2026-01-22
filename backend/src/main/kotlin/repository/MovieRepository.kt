package cl.sixtape.repository

import cl.sixtape.model.movie.Movie
import cl.sixtape.model.movie.MovieCreation
import cl.sixtape.model.movie.MovieFilters
import cl.sixtape.model.movie.MovieUpdate
import java.util.UUID

interface MovieRepository {
    suspend fun findAllMovies(filters: MovieFilters): List<Movie>
    suspend fun findMovieById(id: UUID): Movie?
    suspend fun findMovieByTitleYear(title: String, releaseYear: Int): Movie?
    suspend fun addMovie(movie: MovieCreation): Movie
    suspend fun updateMovie(movie: MovieUpdate): Movie?
    suspend fun deleteMovie(id: UUID): Boolean
}
