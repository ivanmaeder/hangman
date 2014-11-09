#Hangman
##Running Hangman
###Requirements
Tested with:

- Jetty 9.2.3
- MongoDB 2.6.5
- Maven 3.2.3

###Instructions
Start MongoDB. Load the `data/hangman.js` file in a database named `hangman`. This contains the list of phrases used by the game.

Inside the main `hangman/` directory, start the server using Maven:

```
mvn jetty:run
```

Point your browser to:

```
http://localhost:8080/
```

###Tests
Run from inside the main `hangman/` directory,

```
mvn test
```
##Description
###Back-end
The back-end consists of two services that are STATEFUL (game state is kept on the server). The services (implemented as Servlets) either start a new game or advance in the current game.

####Begin a new game
To begin a game or (or abandon and start a new game).

```
Request: GET /start/
Response: {
    phrase: "Random phrase",
    remaining_moves: 6
}
```

####Advance in the current game
To make a move (take a guess).

```
Request: POST /move/?letter=Q
Response: {
    win: false,
    remaining_moves: 4
}
```

Correct guesses do not alter `remaining_moves` (these guesses are free).

Value `win` will be true when all the letters are guessed; a lost game is defined by `win` being false and `remaining_moves` being zero.

####Persistence
Game state is NOT persisted in an external database, it is kept in session.

MongoDB is used to store a set of phrases that user should guess.

###Front-end
The front-end uses HTML, CSS and jQuery. The back-end governs state (in each guess the front-end queries the back-end about progress).
