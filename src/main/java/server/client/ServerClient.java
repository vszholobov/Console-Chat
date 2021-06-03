package server.client;

public class ServerClient implements Client {
    private final String name;

    public ServerClient(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public void send(String message) {

    }
}
