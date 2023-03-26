# Movie-API

Develop an API project based on a public API to meet a set of requirements. Project developed using Movie API: Get movie
recommendations based on inputted criteria, e.g. year/genre/actor/ratings/certification. Checks added for the age of who
will be watching.
This project was developed using Java 17.

---

## Key Features of Solution

+ Backend-only app
+ Users need to interact with API using Postman
+ Create API endpoints with the appropriate HTTP verbs
+ API must call the public API and use the returned data to solve the problem
+ API base URL and endpoints must be appropriately named
+ Good separation of concerns, clean and readable
+ Good coverage of tests
+ Applied error and exception handling considerations in API design
+ Include a /health endpoint to give the health of the application

---

## Assumptions

+ User has access to postman and is aware of end points (included below)
+ Application will use movies only (no tv programmes)
+ Adult movies will not be included

---

## Approaches

+ Sketch and plan ideas first
+ Draw UML
+ Model View Controller (MVC) approach with Spring boot
+ Custom Exceptions to account for invalid requests
+ Testing periodically visited (more Agile than TDD)

---

## SSD

![This is an image](https://github.com/hvferreira/movie-api/blob/master/Docs/SSD.png)

## UML

![This is an image](https://github.com/hvferreira/movie-api/blob/master/Docs/UML.png)

---

## Run Application

+ To run this application - run from "ApiApplication.java"
+ Access the API from software such as Postman (or browser)
+ Runs from http://localhost:8081/api/v1/
+ For endpoints and usage, see below:

## Movie

**/movie/{movieId}** <br />
*Returns: movie object* <br />
Get the movie info. for a movie with a particular id <br />
*e.g. /movie/20* <br />

**/movie/{movieId}/recommendations** <br />
*Returns: list of movie objects* <br />
Get a list of movie recommendations based on the movie with the given Id <br />
*E.g. /movie/20/recommendations* <br />

**/movie/{movieId}/similar** <br />
*Returns: list of movie objects* <br />
Get a list of similar movies based on the movie with the given id <br />
*E.g. /movie/20/similar* <br />

**/movie/actor?actor1={actor1Id}&actor2={actor2Id}** <br />
*Returns: list of movie objects* <br />
Get a list of movies that the actors with the given ids has appeared it. Actor 2 is optional. <br />
*E.g. /movie/actor?actor1=31&actor2=12898 <br />
/movie/actor?actor1=31*

**/movie/{movieId}/director**<br />
*Returns: a director object*<br />
Get the director name based on the movie with the given id<br />
*E.g. /movie/20/director*

**/movie/genrelist** <br />
*Returns: list of genre objects* <br />
Get the genres (categories) available for movies <br />
*E.g. /movie/genrelist*

**/movie/popularMovies**<br />
*Returns: list of movie objects*<br />
Get all the currently popular movies<br />
*E.g. /movie/popularMovies*

**/movie/topRatedMovies** <br />
*Returns: list of movie objects*<br />
Get all the top rated movies<br />
*E.g. /movie/topRatedMovies*<br />

**/movie/latestMovie**<br />
*Returns: a movie object*<br />
Get the latest movie that has been added to the external api<br />
*E.g. /movie/latestMovie*

**/movie/{movieId}/actors**<br />
*Returns: list of actor objects*<br />
Get the actors that appear in the movie with the given id<br />
*E.g. /movie/20/actors*

**/movie/findMovies**<br />
*Returns: a list of movie objects*<br />
Get movies using certain parameters (ALL optional)<br />

+ Rating - gte<br />
+ Release from date (default 1900-01-01 in YYYY-MM-DD format) - gte<br />
+ Genre<br />
+ Runtime (in minutes) - gte<br />

*E.g. /movie/findMovies?rating=9.0&genre=28&date_from=2000-10-02&time_available=90*

**/movie/random** <br />
*Returns: a random movie*<br />
Get a completely random movie<br />
*E.g. movie/random*

**movie/rating?rateMin={rateMin}&rateMax={rateMax}** <br />
*Returns a list of movie objects* <br />
Get a list of movie objects between a specific minimum rating and maximum rating. The maximum rating is optional <br />
*E.g. movie/rating?rateMin=7.2&rateMax=9.2 <br />
/rating?rateMin=9.0*

**/movie/upcoming** <br />
*Returns: list of movie objects*<br />
Get a list of movies <br />
*E.g. /movie/upcoming*

## Actor

**/actor/{actorId}** <br />
*Returns: an actor object* <br />
Get the actor by the given id<br />
*E.g. /actor/2*

**/actor/popular** <br />
*Returns: list of actor objects* <br />
Get the current popular actors <br />
*E.g. /actor/popular*

## Search

**/search/movie/{searchTerm}** <br />
*Returns: a list of movie objects* <br />
Get a list of movies based on a search term <br />
*E.g. /search/movie/”the lord of the rings”*

**/search/actor/{searchTerm}** <br />
*Returns: a list of actor objects* <br />
Get a list of actors based on a search term<br />
*E.g. /search/actor/”tom”*

## Reviews

**/movie/{movieId}/reviews** <br />
*Returns a list of review objects* <br />
Get a list of review objects for a particular movie with the given Id<br />
*E.g. /movie/28/reviews*

---

## Future Considerations

+ Create frontend
+ Create accounts
+ Users can create a list of movies to watch
+ Create list of users
+ Each user can have a list with favourite movies/actors
+ Review Movies
+ Suggest movie considering the weather
+ User can share list of own favourite movies
+ Tell platform (netflix, amazon, HBO, …) where you can watch that movie
+ Rate Movies
+ Deploy API into a cloud service (AWS, GCP, Azure)
+ API usage for TV programmes
+ Add Database
+ User can give suggestions for API improvement
+ Each movie has a recommendation of food/drinks (from food API) and order from delivery apps (UberEats, Deliveroo)
+ User can have a list of movies to watch in a day selected by user or random criteria