package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import resources.TestResource;
import servlet.ResourceServer;

public class Main {

    public static void main(String[] args) throws Exception {


        TestResource test = new TestResource();
        Server server = new Server(5050);
        //создаем контейнер
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new ResourceServer(test)), "/resources");

        System.out.println("Server started");
        server.start();
        server.join();
    }


}
