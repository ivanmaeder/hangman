#Hangman
##Requirements
Tested with:

- Jetty 9.2.3
- MongoDB version 2.6.5
- Maven 3.2.3

##Instructions
Start MongoDB. Load the `data/hangman.js` file in a database named `hangman`. This contains the list of phrases used by the game.

Start the server using Maven:

```
mvn jetty:run
```

Point your browser to:

```
http://localhost:8080/
```

##Tests
Run,

```
mvn test
```
