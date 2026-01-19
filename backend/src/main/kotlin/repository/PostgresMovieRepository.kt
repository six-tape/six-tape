package cl.sixtape.repository

import cl.sixtape.db.MovieDAO
import cl.sixtape.db.MovieTable
import cl.sixtape.db.suspendTransaction
import cl.sixtape.model.movie.Movie
import cl.sixtape.model.movie.MovieCreation
import cl.sixtape.model.movie.MovieUpdate
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import java.util.UUID

class PostgresMovieRepository : MovieRepository {
    override suspend fun findAllMovies(): List<Movie> = suspendTransaction {
        MovieDAO.all().map { it.toMovie() }
    }

    override suspend fun findMovieById(id: UUID): Movie? = suspendTransaction {
        MovieDAO.findById(id)?.toMovie()
    }

    override suspend fun findMovieByTitle(title: String): Movie? = suspendTransaction {
        MovieDAO
            .find { (MovieTable.title eq title) }
            .firstOrNull()
            ?.toMovie()
    }

    override suspend fun addMovie(movie: MovieCreation): Movie = suspendTransaction {
        val newMovieDAO = MovieDAO.new {
            title = movie.title
            runtime = movie.runtime
            watched = movie.watched
        }
        newMovieDAO.toMovie()
    }

    override suspend fun updateMovie(movie: MovieUpdate): Movie? = suspendTransaction {
        val updatedMovieDao = MovieDAO.findByIdAndUpdate(movie.id) {
            movie.title?.run { it.title = this }
            movie.runtime?.run { it.runtime = this }
            movie.watched?.run { it.watched = this }
        }
        updatedMovieDao?.toMovie()
    }

    override suspend fun deleteMovie(id: UUID): Boolean = suspendTransaction {
        val MovieToDelete = MovieDAO.findById(id) ?: return@suspendTransaction false
        MovieToDelete.delete()
        true
    }

    private fun MovieDAO.toMovie() = Movie(
        id = this.id.value,
        title = this.title,
        runtime = this.runtime,
        watched = this.watched
    )
}