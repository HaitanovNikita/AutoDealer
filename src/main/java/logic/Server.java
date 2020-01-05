package logic;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import tables.Automobile;
import tables.ModelCar;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.Executors;


public class Server {

    private final static String PATH_FILE = "C:\\Users\\User\\eclipse-workspace1\\AutoDealer_1\\static";
    private static AutomobileDaoMySQl automobileDaoMySQl;
    private static ArrayList<Automobile> automobileArrayList;

    public Server() {
        automobileDaoMySQl = new AutomobileDaoMySQl();
        starting();
    }

    private void starting() {
        System.out.println("server auto dealer starting!");

        InetSocketAddress inetSocketAddress = new InetSocketAddress(1030);
        HttpServer server = null;
        try {
            server = HttpServer.create(inetSocketAddress, 5);
            server.createContext("/", new StaticHandler());
            server.createContext("/api/get-client", new GetClientHandler());

            server.setExecutor(Executors.newCachedThreadPool());
            server.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static class GetClientHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String query = exchange.getRequestURI().getQuery();
            if (query != null) {
                System.err.println("GetClientHandler");
                System.err.println("query: " + query);
                String answer = performOperation(query);
                if (answer != "") {
                    System.err.println("answer: " + answer);
                    sendResponseHeaders(200, answer, exchange);
                } else {
                    sendResponseHeaders(405, answer, exchange);
                }

                addCors(exchange);
            }
        }

        private String performOperation(String query) {
            String[] arrSplitQuery = query.split("operation=|&");
            String answer = "", operation = "";
            if (arrSplitQuery != null) {
                System.out.println(Arrays.toString(arrSplitQuery));
                operation = arrSplitQuery[1];
            }
            switch (operation) {
                case "getAllModelCars":
                    MachinePartsDaoMySql<ModelCar> partsDaoMySql = new MachinePartsDaoMySql<>(ModelCar.class);
                    for (ModelCar m : partsDaoMySql.read()) answer += m.toString() + " ";
                    System.err.println("Answer: \n" + answer);
                    break;
                case "getAutos":
                    System.err.println("Enter case");
                    arrSplitQuery = query.split("&id_model=");
                    Arrays.stream(arrSplitQuery).forEach((s) -> System.out.println("arrays: " + s));
                    AutomobileDaoMySQl automobileDaoMySQl = new AutomobileDaoMySQl();
                    String queryToDb = "Select " +
                            "a.id, a.car_price, a.car_make, a.year_issue_car, " +
                            "p.horse_power, m.name_model, " +
                            "e.type_engine, c.color_car, " +
                            "t.type_body " +
                            "from Automobile as a " +
                            "inner join PowerCar as p on a.power_car = p.ID " +
                            "inner join EngineCar as e on  a.engine_car = e.ID " +
                            "inner join ColorCar as c on a.color_car = c.ID " +
                            "inner join TypeCarBody as t on a.type_car_body= t.ID " +
                            "inner join ModelCar as m on a.model_car = m.ID " +
                            "where a.model_car = " + arrSplitQuery[1];
                    for (Automobile a : automobileDaoMySQl.queryAboutAuto(queryToDb)) {
                        answer += a.getId() + " " + a.getCar_make() + " " + a.getCar_price() + " " +
                                a.getColor_carString() + " " + a.getEngine_carString() + " " + a.getModel_carString() + " " +
                                a.getPower_car() + " " + a.getType_car_bodyString() + " " + a.getYear_issue_car()+" ";
                    }

                    break;
            }
            return answer;
        }
    }

    static class StaticHandler implements HttpHandler {

        private String answer = "";
        private String nameFile = "";
        private String query = "";

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StaticHandler that = (StaticHandler) o;
            return Objects.equals(query, that.query);
        }

        @Override
        public int hashCode() {
            return Objects.hash(query);
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            query = exchange.getRequestURI().toString();
            if (!query.equals("/")) {
                nameFile = query;
                readFile(exchange, nameFile);
                return;
            } else if (query.equals("/")) {
                nameFile = "\\index.html";
                readFile(exchange, nameFile);
                return;
            }
        }

        private static void readFile(HttpExchange exchange, String nameFile) {

            String fileСontents = "";
            Path path = Paths.get(PATH_FILE + nameFile);
            if (path != null) {
                try {
                    byte[] fileBuffer = Files.readAllBytes(path);
                    if (fileBuffer != null) {
                        exchange.sendResponseHeaders(200, fileBuffer.length);
                        addCors(exchange);
                        OutputStream body = exchange.getResponseBody();
                        body.write(fileBuffer);
                        body.close();
                    } else {
                        sendResponseHeaders(404, "", exchange);
                    }
                } catch (NoSuchFileException e) {
                    System.err.println(e.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }

    static void addCors(HttpExchange exchange) {
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET");
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
    }

    private static void sendResponseHeaders(int codeAnswer, String answer, HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(codeAnswer, answer.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(answer.getBytes());
        os.close();
    }
}
