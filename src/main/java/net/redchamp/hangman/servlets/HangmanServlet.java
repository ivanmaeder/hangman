package net.redchamp.hangman.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.mongodb.*;

public class HangmanServlet extends HttpServlet {
    private String greeting = "Hello World";

    public HangmanServlet() {
        //
    }

    public HangmanServlet(String greeting) {
        this.greeting = greeting;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>"+greeting+"</h1>");
        response.getWriter().println("session=" + request.getSession(true).getId());

        response.getWriter().println(doStuff());
    }

    private String doStuff() {
        // To directly connect to a single MongoDB server (note that this will not auto-discover the primary even
        // if it's a member of a replica set:
        try {
            MongoClient mongoClient = new MongoClient();
            // or
            //MongoClient mongoClient = new MongoClient( "localhost" );
            // or
            //MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

            DB db = mongoClient.getDB( "mydb" );
            DBCollection coll = db.getCollection("testData");
            BasicDBObject query = new BasicDBObject("sessionId", "Robert");
            System.out.println("WTF");
            DBObject myDoc = coll.findOne(query);
            return myDoc.toString();
        }
        catch (java.net.UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
