package ua.edu.chmnu.network.java.server;

import java.io.*;
import java.net.*;

public class Server {
    private static final int PORT = 5000;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running on port " + PORT + "...");

            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true)) {

                System.out.println("Client connected: " + clientSocket.getInetAddress());

                String clientName = input.readLine();
                System.out.println("Client name: " + clientName);

                output.println("Hello, " + clientName + "! You can now send a message.");

                String clientMessage;
                while ((clientMessage = input.readLine()) != null) {
                    System.out.println(clientName + " sent: " + clientMessage);
                    output.println("Message sent");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
