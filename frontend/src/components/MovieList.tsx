import MovieListItem from "./MovieListItem.tsx";
import { useState } from "react";

interface Movie {
    title: string;
    summary?: string;
    user: string;
}

const MovieList = ({children}: { children: any}) => {

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

    const addMovie = (movie: Movie) => {
        setMoviesList([...moviesList, movie]);
    }
    const onAddButtonClick = () => {
        const dialog = document.getElementById("addMovieDialog") as HTMLDialogElement;
        dialog.showModal();
    }
    return (
        <>
            <ul className="px-4 flex flex-col gap-4">
                {movies.map((movie, index) => (
                    <MovieListItem key={index} title={movie.title} summary={movie.summary} user={movie.user}/>
                ))}
                <li>test</li>
            </ul>

            <dialog id="addMovieDialog" closedby="any">
                <form method="dialog">
                    <h2>Añadir película</h2>
                    <label htmlFor="title">Título</label>
                    <input type="text" id="title" name="title" placeholder="Título de la película"/>
                    <label htmlFor="summary">Descripción (Opcional)</label>
                    <input type="text" id="summary" name="summary" placeholder="Descripción de la película"/>
                    <label htmlFor="user">Usuario</label>
                    <input type="text" id="user" name="user" placeholder="Usuario que la añadió"/>
                    <button>Añadir</button>
                </form>
            </dialog>
            <button onClick={onAddButtonClick} id="addMovieButton" className="bg-primary-interactive rounded-full p-4 absolute bottom-8 right-8">
                {children}
            </button>
        </>
    );
};
export default MovieList;