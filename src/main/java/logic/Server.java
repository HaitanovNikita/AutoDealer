package logic;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import tables.Automobile;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.concurrent.Executors;

public class Server {

    private final static String PATH_FILE = "C:\\Users\\User\\eclipse-workspace1\\AutoDealer_1\\static";
    private static AutomobileDaoMySQl automobileDaoMySQl;
    private static ArrayList<Automobile> automobileArrayList;

    public Server() {
        automobileDaoMySQl= new AutomobileDaoMySQl();
        starting();
    }

    private void starting(){
        System.out.println("server auto dealer starting!");

        InetSocketAddress inetSocketAddress = new InetSocketAddress(1080);
        HttpServer server = null;
        try{
            server = HttpServer.create(inetSocketAddress,5);
            server.createContext("/api/get-automobile", new GetClientHandler());

            server.setExecutor(Executors.newCachedThreadPool());
            server.start();

        }catch(IOException e ){
            e.printStackTrace();
        }
    }

    static class GetClientHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String automobiles = "";

            try {
                automobileArrayList = automobileDaoMySQl.readAllAutomobiles();
                for (Automobile a : automobileArrayList) {
                    automobiles += a.getId() + " " + a.getModel_car() + " " +
                            a.getCar_make()+ " " + a.getCar_price()+ " " +
                            a.getColor_car()+ " " +a.getEngine_car()+ " " +
                            a.getPower_car()+ " " +a.getType_car_body()+ " " +a.getYear_issue_car()+
                            "\n";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            addCors(exchange);
            exchange.sendResponseHeaders(200, automobiles.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(automobiles.getBytes());
            os.close();

        }
    }

    static void addCors(HttpExchange exchange) {
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET");
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
    }


}
