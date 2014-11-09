package net.redchamp.hangman;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.eclipse.jetty.server.*;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.servlet.*;

//import net.redchamp.hangman.servlets.*;


/*

#Jetty and MongoDB
export CLASSPATH=.:/Users/redchamp/Java/jetty-distribution-9.2.3.v20140905/lib/servlet-api-3.1.jar:/Users/redchamp/Java/jetty-distribution-9.2.3.v20140905/lib/jetty-alpn-client-9.2.3.v20140905.jar:/Users/redchamp/Java/jetty-distribution-9.2.3.v20140905/lib/jetty-alpn-server-9.2.3.v20140905.jar:/Users/redchamp/Java/jetty-distribution-9.2.3.v20140905/lib/jetty-annotations-9.2.3.v20140905.jar:/Users/redchamp/Java/jetty-distribution-9.2.3.v20140905/lib/jetty-client-9.2.3.v20140905.jar:/Users/redchamp/Java/jetty-distribution-9.2.3.v20140905/lib/jetty-continuation-9.2.3.v20140905.jar:/Users/redchamp/Java/jetty-distribution-9.2.3.v20140905/lib/jetty-deploy-9.2.3.v20140905.jar:/Users/redchamp/Java/jetty-distribution-9.2.3.v20140905/lib/jetty-http-9.2.3.v20140905.jar:/Users/redchamp/Java/jetty-distribution-9.2.3.v20140905/lib/jetty-io-9.2.3.v20140905.jar:/Users/redchamp/Java/jetty-distribution-9.2.3.v20140905/lib/jetty-jaas-9.2.3.v20140905.jar:/Users/redchamp/Java/jetty-distribution-9.2.3.v20140905/lib/jetty-jaspi-9.2.3.v20140905.jar:/Users/redchamp/Java/jetty-distribution-9.2.3.v20140905/lib/jetty-jmx-9.2.3.v20140905.jar:/Users/redchamp/Java/jetty-distribution-9.2.3.v20140905/lib/jetty-jndi-9.2.3.v20140905.jar:/Users/redchamp/Java/jetty-distribution-9.2.3.v20140905/lib/jetty-plus-9.2.3.v20140905.jar:/Users/redchamp/Java/jetty-distribution-9.2.3.v20140905/lib/jetty-proxy-9.2.3.v20140905.jar:/Users/redchamp/Java/jetty-distribution-9.2.3.v20140905/lib/jetty-quickstart-9.2.3.v20140905.jar:/Users/redchamp/Java/jetty-distribution-9.2.3.v20140905/lib/jetty-rewrite-9.2.3.v20140905.jar:/Users/redchamp/Java/jetty-distribution-9.2.3.v20140905/lib/jetty-schemas-3.1.jar:/Users/redchamp/Java/jetty-distribution-9.2.3.v20140905/lib/jetty-security-9.2.3.v20140905.jar:/Users/redchamp/Java/jetty-distribution-9.2.3.v20140905/lib/jetty-server-9.2.3.v20140905.jar:/Users/redchamp/Java/jetty-distribution-9.2.3.v20140905/lib/jetty-servlet-9.2.3.v20140905.jar:/Users/redchamp/Java/jetty-distribution-9.2.3.v20140905/lib/jetty-servlets-9.2.3.v20140905.jar:/Users/redchamp/Java/jetty-distribution-9.2.3.v20140905/lib/jetty-util-9.2.3.v20140905.jar:/Users/redchamp/Java/jetty-distribution-9.2.3.v20140905/lib/jetty-webapp-9.2.3.v20140905.jar:/Users/redchamp/Java/jetty-distribution-9.2.3.v20140905/lib/jetty-xml-9.2.3.v20140905.jar:/Users/redchamp/Java/mongo-java-driver-2.12.4.jar

*/
public class Hangman extends AbstractHandler {
    /*
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
 
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
 
        context.addServlet(new ServletHolder(new HangmanServlet()),"/*");
        context.addServlet(new ServletHolder(new HangmanServlet("Buongiorno Mondo")),"/it/*");
        context.addServlet(new ServletHolder(new HangmanServlet("Bonjour le Monde")),"/fr/*");
 
        server.start();
        server.join();
    }
    */
        public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response) 
        throws IOException, ServletException
    {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        response.getWriter().println("<h1>Hello World</h1>");
    }
 
    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);
        server.setHandler(new Hangman());
 
        server.start();
        server.join();
    }
}
