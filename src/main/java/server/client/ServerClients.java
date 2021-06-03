package server.client;

import java.util.HashSet;
import java.util.Set;

public class ServerClients implements Clients {
    private final Set<Client> clients;

    public ServerClients() {
        this.clients = new HashSet<>();
    }

    @Override
    public void add(Client client) {
        this.clients.add(client);
    }

    @Override
    public void remove(Client client) {
        this.clients.remove(client);
    }

    @Override
    public void send(String message) {
        for(Client client : clients) {
            client.send(message);
        }
    }
}
