package net.redchamp.hangman.data;

import java.util.Random;

import com.mongodb.*;

public class Phrase {
    private static Random random = new Random();

    public static String fetchRandomPhrase() {
        try {
            MongoClient mongoClient = new MongoClient();

            DB db = mongoClient.getDB("hangman");
            DBCollection movies = db.getCollection("movies");

            DBObject randomMovie;
            do {
                int i = random.nextInt((int) movies.getCount());
                randomMovie = movies.find().skip(i).next();
            } while (randomMovie == null);

            return randomMovie.get("title").toString();
        }
        catch (java.net.UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
