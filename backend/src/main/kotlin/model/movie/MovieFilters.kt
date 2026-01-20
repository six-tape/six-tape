package cl.sixtape.model.movie

data class MovieFilters(
    val title: String? = null,
    val watched: Boolean? = null,
    val maxRuntime: Int? = null
)
