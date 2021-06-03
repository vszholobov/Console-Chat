package server;

public interface Server {
    void send(String message);

    void remove(ServerClient client);

    void start();
}
