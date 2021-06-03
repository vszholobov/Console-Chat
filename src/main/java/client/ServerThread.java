package client;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

public class ServerThread implements Runnable {
    private final LinkedList<String> messagesToSend;
    private final Socket socket;
    private final String userName;

    public ServerThread(Socket socket, String userName) {
        this.socket = socket;
        this.userName = userName;
        this.messagesToSend = new LinkedList<>();
    }

    public void send(String message) {
        synchronized(messagesToSend) {
            messagesToSend.push(message);
        }
    }

    @Override
    public void run() {
        try {
            PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), false);
            InputStream serverIn = socket.getInputStream();
            Scanner serverScanner = new Scanner(serverIn);

            while(!socket.isClosed()) {
                if(serverIn.available() > 0 && serverScanner.hasNextLine()) {
                    System.out.println(serverScanner.nextLine());
                }

                if(messagesToSend.size() > 0) {
                    String message;
                    synchronized(messagesToSend) {
                        message = messagesToSend.pop();
                    }

                    serverOut.write(userName + ": " + message + System.lineSeparator());
                    serverOut.flush();
                }
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}