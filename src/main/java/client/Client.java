package client;

import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final String serverAddress;
    private final int serverPort;

    public Client(String address, int port) {
        this.serverAddress = address;
        this.serverPort = port;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите псевдоним: ");
        String userName = scanner.nextLine();
        System.out.println("Добро пожаловать, амиго! Чувствуй себя как дома!");

        try {
            Socket socket = new Socket(serverAddress, serverPort);
            Thread.sleep(1000);

            ServerThread server = new ServerThread(socket, userName);
            Thread serverAccessThread = new Thread(server);
            serverAccessThread.start();

            while(serverAccessThread.isAlive()) {
                if(scanner.hasNextLine()) {
                    server.send(scanner.nextLine());
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}