package cl.sixtape.model

import cl.sixtape.db.MovieDAO
import cl.sixtape.db.MovieTable
import cl.sixtape.db.suspendTransaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import java.util.UUID

class PostgresMovieRepository : MovieRepository {
    override suspend fun allMovies(): List<Movie> = suspendTransaction {
        MovieDAO.all().map { Movie.fromDAO(it)!! }
    }

    override suspend fun movieById(id: UUID): Movie? = suspendTransaction {
        Movie.fromDAO(MovieDAO.findById(id))
    }

    override suspend fun movieByTitle(title: String): Movie? = suspendTransaction {
        MovieDAO
            .find { (MovieTable.title eq title) }
            .limit(1)
            .map { Movie.fromDAO(it) }
            .firstOrNull()
    }

    override suspend fun addMovie(movie: Movie): Unit = suspendTransaction {
        if (movieByTitle(movie.title) != null) {
            throw IllegalStateException("Movie already exists.")
        }
        MovieDAO.new(id=movie.id) {
            title = movie.title
            releaseYear = movie.releaseYear
            runtime = movie.runtime
            overview = movie.overview
        }
    }

    override suspend fun removeMovie(title: String): Boolean = suspendTransaction {
        val rowsDeleted = MovieTable.deleteWhere {
            MovieTable.title eq title
        }
        rowsDeleted == 1
    }
}