import java.io.*;
import java.net.*;

public class Client {

    private static Socket clientSocket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        try {
            try {
                clientSocket = new Socket("server", 4321);

                reader = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                System.out.println("Client is waiting for your message");
                String message = reader.readLine();
                out.write(message + "\n");
                out.flush();
                String ServerResponse = in.readLine();
                System.out.println("Server answered: " + ServerResponse);
            } finally {
                System.out.println("Client is being closed");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }
}