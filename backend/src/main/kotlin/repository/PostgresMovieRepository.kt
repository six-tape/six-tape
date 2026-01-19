package cl.sixtape.repository

import cl.sixtape.db.MovieDAO
import cl.sixtape.db.MovieTable
import cl.sixtape.db.suspendTransaction
import cl.sixtape.model.movie.Movie
import cl.sixtape.model.movie.MovieCreation
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

    suspend fun updateMovie(movie: Movie): Movie = suspendTransaction {

    }

    override suspend fun deleteMovie(title: String): Boolean = suspendTransaction {
        val rowsDeleted = MovieTable.deleteWhere {
            MovieTable.title eq title
        }
        rowsDeleted == 1
    }

    private fun MovieDAO.toMovie() = Movie(
        id = this.id.value,
        title = this.title,
        runtime = this.runtime,
        watched = this.watched
    )
}