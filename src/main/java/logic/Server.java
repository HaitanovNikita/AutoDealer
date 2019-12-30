package logic;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import tables.Automobile;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.Executors;

import static org.hibernate.internal.util.io.StreamCopier.BUFFER_SIZE;

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

            server.setExecutor(Executors.newCachedThreadPool());
            server.start();

        }catch(IOException e ){
            e.printStackTrace();
        }
    }

    static class StaticHandler implements HttpHandler {

        String answer = "";
        String nameFile = "";
        String query = "";

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
//                String[] strQuery = query.split("/");
//                nameFile = strQuery[1];
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

        private static void readFile_2(HttpExchange exchange, String nameFile){
   /*         String fileСontents = "";


            System.out.println(PATH_FILE + nameFile);
            File file = new File(PATH_FILE + nameFile);
            if (!(file.exists() && !file.isDirectory())) {
                fileСontents = "file not found";
                System.err.println(fileСontents);
            }
            FileInputStream inF = null;
            try {
                DataOutputStream outF = new DataOutputStream(exchange.getResponseBody());
                inF = new FileInputStream(file);
                byte[] bytes = new byte[5*1024];
                int count;
                long lenght = file.length();
                exchange.sendResponseHeaders(200, 0);
                outF.writeLong(lenght);
                while ((count = inF.read(bytes)) > -1) {
                    outF.write(bytes, 0, count);
                }
                outF.close();
                inF.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
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

        /*@Override
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
        }*/
    }
}
