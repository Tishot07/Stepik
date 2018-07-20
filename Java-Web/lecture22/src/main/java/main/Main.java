package main;

import accounts.AccountService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SignInServlet;
import servlets.SignUpServlet;

public class Main {

    public static void main(String[] args) throws Exception {

        AccountService acc = new AccountService();

        //server jetty с портом 8080
        Server server = new Server(8080);
        //создаем контейнер
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        server.setHandler(context);
        //в контейнер кидаем то, что будет обрабатывать запрос - frontend, который придет на адрес /mirror
        context.addServlet(new ServletHolder(new SignInServlet(acc)), "/signin");
        context.addServlet(new ServletHolder(new SignUpServlet(acc)), "/signup");

        /*
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

*/

        System.out.println("Server started");
        server.start();
        server.join();

    }
}






