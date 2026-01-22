package cl.sixtape.repository

import cl.sixtape.db.MovieDAO
import cl.sixtape.db.MovieTable
import cl.sixtape.db.suspendTransaction
import cl.sixtape.model.movie.Movie
import cl.sixtape.model.movie.MovieCreation
import cl.sixtape.model.movie.MovieFilters
import cl.sixtape.model.movie.MovieUpdate
import org.jetbrains.exposed.sql.Op
import org.jetbrains.exposed.sql.*
import java.util.UUID

class PostgresMovieRepository : MovieRepository {
    override suspend fun findAllMovies(filters: MovieFilters): List<Movie> = suspendTransaction {
        MovieDAO.find {
            val conditions = mutableListOf<Op<Boolean>>()
            filters.title?.let {
                val escapedTitle = it.replace("]", "]]")
                    .replace("%", "]%")
                    .replace("_", "]_")
                conditions.add(MovieTable.title.like(LikePattern("%$escapedTitle%", escapeChar= ']')))
            }
            filters.watched?.let { conditions.add(MovieTable.watched eq it)}
            filters.maxRuntime?.let { conditions.add(MovieTable.runtime lessEq it)}
            filters.releaseYear?.let { conditions.add(MovieTable.releaseYear eq it)}
            conditions.compoundAnd()
        }.map { it.toMovie() }
    }

    override suspend fun findMovieById(id: UUID): Movie? = suspendTransaction {
        MovieDAO.findById(id)?.toMovie()
    }

    override suspend fun findMovieByTitleYear(title: String, releaseYear: Int): Movie? = suspendTransaction{
        MovieDAO.find {
            MovieTable.title eq title and (MovieTable.releaseYear eq releaseYear)
        }.firstOrNull()?.toMovie()
    }

    override suspend fun addMovie(movie: MovieCreation): Movie = suspendTransaction {
        val newMovieDAO = MovieDAO.new {
            title = movie.title
            runtime = movie.runtime
            watched = movie.watched
            releaseYear = movie.releaseYear
        }
        newMovieDAO.toMovie()
    }

    override suspend fun updateMovie(movie: MovieUpdate): Movie? = suspendTransaction {
        val updatedMovieDao = MovieDAO.findByIdAndUpdate(movie.id) {
            if (movie.title != null) it.title = movie.title
            if (movie.runtime != null) it.runtime = movie.runtime
            if (movie.watched != null) it.watched = movie.watched
            if (movie.releaseYear != null) it.releaseYear = movie.releaseYear
        }
        updatedMovieDao?.toMovie()
    }

    override suspend fun deleteMovie(id: UUID): Boolean = suspendTransaction {
        val movieToDelete = MovieDAO.findById(id) ?: return@suspendTransaction false
        movieToDelete.delete()
        true
    }

    private fun MovieDAO.toMovie() = Movie(
        id = this.id.value,
        title = this.title,
        runtime = this.runtime,
        watched = this.watched,
        releaseYear = this.releaseYear
    )
}