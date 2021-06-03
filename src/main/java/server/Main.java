package server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Server server = new ChatServer(5555);
        server.start();
    }
}
