package ua.edu.chmnu.network.java.client;

import java.io.*;
import java.net.*;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 5000;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to the server!");

            System.out.print("Enter your name: ");
            String name = consoleInput.readLine();
            output.println(name);

            System.out.println("Server: " + input.readLine());

            String message;
            while (true) {
                System.out.print("Enter your message: ");
                message = consoleInput.readLine();

                if ("exit".equalsIgnoreCase(message)) {
                    System.out.println("Exiting...");
                    break;
                }

                output.println(message);
                System.out.println("Server: " + input.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
