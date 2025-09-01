interface MovieListItemProps {
    title: string;
    summary?: string;
    user: string;
}

const MovieListItem = ({
                           title,
                           summary = "",
                           user
} : MovieListItemProps) => {
    return (
        <li>
            <article className="flex gap-4 rounded-2xl p-4 bg-secundary-bg">
                <div className="bg-white w-20 h-30"/>
                <div>
                    <h2 className="text-xl font-black">{title}</h2>
                    <h3 className="italic font-semibold">{user}</h3>
                    <p>{summary}</p>
                </div>
            </article>
        </li>
    );
}

export default MovieListItem;