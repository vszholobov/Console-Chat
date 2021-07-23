package server;

public class Main {
    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Передано недостаточно аргументов");
            System.out.println("Нужно передать порт сервера");
        }

        ChatServer server = new ChatServer(Integer.parseInt(args[0]));
        server.start();
    }
}
