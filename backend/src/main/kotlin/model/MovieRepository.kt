package cl.sixtape.model

interface MovieRepository {
    suspend fun allMovies(): List<Movie>
    suspend fun movieByTitle(title: String): Movie?
    suspend fun addMovie(movie: Movie)
    suspend fun removeMovie(title: String): Boolean
}
