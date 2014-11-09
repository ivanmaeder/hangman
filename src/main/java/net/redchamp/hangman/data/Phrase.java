package net.redchamp.hangman.data;

import com.mongodb.*;

public class Phrase {
    public static String fetchRandomPhrase() {
        try {
            MongoClient mongoClient = new MongoClient();

            DB db = mongoClient.getDB("hangman");
            DBCollection movies = db.getCollection("movies");

            /* Movies are stored in this format:

               {random: Math.random(), title: "The Shawshank Redemption", year: 1994}

               Using `random` like that is the recommended method for
               obtaining random documents:

               http://cookbook.mongodb.org/patterns/random-attribute/
             */
            double random = Math.random();

            BasicDBObject query = new BasicDBObject("random", new BasicDBObject("$lt", random));
            DBObject randomMovie = movies.findOne(query);

            if (randomMovie == null) {
                query = new BasicDBObject("random", new BasicDBObject("$gt", random));
                randomMovie = movies.findOne(query);
            }

            return randomMovie.get("title").toString();
        }
        catch (java.net.UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
