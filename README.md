#Hangman
##Instructions
Clone project:

```
clone http://...
```

Start MongoDB (version 2.6.5) using the database path `data/db/` from the main project directory. E.g.,

```
cd hangman/
sudo mongod -dbpath ./data/db/
```

With Maven (version 3.2.3), start the server,

```
mvn jetty:run
```

##Tests
Run,

```
mvn test
```
