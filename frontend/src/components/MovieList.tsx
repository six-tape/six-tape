import MovieListItem from "./MovieListItem";
import { useState } from "react";

interface Movie {
    title: string;
    summary?: string;
    user: string;
}

const MovieList = () => {

    const movies: Movie[] = [
        {
            title: "Arrival",
            summary: "Muy buena película.",
            user: "Vicente"
        },{
            title: "Spaceman",
            summary: "Película no mala de Adam Sandler.",
            user: "Vicente"
        }
    ];
    const [moviesList, setMoviesList] = useState(movies);
    return (
        <ul className="px-4 flex flex-col gap-4">
            {movies.map((movie, index) => (
                <MovieListItem key={index} title={movie.title} summary={movie.summary} user={movie.user}/>
            ))}
        </ul>
    );
}

export default MovieList;
