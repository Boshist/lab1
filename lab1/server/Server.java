import java.io.*;
import java.net.*;

public class Server {

    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        try {
            try {
                server = new ServerSocket(4321);
                System.out.println("Server is active");

                clientSocket = server.accept();

                try {

                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    String message = in.readLine();
					System.out.println("Server received the following:" + message);
					
                    out.write("Client wrote the following: " + message + "\n");
                    out.flush();

                } finally {
                    System.out.println("Closing connection");
                    clientSocket.close();

                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Server is turned off");
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}