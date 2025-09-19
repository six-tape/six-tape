interface MovieDialogProps {
    onSubmit: () => void;
    open: boolean;
}

const MovieDialog = ({
    onSubmit,
    open
}: MovieDialogProps) => {

    return (
        <dialog id="addMovieDialog">
            <form method="dialog">
                <h2>Añadir película</h2>
                <label htmlFor="title">Título</label>
                <input type="text" id="title" name="title" placeholder="Título de la película"/>
                <label htmlFor="summary">Descripción (Opcional)</label>
                <input type="text" id="summary" name="summary" placeholder="Descripción de la película"/>
                <label htmlFor="user">Usuario</label>
                <input type="text" id="user" name="user" placeholder="Usuario que la añadió"/>
                <button onClick={onSubmit}>Añadir</button>
            </form>
        </dialog>
    );
}
export default MovieDialog;