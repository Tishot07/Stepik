package main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.Frontend;


public class Main {

    public static void main(String[] args) throws Exception {
        Frontend frontend = new Frontend();

        //server jetty с портом 8080
        Server server = new Server(8080);
        //создаем контейнер
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        server.setHandler(context);
        //в контейнер кидаем то, что будет обрабатывать запрос - frontend, который придет на адрес /mirror
        context.addServlet(new ServletHolder(frontend), "/mirror");

        System.out.println("Server started");
        server.start();
        server.join();

    }
}
