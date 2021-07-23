package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class ServerClient implements Runnable, Client {
    private final ChatServer server;
    private final Socket socket;
    private final PrintWriter clientOut;

    public ServerClient(ChatServer server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;
        this.clientOut = new PrintWriter(socket.getOutputStream(), false);
    }

    @Override
    public void send(String message) {
        this.clientOut.write(message + System.lineSeparator());
        this.clientOut.flush();
    }

    @Override
    public void run() {
        try {
            Scanner in = new Scanner(socket.getInputStream());

            this.socket.setSoTimeout(1000);

            while(!socket.isClosed()) {
                if(in.hasNextLine()) {
                    this.server.send(in.nextLine());
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        this.server.remove(this);
    }
}