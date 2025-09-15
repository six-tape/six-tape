package cl.sixtape.model.movie

import cl.sixtape.db.MovieDAO
import cl.sixtape.db.MovieTable
import cl.sixtape.db.daoToModel
import cl.sixtape.db.suspendTransaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere

class PostgresMovieRepository : MovieRepository {
    override suspend fun allMovies(): List<Movie> = suspendTransaction {
        MovieDAO.all().map(::daoToModel)
    }

    override suspend fun movieByTitle(title: String): Movie? = suspendTransaction {
        MovieDAO
            .find { (MovieTable.title eq title) }
            .limit(1)
            .map(::daoToModel)
            .firstOrNull()
    }

    override suspend fun addMovie(movie: Movie): Unit = suspendTransaction {
        MovieDAO.new {
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