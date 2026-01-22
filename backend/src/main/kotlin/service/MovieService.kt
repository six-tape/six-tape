package cl.sixtape.service

import cl.sixtape.model.movie.MovieCreation
import cl.sixtape.model.movie.MovieFilters
import cl.sixtape.model.movie.MovieUpdate
import cl.sixtape.repository.MovieRepository
import java.time.Year
import java.util.UUID

class MovieService(private val movieRepository: MovieRepository) {
    suspend fun getAllMovies(filters: MovieFilters) {
        filters.title?.let { if (it.isBlank()) throw ValidationException("title cannot be blank") }
        filters.maxRuntime?.let {
            if (it <= 0) throw ValidationException("maxRuntime must be greater than 0")
            if (it > 4000) throw ValidationException("maxRuntime must be less than 4000")
        }
        filters.releaseYear?.let {
            if (it <= 0) throw ValidationException("releaseYear must be greater than 0")
            if (it > Year.now().value) throw ValidationException("releaseYear cannot be greater than ${Year.now().value}")
        }
        movieRepository.findAllMovies(filters)
    }

    suspend fun getMovieById(id: UUID) {
        movieRepository.findMovieById(id) ?: throw NotFoundException("Movie with id $id not found")
    }

    suspend fun addMovie(movie: MovieCreation) {
        if (movie.title.isBlank()) throw ValidationException("title cannot be blank")
        if (movie.runtime <= 0) throw ValidationException("runtime must be greater than 0")
        if (movie.runtime > 4000) throw ValidationException("runtime must be less than 4000")
        if (movie.releaseYear <= 0) throw ValidationException("releaseYear must be greater than 0")
        if (movie.releaseYear > Year.now().value) throw ValidationException("releaseYear cannot be greater than ${Year.now().value}")

        if (movieRepository.findMovieByTitleYear(movie.title, movie.releaseYear) != null) throw ConflictException("Movie with title ${movie.title} and release year ${movie.releaseYear} already exists")

        movieRepository.addMovie(movie)
    }

    suspend fun updateMovie(movie: MovieUpdate) = movieRepository.updateMovie(movie)

    suspend fun deleteMovie(id: UUID) = movieRepository.deleteMovie(id)
}