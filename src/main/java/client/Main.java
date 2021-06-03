package client;

public class Main {
    public static void main(String[] args) {
        if(args.length < 2) {
            System.out.println("Передано недостаточно аргументов");
            System.out.println("Нужно передать адрес и порт сервера");
        }

        Client client = new Client(args[0], Integer.parseInt(args[1]));
        client.start();
    }
}
