# six-tape

Movies watchlist webapp with IMDB recommendations

## Branch Managment

We follow GitFlow, that means:

* We have a `main` and a `develop` branch
* All features in the `main` branch should work
* You should never commit directly to `main` or `develop`, always through a `Pull Request`
* Always work from a `feature/{}` or a `fix/{}` branch that is checked out from `develop`
* Use [conventional commits](https://gist.github.com/qoomon/5dfcdf8eec66a051ecd85625518cfd13)


## Project Technology Stack

### Frontend

* Astro with React
* TailwindCSS for styles
* MaterialUI as a React component library

### Backend
* KTor with Kotlin
