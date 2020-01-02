package logic;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import tables.Automobile;
import tables.ModelCar;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


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

        InetSocketAddress inetSocketAddress = new InetSocketAddress(1030);
        HttpServer server = null;
        try{
            server = HttpServer.create(inetSocketAddress,5);
            server.createContext("/", new StaticHandler());
            server.createContext("/api/get-client", new GetClientHandler());

            server.setExecutor(Executors.newCachedThreadPool());
            server.start();

        }catch(IOException e ){
            e.printStackTrace();
        }
    }


    static class GetClientHandler implements HttpHandler{

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String query = exchange.getRequestURI().getQuery();
            if(query!=null){
                System.err.println("GetClientHandler");
                System.err.println("query: "+query);
              String answer = performOperation(query);
              sendResponseHeaders(200,answer,exchange);
              addCors(exchange);
            }
        }

        private String performOperation(String query){
            String[] arrSplitQuery = query.split("operation=|&id=");
            String answer ="", operation="";
            if(arrSplitQuery!=null){
                System.out.println(Arrays.toString(arrSplitQuery));
                 operation = arrSplitQuery[1];
            }
            switch (operation){
                case "getAllModelCars":
                    MachinePartsDaoMySql<ModelCar> partsDaoMySql = new MachinePartsDaoMySql<>(ModelCar.class);
                    for(ModelCar m: partsDaoMySql.read()) answer+=m.toString()+" ";
                    System.err.println("Answer: \n"+answer);
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
        public boolean equals (Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StaticHandler that = (StaticHandler) o;
            return Objects.equals(query, that.query);
        }

        @Override
        public int hashCode () {
            return Objects.hash(query);
        }

        @Override
        public void handle (HttpExchange exchange) throws IOException {

            query = exchange.getRequestURI().toString();
            System.out.println("query: " + query);

            if (!query.equals("/")) {
                System.out.println("query != null");
                nameFile = query;
                if(query.contains(".jpg") | query.contains(".png")){
                    System.err.println("Загружаем картинку/ Запрос: "+query);
                    readFile_2(exchange,nameFile);
                    return;
                }else{
                answer = readFile(nameFile);
                    }
            } else if (query.equals("/")) {
                System.out.println("query = null");
                nameFile = "\\index.html";
                answer = readFile(nameFile);
            }

            if (answer.equals("file not found")) {
                sendResponseHeaders(404, "", exchange);
            } else {
//                System.out.println(answer);
                sendResponseHeaders(200, answer, exchange);
            }
            addCors(exchange);

        }



        private static void readFile_2(HttpExchange exchange, String nameFile){

            String fileСontents = "";
            System.err.println("PATH: "+PATH_FILE + nameFile);
            Path path = Paths.get(PATH_FILE + nameFile);
            try {
                byte[] fileBuffer = Files.readAllBytes(path);
                exchange.sendResponseHeaders(200, fileBuffer.length);
                addCors(exchange);
                OutputStream body = exchange.getResponseBody();
                body.write(fileBuffer);
                body.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        private static String readFile(String nameFile) {
            String fileСontents = "";
            System.out.println(PATH_FILE + nameFile);
            File f = new File(PATH_FILE + nameFile);
            if (!(f.exists() && !f.isDirectory())) {
                fileСontents = "file not found";
                return fileСontents;
            }

            try (FileReader reader = new FileReader(PATH_FILE + nameFile)) {
                int c;
                while ((c = reader.read()) != -1) {
                    fileСontents += (char) c;
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());

            }
            return fileСontents;
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
