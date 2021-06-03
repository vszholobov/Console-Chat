package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer implements Server {
    private final int serverPort;
    private final Set<ServerClient> clients;

    public ChatServer(int portNumber) {
        this.serverPort = portNumber;
        this.clients = new HashSet<>();
    }

    @Override
    public void send(String message) {
        System.out.println(message);

        for(ServerClient client : clients) {
            client.send(message);
        }
    }

    @Override
    public void remove(ServerClient client) {
        this.clients.remove(client);
    }

    @Override
    public void start() {
        ServerSocket serverSocket;

        try {
            serverSocket = new ServerSocket(serverPort);
        } catch(IOException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("SUCCESS: server started");
        this.acceptClients(serverSocket);
    }

    private void acceptClients(ServerSocket serverSocket) {
        while(true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("SUCCESS: accepted " + clientSocket.getRemoteSocketAddress());

                ServerClient client = new ServerClient(this, clientSocket);
                clients.add(client);

                Thread thread = new Thread(client);
                thread.start();
            } catch(IOException e) {
                System.out.println("ERROR: could not accept incoming request");
                e.printStackTrace();
            }
        }
    }
}