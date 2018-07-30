package chat;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

//расширяем WebSocketServlet, который в Jetty
public class WebSocketChatServlet extends WebSocketServlet {
    private final static int LOGOUT_TIME = 10 * 60 * 1000;
    private final ChatService chatService;

    public WebSocketChatServlet() {
        this.chatService = new ChatService();
    }

    @Override
    public void configure(WebSocketServletFactory webSocketServletFactory) {
        //Фабрика производит сокеты
        //в фабрику посвляем время, через которое прекращается работа с user, если есть простой
        webSocketServletFactory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        //при обращении нового user, создается новый сокет
        //те вызывается наша страница /char, прилетает запрос, и мы создаем сокет
        webSocketServletFactory.setCreator((req, resp) -> new ChatWebSocket(chatService));
    }
}
