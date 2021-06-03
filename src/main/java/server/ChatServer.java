package server;

import server.client.Client;
import server.client.Clients;
import server.client.ServerClient;
import server.client.ServerClients;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer implements Server {
    private final int port;
    private final Clients clients;

    public ChatServer(int port) {
        this.port = port;
        this.clients = new ServerClients();
    }

    @Override
    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(this.port);

        System.out.println("Server successfully started on: " + serverSocket.getLocalSocketAddress());

        while(true) {
            try {
                Socket socket = serverSocket.accept();

                System.out.println("Accepts: " +
                        socket.getRemoteSocketAddress() +
                        System.lineSeparator() +
                        "Connected ip: " +
                        socket.getInetAddress());

                PrintWriter pw = new PrintWriter(socket.getOutputStream(), false);
                pw.println("Введите имя: ");
                pw.flush();

                Scanner in = new Scanner(socket.getInputStream());
                Client client = new ServerClient(in.nextLine());
                System.out.println(client.name());

                // ClientThread client = new ClientThread(this, socket);
                // Thread thread = new Thread(client);
                // thread.start();
                // clients.add(client);
            } catch(IOException ex) {
                System.out.println("Accept failed on : " + port);
            }
        }
    }
}
