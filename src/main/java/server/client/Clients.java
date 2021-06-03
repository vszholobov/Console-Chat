package server.client;

public interface Clients {
    void add(Client client);

    void remove(Client client);

    void send(String message);
}
